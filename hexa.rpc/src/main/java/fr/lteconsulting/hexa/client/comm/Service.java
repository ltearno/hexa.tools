package fr.lteconsulting.hexa.client.comm;

public interface Service
{
	public String getInterfaceChecksum();

	public void Init( AcceptsRPCRequests server );
}
