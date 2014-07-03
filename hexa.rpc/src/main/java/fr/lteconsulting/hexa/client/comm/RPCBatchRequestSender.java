package fr.lteconsulting.hexa.client.comm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;

/*
 * Purpose of this class is to send a nucket of requests,
 * handle if the client wants to send pre- and post- network requests,
 * and return the result to its caller
 */
public class RPCBatchRequestSender
{
	public interface XRPCBatchRequestSender
	{
		void sent( RPCBatchRequestSender request );

		void answerReceived( RPCBatchRequestSender request );

		void error( RPCErrorCodes errorCode, Exception exception, RPCBatchRequestSender request );

		List<BeforeNetworkRequestHandler> getBeforeNetworkRequestHandlers();

		List<AfterNetworkRequestHandler> getAfterNetworkRequestHandlers();
	}

	private final int MAX_NB_REQUESTS = 20;

	XRPCBatchRequestSender callback = null;

	String url = null;

	HashMap<String, ServiceInfo> usedServices = new HashMap<String, ServiceInfo>();
	ArrayList<RequestCallInfo> requestsToSend = new ArrayList<RequestCallInfo>();
	ArrayList<RequestCallInfo> sentRequests = null;

	int nbSentBytes = 0;
	Request sentRequest = null;

	String receivedTxt = null;

	// initialize with url, and other needed information
	public void init( String baseUrl, XRPCBatchRequestSender callback )
	{
		this.url = baseUrl + "&locale=" + LocaleInfo.getCurrentLocale().getLocaleName();
		this.callback = callback;
	}

	// returns true if this instance can send another RPC in this batch
	// basically, once the request batch is prepared and sent, no more calls can
	// be added
	public boolean canAddRequest()
	{
		return sentRequest == null && requestsToSend.size() < MAX_NB_REQUESTS;
	}

	public boolean isReadyToSend()
	{
		return sentRequest == null;
	}

	public boolean isSending()
	{
		return sentRequest != null && receivedTxt == null;
	}

	// adds a request to the batch
	public void addRequest( RequestCallInfo info )
	{
		checkCallService( info );

		requestsToSend.add( info );
	}

	public String getReceivedText()
	{
		return receivedTxt;
	}

	public String getTrace()
	{
		return url;
	}

	public int getNbSentBytes()
	{
		return nbSentBytes;
	}

	public int getNbReceivedBytes()
	{
		if( receivedTxt == null )
			return 0;

		return receivedTxt.length();
	}

	// necessary to be sure that call's service is referenced
	private void checkCallService( RequestCallInfo info )
	{
		String service = info.request.service;
		String interfaceChecksum = info.request.interfaceChecksum;

		String key = getServiceKey( info );

		if( usedServices.containsKey( key ) )
			return;

		ServiceInfo serviceInfo = new ServiceInfo( service, interfaceChecksum );
		serviceInfo.id = usedServices.size();
		usedServices.put( key, serviceInfo );
	}

	private String getServiceKey( RequestCallInfo info )
	{
		return info.request.service + "-" + info.request.interfaceChecksum;
	}

	// sends the batch in the network
	// when response arrives, it will be given back to each request callback
	public boolean send()
	{
		assert sentRequests == null;
		sentRequests = new ArrayList<RequestCallInfo>();

		// add prepended requests,
		addPrependedRequests( sentRequests );

		// add requests to send
		while( !requestsToSend.isEmpty() )
			sentRequests.add( requestsToSend.remove( 0 ) );

		// add appended requests
		addAppendedRequests( sentRequests );

		// prepare payload
		JSONArray payload = createPayload();

		RequestBuilder builderPost = buildMultipart( "payload", payload.toString() );

		nbSentBytes += builderPost.getRequestData().length();

		try
		{
			sentRequest = builderPost.send();
		}
		catch( RequestException e )
		{
			callback.error( RPCErrorCodes.ERROR_REQUEST_SEND, e, this );
			return false;
		}

		callback.sent( this );

		return true;
	}

