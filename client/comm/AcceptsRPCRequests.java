package com.hexa.client.comm;

import com.hexa.client.comm.ServerComm.ServerCommCb;

public interface AcceptsRPCRequests
{
	void sendRequest( boolean fUseCache, boolean fInvalidate, RequestDesc request, Object cookie, ServerCommCb callback );
}
