package com.hexa.client.comm;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class RequestDesc
{
	String service;
	String interfaceChecksum;
	String method = null;
	JSONArray params = null;
	
	String key = null;
	
	public RequestDesc( String service, String interfaceChecksum, int method, JSONArray params )
	{
		this( service, interfaceChecksum, String.valueOf(method), params );
	}
	
	public RequestDesc( String service, String interfaceChecksum, String method, JSONArray params )
	{
		this.service = service;
		this.interfaceChecksum = interfaceChecksum;
		this.method = method;
		this.params = params;
	}
	
	public String getUniqueKey()
	{
		if( key == null )
			key = _getUniqueKey();
		
		return key;
	}
	
	public JSONObject getJson()
	{
		JSONObject obj = new JSONObject();
		
		obj.put( "service", new JSONString( service ) );
		obj.put( "checksum", new JSONString( interfaceChecksum ) );
		obj.put( "method", new JSONString( method ) );
		obj.put( "params", params );
		
		return obj;
	}
	
	// calculate the unique key of that request
	private String _getUniqueKey()
	{
		StringBuilder b = new StringBuilder();
		b.append( service );
		b.append( "#" );
		b.append( method );
		if( params != null )
		{
			b.append( "#" );
			b.append( params.toString() );
		}
		
		return b.toString();
	}
}