	private final RequestCallback requestCallback = new RequestCallback()
	{
		@Override
		public void onResponseReceived( Request request, Response response )
		{
			int statusCode = response.getStatusCode();

			if( statusCode == 500 || statusCode == 0 )
			{
				callback.error( RPCErrorCodes.ERROR_REQUEST_RESPONSE_STATUS, null, RPCBatchRequestSender.this );
				return;
			}

			receivedTxt = response.getText();
			if( receivedTxt == null || receivedTxt.length() == 0 )
			{
				callback.error( RPCErrorCodes.ERROR_REQUEST_RESPONSE_EMPTY, null, RPCBatchRequestSender.this );
				return;
			}

			assert statusCode == 200;

			JsArray<GenericJSO> jso = null;
			try
			{
				// parses the json encoded answer
				// native : JSON.parse(jsonString);
				// eval('(' + jsonString + ')');
				jso = JsonUtils.unsafeEval( receivedTxt ).cast();
			}
			catch( Exception exception )
			{
				callback.error( RPCErrorCodes.ERROR_REQUEST_RESPONSE_PARSE, exception, RPCBatchRequestSender.this );
				return;
			}

			int nbRequests = sentRequests.size();
			int nbAnswers = jso.length();
			assert nbRequests == nbAnswers;
			for( int r = 0; r < nbRequests; r++ )
			{
				// pair each request with its answer
				RequestCallInfo requestCallInfo = sentRequests.get( r );
				GenericJSO rawAnswer = jso.get( r );

				// decrypt the raw answer
				int msgLevel = rawAnswer.getIntByIdx( 0 );
				String msg = rawAnswer.getStringByIdx( 1 );
				GenericJSO hangOut = rawAnswer.getGenericJSOByIdx( 2 );
				ResponseJSO retValue = rawAnswer.<ResponseJSO>getJavaScriptObjectByIdx( 3 );

				requestCallInfo.setResult( msgLevel, msg, hangOut, retValue );
			}

			callback.answerReceived( RPCBatchRequestSender.this );
		}

		@Override
		public void onError( Request request, Throwable exception )
		{
			Exception e = null;
			if( exception instanceof Exception )
				e = (Exception) exception;

			callback.error( RPCErrorCodes.ERROR_REQUEST_GWT, e, RPCBatchRequestSender.this );
		}
	};

	// prepare a multipart form http request
	private RequestBuilder buildMultipart( String name, String value )
	{
		String boundary = "AJAX------" + Math.random() + "" + new Date().getTime();

		RequestBuilder builderPost = new RequestBuilder( RequestBuilder.POST, url );
		builderPost.setHeader( "Content-Type", "multipart/form-data; charset=utf-8; boundary=" + boundary );
		builderPost.setCallback( requestCallback );

		String CRLF = "\r\n";
		String data = "--" + boundary + CRLF;
		data += "--" + boundary + CRLF;

		data += "Content-Disposition: form-data; ";
		data += "name=\"" + name + "\"" + CRLF + CRLF;
		data += value + CRLF;
		data += "--" + boundary + "--" + CRLF;

		builderPost.setRequestData( data );

		return builderPost;
	}

	private JSONArray createPayload()
	{
		// services
		JSONArray servicesUsed = new JSONArray();
		for( ServiceInfo service : usedServices.values() )
		{
			service.id = servicesUsed.size(); // re order services...
			servicesUsed.set( service.id, service.getJson() );
		}

		// API Calls part of the url
		JSONArray calls = new JSONArray();

		for( RequestCallInfo info : sentRequests )
			calls.set( calls.size(), serializeCall( info ) );

		// final payload
		JSONArray payload = new JSONArray();
		payload.set( 0, servicesUsed );
		payload.set( 1, calls );

		return payload;
	}

	private JSONArray serializeCall( RequestCallInfo info )
	{
		ServiceInfo serviceInfo = usedServices.get( getServiceKey( info ) );

		JSONArray json = info.request.getJson();
		json.set( 2, new JSONNumber( serviceInfo.id ) );

		return json;
	}

	private void addPrependedRequests( final List<RequestCallInfo> requestsList )
	{
		AcceptsRPCRequests prependerImpl = new AcceptsRPCRequests()
		{
			@Override
			public void sendRequest( boolean fUseCache, boolean fInvalidate, RequestDesc request, Object cookie, XRPCRequest callback )
			{
				RequestCallInfo callInfo = new RequestCallInfo( request, callback, cookie );
				checkCallService( callInfo );
				requestsList.add( callInfo );
			}
		};

		List<BeforeNetworkRequestHandler> prependers = callback.getBeforeNetworkRequestHandlers();
		if( prependers == null )
			return;

		// let the prependers add their customized requests
		for( BeforeNetworkRequestHandler prepender : prependers )
			prepender.onBeforeNetworkRequest( prependerImpl );
	}

	private void addAppendedRequests( final List<RequestCallInfo> requestsList )
	{
		AcceptsRPCRequests appenderImpl = new AcceptsRPCRequests()
		{
			@Override
			public void sendRequest( boolean fUseCache, boolean fInvalidate, RequestDesc request, Object cookie, XRPCRequest callback )
			{
				RequestCallInfo callInfo = new RequestCallInfo( request, callback, cookie );
				checkCallService( callInfo );
				requestsList.add( callInfo );
			}
		};

		List<AfterNetworkRequestHandler> appenders = callback.getAfterNetworkRequestHandlers();
		if( appenders == null )
			return;

		// let the appenders add their customized requests
		for( AfterNetworkRequestHandler appender : appenders )
			appender.onAfterNetworkRequest( appenderImpl );
	}

	private static class ServiceInfo
	{
		public ServiceInfo( String service, String interfaceChecksum )
		{
			this.service = service;
			this.interfaceChecksum = interfaceChecksum;
		}

		public JSONArray getJson()
		{
			JSONArray json = new JSONArray();

			json.set( 0, new JSONString( service ) );
			json.set( 1, new JSONString( interfaceChecksum ) );

			return json;
		}

		int id;
		String service;
		String interfaceChecksum;
	}
}
