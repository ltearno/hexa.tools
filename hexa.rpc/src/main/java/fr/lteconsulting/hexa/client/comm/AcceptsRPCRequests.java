package fr.lteconsulting.hexa.client.comm;

public interface AcceptsRPCRequests
{
	void sendRequest( boolean fUseCache, boolean fInvalidate, RequestDesc request, Object cookie, XRPCRequest callback );
}
