package fr.lteconsulting.hexa.client.comm.callparams;

import java.util.Set;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONValue;

public class SetMarshall<T> implements ICallParamMarshall<Set<T>>
{
	ICallParamMarshall<T> subMarshall;

	public SetMarshall( ICallParamMarshall<T> subMarshall )
	{
		this.subMarshall = subMarshall;
	}

	@Override
	public JSONValue marshall( Set<T> value )
	{
		JSONArray array = new JSONArray();
		int i = 0;
		for( T v : value )
			array.set( i++, subMarshall.marshall( v ) );

		return array;
	}

}
