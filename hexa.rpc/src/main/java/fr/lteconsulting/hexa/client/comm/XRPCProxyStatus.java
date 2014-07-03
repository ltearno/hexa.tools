package fr.lteconsulting.hexa.client.comm;

public interface XRPCProxyStatus
{
	void onServerCommStatusChanged( String status, int nbRqPending, int sentBytes, int receivedBytes );
}
