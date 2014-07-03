package fr.lteconsulting.hexa.client.comm.callparams;

import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class StringMarshall implements ICallParamMarshall<String>
{
	@Override
	public JSONValue marshall( String value )
	{
		if( value == null )
			return null;
		return new JSONString( value );
	}
}
