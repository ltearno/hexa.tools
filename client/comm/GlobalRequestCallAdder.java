package com.hexa.client.comm;

import com.google.gwt.json.client.JSONArray;
import com.hexa.client.comm.ServerComm.ServerCommCb;

//This interface is implemented by a client and called by the server
//at the moment of sending a network request to the proxied server
//
//This allows the client to add its own procedure calls to the request
public interface GlobalRequestCallAdder
{
	interface CallAdder
	{
		void addCall( String service, String interfaceChecksum, String method, JSONArray params, Object cookie, ServerCommCb callback );
	}
	
	// each time a request is about to be sent, this method is called on all registered
	// call adders
	void onBeforeSendRequest( CallAdder adder );
}
