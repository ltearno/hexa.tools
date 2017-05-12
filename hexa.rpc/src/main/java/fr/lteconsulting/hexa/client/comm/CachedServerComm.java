package fr.lteconsulting.hexa.client.comm;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;

/*
 * class CachedServerComm
 *
 * Handles the process of caching service calls results
 * It mainly replicate ServerComm interface but with
 * added parameters to manage cached data
 *
 * It also ensures that answers are given to the clients
 * in the order they were asked.
 */
public class CachedServerComm implements XRPCRequest, AcceptsRPCRequests
{
	RPCProxy srv = new RPCProxy();

	public void Init( String baseUrl, XRPCProxy serverCommMessageCb )
	{
		srv.Init( baseUrl, serverCommMessageCb );
	}

	public RPCProxy getInternalServerComm()
	{
		return srv;
	}

	private final ArrayList<PendingRequestInfo> pendingRequests = new ArrayList<PendingRequestInfo>();
	private final HashMap<String, ResponseJSO> cache = new HashMap<String, ResponseJSO>();

	// requests as received by the sendRequest method
	private ArrayList<RequestCallInfo> requestStack = new ArrayList<RequestCallInfo>();

	private boolean fCallbackingScheduled = false;

	class PendingRequestInfo
	{
		boolean fStoreResultInCache;

		String requestKey;

		ArrayList<RequestCallInfo> subscriptions = new ArrayList<RequestCallInfo>();

		public PendingRequestInfo( boolean fStoreResultInCache, RequestCallInfo requestCallInfo )
		{
			this.fStoreResultInCache = fStoreResultInCache;

			requestKey = requestCallInfo.request.getUniqueKey();
			addSubscription( requestCallInfo );
		}

		public void addSubscription( RequestCallInfo requestCallInfo )
		{
			subscriptions.add( requestCallInfo );
		}
	}

	@Override
	public void sendRequest( boolean fUseCache, boolean fInvalidate, RequestDesc request, Object cookie, XRPCRequest callback )
	{
		// destroys the data cache if specified so
		if( fInvalidate )
		{
			//GWT.log( "CACHE-CLEAR for request " + request.getUniqueKey() + " / " + request.getExtraInfo() );
			cache.clear();
		}

		RequestCallInfo requestCallInfo = new RequestCallInfo( request, callback, cookie );
		requestStack.add( requestCallInfo );

		if( fUseCache )
		{
			//GWT.log( "CACHE-STATE for request " + request.getUniqueKey() + " / " + request.getExtraInfo() + " size:" + cache.size() + " hasResponse:" + cache.containsKey( request.getUniqueKey() ) );

			// is the result already in cache ?
			ResponseJSO cached = cache.get( request.getUniqueKey() );
			if( cached != null )
			{
				requestCallInfo.setResult( 0, null, null, cached );
				checkAnswersToGive();
				return;
			}

			// is the same request already pending ?
			for( PendingRequestInfo pending : pendingRequests )
			{
				if( !pending.requestKey.equals( request.getUniqueKey() ) )
					continue;

				pending.addSubscription( requestCallInfo );
				return;
			}
		}

		// create a pending request
		PendingRequestInfo pending = new PendingRequestInfo( fUseCache && (!fInvalidate), requestCallInfo );
		pendingRequests.add( pending );

		// send the request to the server
		srv.sendRequest( request, pending, this );
	}

	// receives the answer from the ServerComm object
	@Override
	public void onResponse( Object cookie, ResponseJSO response, int msgLevel, String msg )
	{
		PendingRequestInfo info = (PendingRequestInfo) cookie;

		// Store answer in cache
		if( info.fStoreResultInCache )
			cache.put( info.requestKey, response );

		// give the result to all the subscribees
		for( RequestCallInfo call : info.subscriptions )
			call.setResult( msgLevel, msg, null, response );

		// forget this request
		pendingRequests.remove( info );

		// calls back the clients
		checkAnswersToGive();
	}

	private void checkAnswersToGive()
	{
		if( fCallbackingScheduled )
			return;

		fCallbackingScheduled = true;
		Scheduler.get().scheduleFinally( checkResults );
	}

	ScheduledCommand checkResults = new ScheduledCommand()
	{
		@Override
		public void execute()
		{
			fCallbackingScheduled = false;

			while( !requestStack.isEmpty() )
			{
				RequestCallInfo info = requestStack.get( 0 );

				// we give back results in the order requests have been made,
				// here we may need to wait for other responses to arrive
				if( !info.fResultReceived )
				{
					GWT.log( "cannot give further results are we are still waiting for previous one..." );
					return;
				}

				requestStack.remove( 0 );

				info.giveResultToCallbacks();
			}
		}
	};
}
