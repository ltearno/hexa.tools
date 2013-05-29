package com.hexa.client.comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.hexa.client.comm.ServerComm.ServerCommCb;
import com.hexa.client.comm.ServerComm.ServerCommMessageCb;

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
public class CachedServerComm implements ServerCommCb, AcceptsRPCRequests
{
	ServerComm srv = new ServerComm();

	public void Init( String baseUrl, ServerCommMessageCb serverCommMessageCb )
	{
		srv.Init( baseUrl, serverCommMessageCb );
	}

	public ServerComm getInternalServerComm()
	{
		return srv;
	}

	List<CallInfo> pendingRequests = new ArrayList<CallInfo>();
	HashMap<String, ResponseJSO> cache = new HashMap<String, ResponseJSO>();

	@Override
	public void sendRequest( boolean fUseCache, boolean fInvalidate, RequestDesc request, Object cookie, ServerCommCb callback )
	{
		// destroys the data cache if specified so
		if( fInvalidate )
		{
			GWT.log( "Clear cache", null );
			cache.clear();
		}

		CallInfo info = new CallInfo( fUseCache && (!fInvalidate), request, cookie, callback );
		pendingRequests.add( info );

		if( fUseCache )
		{
			ResponseJSO cached = cache.get( request.getUniqueKey() );
			if( cached != null )
			{
				// because results are already in the cache
				info.fPutResultInCache = false;

				// give results
				info.setResult( cached, 0, "" );

				// calls back the clients
				checkAnswersToGive();

				return;
			}
		}

		// really send the request to the server
		srv.sendRequest( request, info, this );
	}

	// receives the answer from the ServerComm object
	@Override
	public void onResponse( Object cookie, ResponseJSO response, int msgLevel, String msg )
	{
		CallInfo info = (CallInfo) cookie;

		// Store answer in cache
		if( info.fPutResultInCache )
			cache.put( info.request.getUniqueKey(), response );

		// stores the result
		info.setResult( response, msgLevel, msg );

		// calls back the clients
		checkAnswersToGive();
	}

	private void checkAnswersToGive()
	{
		Scheduler.get().scheduleFinally( checkResults );
	}

	ScheduledCommand checkResults = new ScheduledCommand()
	{
		public void execute()
		{
			while( !pendingRequests.isEmpty() )
			{
				CallInfo info = pendingRequests.get( 0 );

				// we give back results in the order requests have been made
				if( !info.fResult )
					return;

				pendingRequests.remove( 0 ).callback.onResponse( info.cookie, info.response, info.msgLevel, info.msg );
			}
		}
	};

	// stores call to sendRequest(...) information
	class CallInfo
	{
		CallInfo( boolean fPutResultInCache, RequestDesc request, Object cookie, ServerCommCb callback )
		{
			this.fPutResultInCache = fPutResultInCache;
			this.request = request;
			this.cookie = cookie;
			this.callback = callback;
		}

		void setResult( ResponseJSO response, int msgLevel, String msg )
		{
			fResult = true;
			this.response = response;
			this.msgLevel = msgLevel;
			this.msg = msg;
		}

		// cache directives
		boolean fPutResultInCache;

		// request-call description
		RequestDesc request;

		// caller information
		ServerCommCb callback;
		Object cookie;

		// results, valid only when fResult is true;
		boolean fResult = false;
		ResponseJSO response = null;
		int msgLevel;
		String msg;
	}
}
