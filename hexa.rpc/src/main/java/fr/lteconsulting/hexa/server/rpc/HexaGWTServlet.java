package fr.lteconsulting.hexa.server.rpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import fr.lteconsulting.hexa.client.comm.DataProxy;
import fr.lteconsulting.hexa.client.comm.FieldName;
import fr.lteconsulting.hexa.client.common.HexaDateTime;
import fr.lteconsulting.hexa.server.tools.LoggerFactory;
import fr.lteconsulting.hexa.server.tools.Trace;

public abstract class HexaGWTServlet extends HttpServlet
{
	private static final long serialVersionUID = 3471403271348698342L;

	private final Logger log = LoggerFactory.getLogger();

	HashMap<String, ServiceDescription> services = new HashMap<String, ServiceDescription>();

	abstract protected void onInit(); // opportunity to configure implemented
										// services

	@Override
	public void init()
	{
		onInit();
	}

	protected void addService( String serviceName, String serviceChecksum, Object delegate, String[] methodShortcuts )
	{
		services.put( serviceName, new ServiceDescription( serviceName, serviceChecksum, delegate, methodShortcuts ) );
	}

	private ServiceDescription getServiceDelegate( String serviceName, String serviceChecksum )
	{
		ServiceDescription desc = services.get( serviceName );
		if( desc != null && !desc.checksum.equals( serviceChecksum ) )
		{
			Trace.it( "Service not available with this checksum: " + serviceName + ":" + serviceChecksum + " (we have " + desc.checksum + ")" );
			return null;
		}
		return desc;
	}

	@Override
	protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException
	{
		resp.addHeader( "Pragma", "no-cache" );
		resp.addHeader( "Cache-Control", "no-cache" );

		// TODO get locale

		// TODO create a logger

		ServletFileUpload upload = new ServletFileUpload();

		try
		{
			FileItemIterator iterator = upload.getItemIterator( req );

			while( iterator.hasNext() )
			{
				FileItemStream stream = iterator.next();
				if( stream.getFieldName().equals( "payload" ) )
				{
					String response = processPayload( stream.openStream() );
					resp.getWriter().write( response );
					return;
				}
			}
		}
		catch( Exception e )
		{
			resp.getWriter().write( "Exception during POST processing : " + e.getMessage() );
			e.printStackTrace();
			return;
		}

		resp.getWriter().write( "INSUFICIENT PAYLOAD" );
	}

	private String processPayload( InputStream payloadStream ) throws IOException, HexaGWTRPCException
	{
		Trace.push();

		// read the rpc payload
		String payloadString = readPayloadStream( payloadStream );
		if( payloadString == null )
			throw new HexaGWTRPCException( "Unreadable payload : " + Trace.pop() );

		Trace.it( "Processing payload :" );
		Trace.it( "Payload size: " + payloadString.length() );
		Trace.it( "Json content: " + payloadString );

		// parse the payload
		JsonArray payload = parsePayload( payloadString );
		if( payload == null )
			throw new HexaGWTRPCException( "Unparsable payload : " + Trace.pop() );

		JsonArray serviceDescriptions = payload.get( 0 ).getAsJsonArray();
		JsonArray calls = payload.get( 1 ).getAsJsonArray();

		// Service object bindings
		ServiceDescription[] services = prepareUsedServices( serviceDescriptions );
		if( services == null )
			throw new HexaGWTRPCException( "Unavailable service(s) : " + Trace.pop() );

		// prepare answer
		JsonArray result = new JsonArray();

		Trace.it( "Preparing " + calls.size() + " call(s)" );

		// calls to the services
		for( int i = 0; i < calls.size(); i++ )
		{
			Trace.push();
			Trace.it( "Call " + i );

			JsonArray call = calls.get( i ).getAsJsonArray();
			JsonArray responseSerialized = processCall( call, services );

			// appending the answer
			result.add( responseSerialized );

			Trace.pop();
		}

		// log.log(Level.INFO, Trace.peek());

		Trace.pop();

		return result.toString();
	}

	private String readPayloadStream( InputStream payloadStream ) throws IOException
	{
		BufferedReader in = new BufferedReader( new InputStreamReader( payloadStream, "utf-8" ) );

		StringBuilder sb = new StringBuilder();
		int readden = 1024;
		char[] buf = new char[readden];
		do
		{
			readden = in.read( buf );
			sb.append( buf, 0, readden );
		}
		while( readden == buf.length );

		return sb.toString();
	}

	private JsonArray parsePayload( String payload )
	{
		JsonParser parser = new JsonParser();

		JsonElement element = parser.parse( payload );
		if( element == null )
			return null;

		JsonArray result = element.getAsJsonArray();

		return result;
	}

