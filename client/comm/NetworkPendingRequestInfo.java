package com.hexa.client.comm;

import java.util.ArrayList;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.json.client.JSONArray;

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
	}
	
	XNetworkPendingRequestInfo callback;
	
	// method calls sent in this network request
	ArrayList<RequestCallInfo> sentRq = new ArrayList<RequestCallInfo>();
	
	// sent data
	public String sentUrl = null;
	public String sentData = null;
	
	// last GWT sent request, network object
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
	
	// adds a request to be sent
	public void toSend( RequestCallInfo info )
	{
		sentRq.add( info );
	}
	
	// sends all the method calls, giving baseUrl to construct the query
	public void send( String baseUrl )
	{
		sentUrl = baseUrl + "&locale=" + LocaleInfo.getCurrentLocale().getLocaleName();
		
		// API Calls part of the url
		JSONArray calls = new JSONArray();
		for( RequestCallInfo info : sentRq )
			calls.set( calls.size(), info.request.getJson() );
		
		sentData = "calls_json=" + URL.encode( calls.toString() );
		
		resend();
	}
	
	// sends the prepared data through the network
	public void resend()
	{
		if( sentUrl==null || sentData==null )
			return;
		
		RequestBuilder builderPost = new RequestBuilder( RequestBuilder.POST, sentUrl );
		builderPost.setHeader( "Content-Type", "application/x-www-form-urlencoded" );
		builderPost.setRequestData( sentData );
		builderPost.setCallback( this );
		
		try
		{
			lastSentReq = builderPost.send();
			
			callback.sent( this );
		}
		catch( RequestException e )
		{
			callback.error( ServerComm.ErrorCodes.ERROR_REQUEST_SEND, e, this );
		}
	}
	
	public int getNbSentBytes()
	{
		if( sentData == null )
			return 0;
		
		return sentData.length();
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
		if( receivedTxt==null || receivedTxt.length()==0 )
		{
			callback.error( ServerComm.ErrorCodes.ERROR_REQUEST_RESPONSE_EMPTY, null, this );
			return;
		}
		
		callback.answerReceived( this );
		
		try
		{
			// parses the json encoded answer
			// native : JSON.parse(jsonString);
			//  eval('(' + jsonString + ')');
			JsArray<GenericJSO> jso = JsonUtils.unsafeEval( receivedTxt ).cast();
			
			beginAnswerToClients( jso );					
		}
		catch ( Exception exception )
		{
			callback.error( ServerComm.ErrorCodes.ERROR_REQUEST_RESPONSE_PARSE, exception, this );
		}
	}
	
	public void onError( Request request, Throwable exception )
	{
		Exception e = null;
		if( exception instanceof Exception )
			e = (Exception)exception;
		callback.error( ServerComm.ErrorCodes.ERROR_REQUEST_GWT, e, this );
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
		
		// get the request info back,
		// answer the clients,
		// and answer the call corresponding to this request
		answerCall( sentRq.get( reqId ), jso.get( reqId ) );
		
		return nextToProcess < jso.length();
	}
	
	// takes the appropriate decision about a raw answer coming back to its request
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