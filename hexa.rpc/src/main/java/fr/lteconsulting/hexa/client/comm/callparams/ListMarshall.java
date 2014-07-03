package fr.lteconsulting.hexa.client.comm.callparams;

import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONValue;

public class ListMarshall<T> implements ICallParamMarshall<List<T>>
{
	ICallParamMarshall<T> subMarshall;

	public ListMarshall( ICallParamMarshall<T> subMarshall )
	{
		this.subMarshall = subMarshall;
	}

	@Override
	public JSONValue marshall( List<T> value )
	{
		JSONArray array = new JSONArray();
		for( int i = 0; i < value.size(); i++ )
			array.set( i, subMarshall.marshall( value.get( i ) ) );

		return array;
	}
}
