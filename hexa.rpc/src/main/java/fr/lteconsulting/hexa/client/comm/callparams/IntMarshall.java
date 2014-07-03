package fr.lteconsulting.hexa.client.comm.callparams;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONValue;

public class IntMarshall implements ICallParamMarshall<Integer>
{
	@Override
	public JSONValue marshall( Integer value )
	{
		return new JSONNumber( (double) value );
	}

}
