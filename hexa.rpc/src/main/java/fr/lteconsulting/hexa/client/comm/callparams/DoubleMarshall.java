package fr.lteconsulting.hexa.client.comm.callparams;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONValue;

public class DoubleMarshall implements ICallParamMarshall<Double>
{
	@Override
	public JSONValue marshall( Double value )
	{
		return new JSONNumber( value );
	}
}
