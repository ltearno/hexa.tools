package com.hexa.client.comm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;
import com.hexa.client.comm.ServerComm.ServerCommCb;

// stores the method calls that are sent in one network request
// manages the process of sending the network request
// and handling its result
public class NetworkPendingRequestInfo implements RequestCallback, Scheduler.RepeatingCommand
{
	public interface XNetworkPendingRequestInfo
	{
		void sent( NetworkPendingRequestInfo request );

		void answer( RequestCallInfo info, int msgLevel, String msg, GenericJSO hangOut, ResponseJSO retValue, NetworkPendingRequestInfo request );

		void error( ServerComm.ErrorCodes errorCode, Exception exception, NetworkPendingRequestInfo request );

		void answerReceived( NetworkPendingRequestInfo request );

		List<BeforeNetworkRequestHandler> getBeforeNetworkRequestHandlers();
		
		List<AfterNetworkRequestHandler> getAfterNetworkRequestHandlers();
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

	HashMap<String, ServiceInfo> services = new HashMap<String, ServiceInfo>();

	XNetworkPendingRequestInfo callback;

	// method calls sent in this network request
	ArrayList<RequestCallInfo> sentRq = new ArrayList<RequestCallInfo>();

	// sent data
	public String sentUrl = null;
	private int nbSentBytes;

	// last GWT sent request, network object
	List<RequestCallInfo> lastPrependedCallsSent;
	List<RequestCallInfo> lastAppendedCallsSent;
	Request lastSentReq = null;

	// server response
	Response answer = null;

	// server's raw text reply
	String receivedTxt = null;

	// server's parsed reply
	JsArray<GenericJSO> jso = null;

	// which next callback is to call back
	int nextToProcess = 0;

	public NetworkPendingRequestInfo( XNetworkPendingRequestInfo callback )
	{
		this.callback = callback;
	}

	private String getServiceKey( RequestCallInfo info )
	{
		return info.request.service + "-" + info.request.interfaceChecksum;
	}

	// adds a request to be sent
	public void toSend( RequestCallInfo info )
	{
		checkCallService( info );

		sentRq.add( info );
	}

	// necessary to be sure that call's service is referenced
	private void checkCallService( RequestCallInfo info )
	{
		String service = info.request.service;
		String interfaceChecksum = info.request.interfaceChecksum;

		String key = getServiceKey( info );

		if( !services.containsKey( key ) )
		{
			ServiceInfo serviceInfo = new ServiceInfo( service, interfaceChecksum );
			serviceInfo.id = services.size();
			services.put( key, serviceInfo );
		}
	}

	// sends all the method calls, giving baseUrl to construct the query
	public void send( String baseUrl )
	{
		sentUrl = baseUrl + "&locale=" + LocaleInfo.getCurrentLocale().getLocaleName();

		resend();
	}

	// sends the prepared data through the network
	public void resend()
	{
		if( sentUrl == null )
			return;

		// get custom first calls
		final List<RequestCallInfo> prependedCalls = new ArrayList<RequestCallInfo>();
		final List<RequestCallInfo> appendedCalls = new ArrayList<RequestCallInfo>();
		
		// get the preprended calls
		
		AcceptsRPCRequests prependerImpl = new AcceptsRPCRequests() {
			public void sendRequest( boolean fUseCache, boolean fInvalidate, RequestDesc request, Object cookie, ServerCommCb callback )
			{
				// ignore useCache and fInvalidate
				
				RequestCallInfo callInfo = new RequestCallInfo( request );
				callInfo.register( callback, cookie );

				checkCallService( callInfo );

				prependedCalls.add( callInfo );
			}
		};
		
		
		List<BeforeNetworkRequestHandler> prependers = callback.getBeforeNetworkRequestHandlers();
		if( prependers != null )
		{
			for( BeforeNetworkRequestHandler prepender : prependers )
			{
				// let the prepender add its customized requests
				prepender.onBeforeNetworkRequest( prependerImpl );
			}
		}
		
		// get the appended calls
		
		AcceptsRPCRequests appenderImpl = new AcceptsRPCRequests() {
			public void sendRequest( boolean fUseCache, boolean fInvalidate, RequestDesc request, Object cookie, ServerCommCb callback )
			{
				// ignore useCache and fInvalidate
				
				RequestCallInfo callInfo = new RequestCallInfo( request );
				callInfo.register( callback, cookie );

				checkCallService( callInfo );

				appendedCalls.add( callInfo );
			}
		};
		
		
		List<AfterNetworkRequestHandler> appenders = callback.getAfterNetworkRequestHandlers();
		if( appenders != null )
		{
			for( AfterNetworkRequestHandler appender : appenders )
			{
				// let the prepender add its customized requests
				appender.onAfterNetworkRequest( appenderImpl );
			}
		}

		// services
		JSONArray servicesUsed = new JSONArray();
		for( ServiceInfo service : services.values() )
		{
			service.id = servicesUsed.size(); // re order services...

			servicesUsed.set( servicesUsed.size(), service.getJson() );
		}

		// API Calls part of the url
		JSONArray calls = new JSONArray();

		// prepended calls
		for( RequestCallInfo info : prependedCalls )
			calls.set( calls.size(), serializeCall( info ) );

		// normal calls
		for( RequestCallInfo info : sentRq )
			calls.set( calls.size(), serializeCall( info ) );
		
		// appended calls
		for( RequestCallInfo info : appendedCalls )
			calls.set( calls.size(), serializeCall( info ) );

		// final payload
		JSONArray payload = new JSONArray();
		payload.set( 0, servicesUsed );
		payload.set( 1, calls );

		RequestBuilder builderPost = buildMultipart( "payload", payload.toString() );

		nbSentBytes += builderPost.getRequestData().length();

		try
		{
			lastPrependedCallsSent = prependedCalls;
			lastAppendedCallsSent = appendedCalls;
			lastSentReq = builderPost.send();

			callback.sent( this );
		}
		catch( RequestException e )
		{
			callback.error( ServerComm.ErrorCodes.ERROR_REQUEST_SEND, e, this );
		}
	}

