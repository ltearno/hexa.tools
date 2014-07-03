package fr.lteconsulting.hexa.server.rpc;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import fr.lteconsulting.hexa.server.tools.Trace;
import fr.lteconsulting.hexa.shared.rpc.ListInteger;

class ServiceDescription
{
	String name;
	String checksum;
	Object delegate;
	String[] methodShortcuts;

	// cache methods informations
	private final HashMap<String, MethodInfo> methodsCache = new HashMap<String, MethodInfo>();

	private static class MethodInfo
	{
		MethodInfo( Method method )
		{
			this.method = method;
			paramTypes = method.getParameterTypes();
		}

		Method method;

		Class<?>[] paramTypes;
	}

	public ServiceDescription( String name, String checksum, Object delegate, String[] methodShortcuts )
	{
		this.name = name;
		this.checksum = checksum;
		this.delegate = delegate;
		this.methodShortcuts = methodShortcuts;
	}

	private MethodInfo findMethod( String methodName )
	{
		MethodInfo result = methodsCache.get( methodName );
		if( result != null )
			return result;

		Method[] methods = delegate.getClass().getMethods();
		for( int i = 0; i < methods.length; i++ )
		{
			Method method = methods[i];
			if( method.getName().equals( methodName ) )
			{
				MethodInfo methodInfo = new MethodInfo( method );
				methodsCache.put( methodName, methodInfo );
				return methodInfo;
			}
		}

		return null;
	}

	public Object call( String method, JsonArray parameters )
	{
		// Try parsing a numeric encoded value of the called method
		try
		{
			method = methodShortcuts[Integer.parseInt( method )];
		}
		catch( NumberFormatException badFormat )
		{
		}

		Trace.it( "Calling method " + method );

		MethodInfo jmethod = findMethod( method );
		if( jmethod == null )
		{
			throw new RuntimeException( "Called method not found in delegate of class " + delegate.getClass().getName() );
		}

		Trace.it( "Deserialize parameters" );

		// Deserialize the parameters
		Class<?>[] paramTypes = jmethod.paramTypes;
		if( !(parameters == null && paramTypes.length == 0) && parameters.size() != paramTypes.length )
			throw new InvalidParameterException( "parameters.size()!=paramTypes.length" );

		Object[] jparams = new Object[paramTypes.length];

		for( int i = 0; i < paramTypes.length; i++ )
		{
			Trace.it( "Param " + i );

			Class<?> classType = paramTypes[i];
			JsonElement p = parameters.get( i );

			jparams[i] = deserializeParameters( classType, p );
		}

		// Call the delegate
		try
		{
			Trace.it( "Real call..." );
			Object res = jmethod.method.invoke( delegate, jparams );
			Trace.it( "Ok" );
			return res;
		}
		catch( Exception e )
		{
			Trace.it( "Failed with: " + e.getClass().getName() );
			Trace.throwable( e.getCause() );
			throw new RuntimeException( e );
		}
	}

	private Object deserializeParameters( Class<?> classType, JsonElement p )
	{
		if( p == null )
			return null;

		if( classType == String.class )
			return p.getAsString();
		else if( classType == int.class )
			return p.getAsInt();
		else if( classType == Integer.class )
			return new Integer( p.getAsInt() );
		else if( classType == ListInteger.class )
		{
			JsonArray jsonArray = p.getAsJsonArray();
			int size = jsonArray.size();

			ListInteger res = new ListInteger();
			for( int i = 0; i < size; i++ )
				res.add( (Integer) deserializeParameters( Integer.class, jsonArray.get( i ) ) );

			return res;
		}
		else if( classType.isArray() )
		{
			JsonArray jsonArray = p.getAsJsonArray();
			int size = jsonArray.size();

			Class<?> componentClass = classType.getComponentType();

			if( componentClass.isPrimitive() )
			{
				if( componentClass == int.class )
				{
					int[] res = new int[size];
					for( int i = 0; i < size; i++ )
						res[i] = jsonArray.get( i ).getAsInt();
					return res;
				}
				else if( componentClass == String.class )
				{
					String[] res = new String[size];
					for( int i = 0; i < size; i++ )
						res[i] = jsonArray.get( i ).getAsString();
					return res;
				}
			}

			Object res = Array.newInstance( componentClass, size );
			for( int i = 0; i < size; i++ )
				Array.set( res, i, deserializeParameters( componentClass, jsonArray.get( i ) ) );

			return res;
		}
		else if( classType == ArrayList.class )
		{
			throw new RuntimeException( "Deserialization of an ArrayList<?>, please use one of the wrapping types like ListInteger...)" );
		}
		else
		{
			throw new RuntimeException( "Unknown deserialization of parameter for class " + classType.getName() + " json element is " + p.toString() );
		}
	}
}