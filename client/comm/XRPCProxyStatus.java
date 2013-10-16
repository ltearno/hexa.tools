package com.hexa.client.comm;

public interface XRPCProxyStatus
{
	void onServerCommStatusChanged( String status, int nbRqPending, int sentBytes, int receivedBytes );
}
