package com.hexa.client.comm;

public interface XRPCRequest
{
	void onResponse( Object cookie, ResponseJSO response, int msgLevel, String msg );
}
