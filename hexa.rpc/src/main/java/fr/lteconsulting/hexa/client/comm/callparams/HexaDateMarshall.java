package fr.lteconsulting.hexa.client.comm.callparams;

import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import fr.lteconsulting.hexa.client.common.HexaDate;

public class HexaDateMarshall implements ICallParamMarshall<HexaDate>
{
	public JSONValue marshall( HexaDate value )
	{
		if( value == null )
			return null;
		return new JSONString( value.getString() );
	}

}
