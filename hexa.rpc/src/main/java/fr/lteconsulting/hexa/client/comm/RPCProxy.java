package fr.lteconsulting.hexa.client.comm;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import fr.lteconsulting.hexa.client.comm.RPCBatchRequestSender.XRPCBatchRequestSender;
import fr.lteconsulting.hexa.client.interfaces.IAsyncCallback;
import fr.lteconsulting.hexa.client.tools.HexaTools;

public class RPCProxy
{
	private String baseUrl = null;

	private XRPCProxy callback = null;
	private ArrayList<XRPCProxyStatus> statusCallbacks;

	private ArrayList<BeforeNetworkRequestHandler> beforeNetworkRequestHandlers;
	private ArrayList<AfterNetworkRequestHandler> afterNetworkRequestHandlers;

	ArrayList<RPCBatchRequestSender> batchRequestSenders = null;

	boolean isSendScheduled = false;

	int nbSentBytes = 0;
	int nbReceivedBytes = 0;

	public RPCProxy()
	{
		batchRequestSenders = new ArrayList<RPCBatchRequestSender>();
		statusCallbacks = new ArrayList<XRPCProxyStatus>();
	}

	public void Init( String baseUrl, XRPCProxy serverCommMessageCb )
	{
		this.baseUrl = baseUrl;
		this.callback = serverCommMessageCb;
	}

	public Object addBeforeNetworkRequestHandler( BeforeNetworkRequestHandler handler )
	{
		if( beforeNetworkRequestHandlers == null )
			beforeNetworkRequestHandlers = new ArrayList<BeforeNetworkRequestHandler>();

		beforeNetworkRequestHandlers.add( handler );

		return handler;
	}

	public void removeBeforeNetworkRequestHandler( Object registration )
	{
		beforeNetworkRequestHandlers.remove( registration );
	}

	public Object addAfterNetworkRequestHandler( AfterNetworkRequestHandler handler )
	{
		if( afterNetworkRequestHandlers == null )
			afterNetworkRequestHandlers = new ArrayList<AfterNetworkRequestHandler>();

		afterNetworkRequestHandlers.add( handler );

		return handler;
	}

	public void removeAfterNetworkRequestHandler( Object registration )
	{
		afterNetworkRequestHandlers.remove( registration );
	}

	public void registerStatusCallback( XRPCProxyStatus callback )
	{
		statusCallbacks.add( callback );
	}

	public void sendRequest( RequestDesc request, Object cookie, XRPCRequest callback )
	{
		RPCBatchRequestSender sender = getBatchRequestSender();

		sender.addRequest( new RequestCallInfo( request, callback, cookie ) );

		scheduleSend();
	}

	private void scheduleSend()
	{
		if( isSendScheduled )
			return;

		isSendScheduled = true;
		Scheduler.get().scheduleDeferred( sendCommand );
	}

	private ScheduledCommand sendCommand = new ScheduledCommand()
	{
		@Override
		public void execute()
		{
			isSendScheduled = false;

			// something to send ?
			if( batchRequestSenders.isEmpty() )
				return;

			// send the first batch ready to be sent
			for( RPCBatchRequestSender sender : batchRequestSenders )
			{
				if( sender.isReadyToSend() )
				{
					sender.send();
					return;
				}
			}
		}
	};

