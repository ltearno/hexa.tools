package fr.lteconsulting.hexa.client.comm;

public interface XRPCRequest
{
	void onResponse( Object cookie, ResponseJSO response, int msgLevel, String msg );
}
