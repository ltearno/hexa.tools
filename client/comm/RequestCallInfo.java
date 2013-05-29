package com.hexa.client.comm;

import java.util.ArrayList;

import com.google.gwt.core.client.Scheduler;
import com.hexa.client.comm.ServerComm.ServerCommCb;

// Stores all clients waiting for a specific RequestDesc
// manages the process of calling back the clients
public class RequestCallInfo
{
	// request description
	public RequestDesc request;

	// clients waiting on it
	private ArrayList<ServerCommCb> callbacks = new ArrayList<ServerCommCb>();
	private ArrayList<Object> cookies = new ArrayList<Object>();

	// data, when received
	private ResponseJSO retValue = null;
	private int msgLevel = 0;
	private String msg = null;

	public RequestCallInfo( RequestDesc request )
	{
		this.request = request;
	}

	public void register( ServerCommCb callback, Object cookie )
	{
		callbacks.add( callback );
		cookies.add( cookie );
	}

	void giveResultToCallbacks( ResponseJSO retValue, int msgLevel, String msg )
	{
		this.retValue = retValue;
		this.msgLevel = msgLevel;
		this.msg = msg;

		// quietly give back the results
		Scheduler.get().scheduleIncremental( new Scheduler.RepeatingCommand()
		{
			public boolean execute()
			{
				if( callbacks.isEmpty() )
					return false;

				ServerCommCb callback = callbacks.remove( 0 );
				Object cookie = cookies.remove( 0 );

				callback.onResponse( cookie, RequestCallInfo.this.retValue, RequestCallInfo.this.msgLevel, RequestCallInfo.this.msg );

				return !callbacks.isEmpty();
			}
		} );
	}
}