	private JSONArray serializeCall( RequestCallInfo info )
	{
		ServiceInfo serviceInfo = services.get( getServiceKey( info ) );

		JSONArray json = info.request.getJson();
		json.set( 2, new JSONNumber( serviceInfo.id ) );

		return json;
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

	public Response getResponse()
	{
		return answer;
	}

	public String getReceivedText()
	{
		return receivedTxt;
	}

	public String getTrace()
	{
		return sentUrl;
	}

	public void onResponseReceived( Request request, Response response )
	{
		if( response.getStatusCode() == 500 )
		{
			callback.error( ServerComm.ErrorCodes.ERROR_REQUEST_RESPONSE_STATUS, null, this );

			return;
		}

		receivedTxt = response.getText();
		if( receivedTxt == null || receivedTxt.length() == 0 )
		{
			callback.error( ServerComm.ErrorCodes.ERROR_REQUEST_RESPONSE_EMPTY, null, this );
			return;
		}

		callback.answerReceived( this );

		try
		{
			// parses the json encoded answer
			// native : JSON.parse(jsonString);
			// eval('(' + jsonString + ')');
			JsArray<GenericJSO> jso = JsonUtils.unsafeEval( receivedTxt ).cast();

			beginAnswerToClients( jso );
		}
		catch( Exception exception )
		{
			callback.error( ServerComm.ErrorCodes.ERROR_REQUEST_RESPONSE_PARSE, exception, this );
		}
	}

	public void onError( Request request, Throwable exception )
	{
		Exception e = null;
		if( exception instanceof Exception )
			e = (Exception) exception;
		callback.error( ServerComm.ErrorCodes.ERROR_REQUEST_GWT, e, this );
	}

	// prepare a multipart form http request
	private RequestBuilder buildMultipart( String name, String value )
	{
		String boundary = "AJAX------" + Math.random() + "" + new Date().getTime();

		RequestBuilder builderPost = new RequestBuilder( RequestBuilder.POST, sentUrl );
		builderPost.setHeader( "Content-Type", "multipart/form-data; charset=utf-8; boundary=" + boundary );
		builderPost.setCallback( this );

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

	// receives the answer and call the clients back
	void beginAnswerToClients( JsArray<GenericJSO> rawAnswer )
	{
		// register the rawAnswer
		jso = rawAnswer;

		// reset the soft looping over the different calls
		nextToProcess = 0;

		// softly give the rsult back for each call in the request
		Scheduler.get().scheduleIncremental( this );
	}

	@Override
	public boolean execute()
	{
		// get the next call to be processed
		if( nextToProcess >= jso.length() )
			return false; // finished !!!

		int reqId = nextToProcess;
		nextToProcess++;

		RequestCallInfo request;
		int prependedCallsSize = lastPrependedCallsSent != null ? lastPrependedCallsSent.size() : 0;
		int appenedCallSize = lastAppendedCallsSent != null ? lastAppendedCallsSent.size() : 0;
		int normalCallSize = jso.length() - prependedCallsSize - appenedCallSize;
		
		if( reqId < prependedCallsSize )
			request = lastPrependedCallsSent.get( reqId );
		else if( reqId < ( prependedCallsSize + normalCallSize ) )
			request = sentRq.get( reqId - prependedCallsSize );
		else
			request = lastAppendedCallsSent.get( reqId - prependedCallsSize - normalCallSize );

		// get the request info back,
		// answer the clients,
		// and answer the call corresponding to this request
		answerCall( request, jso.get( reqId ) );

		return nextToProcess < jso.length();
	}

	// takes the appropriate decision about a raw answer coming back to its
	// request
	void answerCall( RequestCallInfo rqInfo, GenericJSO rawAnswer )
	{
		// decrypt the raw answer
		int msgLevel = rawAnswer.getIntByIdx( 0 );
		String msg = rawAnswer.getStringByIdx( 1 );
		GenericJSO hangOut = rawAnswer.getGenericJSOByIdx( 2 );
		ResponseJSO retValue = rawAnswer.getResponseJSOByIdx( 3 );

		callback.answer( rqInfo, msgLevel, msg, hangOut, retValue, this );
	}
}