package com.hexa.client.comm;

import java.util.ArrayList;

import com.hexa.client.comm.CachedServerComm;
import com.hexa.client.comm.DataProxyFastFactories;
import com.hexa.client.comm.ServerComm;
import com.hexa.client.comm.callparams.BooleanMarshall;
import com.hexa.client.comm.callparams.DoubleMarshall;
import com.hexa.client.comm.callparams.HexaDateMarshall;
import com.hexa.client.comm.callparams.HexaDateTimeMarshall;
import com.hexa.client.comm.callparams.HexaTimeMarshall;
import com.hexa.client.comm.callparams.ITableMarshall;
import com.hexa.client.comm.callparams.IntMarshall;
import com.hexa.client.comm.callparams.JSOMarshall;
import com.hexa.client.comm.callparams.StringMarshall;

public class ServiceBase
{
	protected CachedServerComm srv = new CachedServerComm();
	protected DataProxyFastFactories factory = new DataProxyFastFactories();
	
	protected String interfaceChecksum = null;
	
	// marshalls
	protected BooleanMarshall booleanMarshall = new BooleanMarshall();
	protected IntMarshall intMarshall = new IntMarshall();
	protected StringMarshall stringMarshall = new StringMarshall();
	protected JSOMarshall jsoMarshall = new JSOMarshall();
	protected HexaDateTimeMarshall dateTimeMarshall = new HexaDateTimeMarshall();
	protected HexaDateMarshall dateMarshall = new HexaDateMarshall();
	protected HexaTimeMarshall timeMarshall = new HexaTimeMarshall();
	protected DoubleMarshall doubleMarshall = new DoubleMarshall();
	protected ITableMarshall itableMarshall = new ITableMarshall();
	
	protected void setConfig( String interfaceChecksum )
	{
		this.interfaceChecksum = interfaceChecksum;
	}
	
	public String getInterfaceChecksum()
	{
	    return interfaceChecksum;
	}
	
	public ServerComm getInternalServerComm()
	{
		return srv.getInternatlServerComm();
	}
	
	ArrayList<GlobalRequestPayloadAdder> globalRequestPayloadAdders = null;
	public Object AddGlobalRequestPayloadAdder( GlobalRequestPayloadAdder adder )
	{
		if( adder == null )
			return null;
		
		if( globalRequestPayloadAdders == null )
			globalRequestPayloadAdders = new ArrayList<GlobalRequestPayloadAdder>();
		
		globalRequestPayloadAdders.add( adder );
		
		return adder;
	}
	
	ArrayList<GlobalRequestCallAdder> globalRequestCallAdders = null;
	public Object AddGlobalRequestCallAdder( GlobalRequestCallAdder adder )
	{
		if( adder == null )
			return null;
		
		if( globalRequestCallAdders == null )
			globalRequestCallAdders = new ArrayList<GlobalRequestCallAdder>();
		
		globalRequestCallAdders.add( adder );
		
		return adder;
	}
}