	private ServiceDescription[] prepareUsedServices( JsonArray serviceDescriptions )
	{
		boolean ok = true;

		ServiceDescription[] services = new ServiceDescription[serviceDescriptions.size()];
		for( int i = 0; i < serviceDescriptions.size(); i++ )
		{
			JsonArray serviceDescription = serviceDescriptions.get( i ).getAsJsonArray();

			String serviceName = serviceDescription.get( 0 ).getAsString(); // Service
																			// name
			String serviceChecksum = serviceDescription.get( 1 ).getAsString(); // service
																				// checksum

			services[i] = getServiceDelegate( serviceName, serviceChecksum );
			if( services[i] == null )
			{
				Trace.it( "Service not available : " + serviceName + ":" + serviceChecksum );
				ok = false;
			}
		}

		return ok ? services : null;
	}

	private JsonArray processCall( JsonArray call, ServiceDescription[] services )
	{
		String method = call.get( 0 ).getAsString();
		JsonArray parameters = call.get( 1 ).getAsJsonArray();
		int serviceIdx = call.get( 2 ).getAsInt();

		Trace.it( "Service:" + services[serviceIdx].name + ", method: " + method + ", params:" + parameters );

		// call to the method
		JsonArray responseSerialized = new JsonArray();
		Object response = null;
		try
		{
			response = services[serviceIdx].call( method, parameters );
			Trace.it( "Call made Ok." );

			// encode the response
			JsonArray encodedResponse = new JsonArray();
			if( response != null && response.getClass().isArray() )
			{
				Trace.it( "Multi-valued return value." );

				// multiple return values
				Object[] responses = (Object[]) response;
				for( int r = 0; r < responses.length; r++ )
					encodedResponse.add( encodeResponse( responses[r] ) );
			}
			else
			{
				encodedResponse.add( encodeResponse( response ) );
			}

			// serialization of the returned value
			responseSerialized.add( new JsonPrimitive( 0 ) ); // ServerState/Level
			responseSerialized.add( new JsonPrimitive( "" ) ); // ServerState/Message
			responseSerialized.add( null ); // Hang_Out_Code
			responseSerialized.add( encodedResponse ); // Encoded_response
		}
		catch( Exception e )
		{
			Trace.throwable( e );
			Trace.it( "Call impossible, exception during call !!!" );

			log.info( Trace.peek() );

			// serialization of the returned value
			responseSerialized.add( new JsonPrimitive( 3 ) ); // ServerState/Level
			responseSerialized.add( new JsonPrimitive( "Exception during call : " + Trace.peek() ) ); // ServerState/Message
			responseSerialized.add( null ); // Hang_Out_Code
			responseSerialized.add( null ); // Encoded_response
		}

		return responseSerialized;
	}

	public static JsonElement encodeResponse( Object response )
	{
		if( response == null )
			return null;

		if( response instanceof List<?> )
		{
			List<?> list = (List<?>) response;
			JsonArray encoded = new JsonArray();

			for( Object e : list )
				encoded.add( encodeResponse( e ) );

			return encoded;
		}

		if( response instanceof Iterable<?> )
		{
			Iterable<?> iterable = (Iterable<?>) response;
			JsonArray encoded = new JsonArray();

			for( Object e : iterable )
				encoded.add( encodeResponse( e ) );

			return encoded;
		}

		if( response instanceof DataProxy )
		{
			// encode a bean
			JsonObject encoded = new JsonObject();

			// find the DataProxy sub class this response herits from
			HashSet<Method> methods = new HashSet<Method>();
			getDataProxyMethods( response.getClass(), methods );

			for( Method m : methods )
			{
				FieldName fieldNameAnnotation = m.getAnnotation( FieldName.class );
				if( fieldNameAnnotation == null )
					continue;

				try
				{
					String field = fieldNameAnnotation.fieldName();
					Object value = m.invoke( response );

					encoded.add( field, encodeResponse( value ) );
				}
				catch( Exception e )
				{
					e.printStackTrace();
				}
			}

			return encoded;
		}

		if( response instanceof String )
			return new JsonPrimitive( (String) response );

		if( response instanceof Number )
			return new JsonPrimitive( (Number) response );

		if( response instanceof HexaDateTime )
			return new JsonPrimitive( ((HexaDateTime) response).getString() );

		return null;
	}

	private static void getDataProxyMethods( Class<?> clazz, HashSet<Method> result )
	{
		if( clazz == null || clazz == Object.class )
			return;

		// find annotated methods within the super class
		getDataProxyMethods( clazz.getSuperclass(), result );

		// find annotated methods within the implemented interfaces
		Class<?>[] implementedInterfaces = clazz.getInterfaces();
		for( int i = 0; i < implementedInterfaces.length; i++ )
			getDataProxyMethods( implementedInterfaces[i], result );

		// find annoted methods in the class
		Method[] methods = clazz.getDeclaredMethods();
		for( int i = 0; i < methods.length; i++ )
		{
			Method m = methods[i];

			FieldName fieldNameAnnotation = m.getAnnotation( FieldName.class );
			if( fieldNameAnnotation == null )
				continue;

			result.add( m );
		}
	}
}