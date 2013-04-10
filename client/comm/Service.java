package com.hexa.client.comm;

import com.hexa.client.comm.ServerComm.ServerCommMessageCb;
import com.hexa.client.interfaces.IAsyncCallback;
import com.hexa.client.interfaces.ITablesManager;
import com.hexa.client.interfaces.IUserLogInfo;

public interface Service
{
	public void Init( String baseUrl, IUserLogInfo userLogInfo, ServerCommMessageCb serverCommMessageCb, Service service, ITablesManager tablesManager );
	public String getInterfaceChecksum();
	
	public void sendErrorData( String text, String trace, IAsyncCallback<Integer> callback );
	
	public ServerComm getInternalServerComm();
}
