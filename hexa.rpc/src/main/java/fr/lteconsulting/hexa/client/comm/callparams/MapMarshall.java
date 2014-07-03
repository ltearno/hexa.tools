package fr.lteconsulting.hexa.client.comm.callparams;

import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

public class MapMarshall<K, V> implements ICallParamMarshall<Map<K, V>>
{
	ICallParamMarshall<V> subMarshall;

	public MapMarshall( ICallParamMarshall<V> subMarshall )
	{
		this.subMarshall = subMarshall;
	}

	public JSONValue marshall( Map<K, V> value )
	{
		JSONObject obj = new JSONObject();

		for( Entry<K, V> entry : value.entrySet() )
			obj.put( entry.getKey().toString(), subMarshall.marshall( entry.getValue() ) );

		return obj;
	}
}
