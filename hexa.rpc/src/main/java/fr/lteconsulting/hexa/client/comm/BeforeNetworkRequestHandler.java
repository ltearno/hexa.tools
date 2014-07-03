package fr.lteconsulting.hexa.client.comm;

public interface BeforeNetworkRequestHandler
{
	void onBeforeNetworkRequest( AcceptsRPCRequests requestPrepender );
}
