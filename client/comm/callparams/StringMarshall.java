package com.hexa.client.comm.callparams;

import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class StringMarshall implements ICallParamMarshall<String>
{
	@Override
	public JSONValue marshall( String value )
	{
		return new JSONString( value );
	}
}