	private XRPCBatchRequestSender batchSenderCallback = new XRPCBatchRequestSender()
	{
		@Override
		public List<BeforeNetworkRequestHandler> getBeforeNetworkRequestHandlers()
		{
			return beforeNetworkRequestHandlers;
		}

		@Override
		public List<AfterNetworkRequestHandler> getAfterNetworkRequestHandlers()
		{
			return afterNetworkRequestHandlers;
		}

		@Override
		public void error( RPCErrorCodes errorCode, Exception exception, RPCBatchRequestSender sender )
		{
			nbReceivedBytes += sender.getNbReceivedBytes();

			batchRequestSenders.remove( sender );

			switch( errorCode )
			{
				case ERROR_REQUEST_SEND:
					HexaTools.alert( "Error with server connexion", "Error while sending the request" );
					break;
				case ERROR_REQUEST_RESPONSE_STATUS:
					HexaTools.alert( "Error with server connexion", "The remote server seems so be down.... Try refreshing the applicatio please..." );
					break;
				case ERROR_REQUEST_RESPONSE_EMPTY:
					HexaTools.alert( "Error with server connexion", "Empty response from the server, connexion may have been cut !" );
					break;
				case ERROR_REQUEST_RESPONSE_PARSE:
					callback.signalResultParseError( sender.getReceivedText(), sender.getTrace() );
					break;
				case ERROR_REQUEST_GWT:
					callback.signalRequestError( sender.getTrace(), exception );
					break;
			}

			statusRefresh();
			scheduleSend();
		}

		@Override
		public void answerReceived( RPCBatchRequestSender sender )
		{
			nbReceivedBytes += sender.getNbReceivedBytes();

			// TODO : I removed that because the assert was to often not
			// verified
			// TODO : reordering answers is then needed !
			// int index = batchRequestSenders.indexOf( sender );
			// assert index == 0;

			batchRequestSenders.remove( sender );

			for( RequestCallInfo info : sender.sentRequests )
			{
				// signal server message to interested callbacks
				if( info.msg.length() > 0 )
					callback.signalServerCommMessage( info.msgLevel, info.msg );

				if( info.msgLevel == 1 )
					continue; // don't process replies that are in error...

				if( info.hangout != null )
				{
					// the server tells us a hang out process is needed
					hangOut( info, info.hangout );
				}
				else
				{
					// normal case fallback
					info.giveResultToCallbacks();
				}
			}

			statusRefresh();
			scheduleSend();
		}

		@Override
		public void sent( RPCBatchRequestSender request )
		{
			nbSentBytes += request.getNbSentBytes();

			statusRefresh();
		}
	};

	// handles the case where an hangOut has been generated by the server
	// in this case, we give control back to the application so that the user
	// can provide the missing information, then, we send the hangout answer
	// over the network
	private void hangOut( final RequestCallInfo req, GenericJSO hangOut )
	{
		final int hangOutId = hangOut.getIntByIdx( 0 );

		JsArrayString tmp = hangOut.getGenericJSOByIdx( 1 ).cast();
		String name = tmp.get( 0 );
		String type = tmp.get( 1 );
		String title = tmp.get( 2 );
		String description = tmp.get( 3 );
		JavaScriptObject hangOutCurrentData = hangOut.getGenericJSOByIdx( 2 );

		callback.hangOut( title, description, name, type, hangOutCurrentData, new IAsyncCallback<JavaScriptObject>()
		{
			@Override
			public void onSuccess( JavaScriptObject result )
			{
				JSONArray params = new JSONArray();
				params.set( 0, new JSONNumber( hangOutId ) );
				params.set( 1, new JSONObject( result ) );
				req.request = new RequestDesc( req.request.service, req.request.interfaceChecksum, "_hang_out_reply_", params );

				sendRequest( req.request, req.cookie, req.callback );
			}
		} );
	}

	// retrieve a batch request sender that is OK for adding requests
	private RPCBatchRequestSender getBatchRequestSender()
	{
		RPCBatchRequestSender sender = null;

		// test the last sender we have in the list
		if( !batchRequestSenders.isEmpty() )
		{
			sender = batchRequestSenders.get( batchRequestSenders.size() - 1 );
			if( sender.canAddRequest() )
				return sender;
		}

		// otherwise, create a new one
		sender = new RPCBatchRequestSender();
		sender.init( baseUrl, batchSenderCallback );
		batchRequestSenders.add( sender );

		return sender;
	}

	private void statusRefresh()
	{
		String status;
		int nbPending = 0;
		for( RPCBatchRequestSender sender : batchRequestSenders )
			if( sender.isSending() )
				nbPending++;

		if( nbPending == 0 )
			status = "OK";
		else
			status = "Loading";

		for( XRPCProxyStatus callback : statusCallbacks )
			callback.onServerCommStatusChanged( status, nbPending, nbSentBytes, nbReceivedBytes );
	}
}
