package com.hexa.client.comm;

import com.google.gwt.json.client.JSONObject;

// This interface is implemented by a client and called by the server
// at the moment of sending a network request to the proxied server
//
// This allows the client to add its own customized payloads to the request
public interface GlobalRequestPayloadAdder
{
	interface PayloadAdder
	{
		void addPayload( String id, JSONObject data );
	}
	
	// each time a request is about to be sent, this method is called on all registered
	// payload adders
	void onBeforeSendRequest( PayloadAdder adder );
}
