package fr.lteconsulting.hexa.client.comm.callparams;

import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import fr.lteconsulting.hexa.client.interfaces.ITable;

public class ITableMarshall implements ICallParamMarshall<ITable>
{
	@Override
	public JSONValue marshall( ITable value )
	{
		return new JSONString( value.getName() );
	}
}
