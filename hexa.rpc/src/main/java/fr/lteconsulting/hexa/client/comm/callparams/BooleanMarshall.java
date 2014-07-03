package fr.lteconsulting.hexa.client.comm.callparams;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONValue;

public class BooleanMarshall implements ICallParamMarshall<Boolean>
{
	public JSONValue marshall( Boolean value )
	{
		return new JSONNumber( (double) (value ? 1 : 0) );
		// return new JSONString( value ? "1" : "0" );
	}
}
