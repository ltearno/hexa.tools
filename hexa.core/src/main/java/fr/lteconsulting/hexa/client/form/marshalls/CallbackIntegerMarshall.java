package fr.lteconsulting.hexa.client.form.marshalls;

import java.util.HashMap;

import fr.lteconsulting.hexa.client.form.FormManager;
import fr.lteconsulting.hexa.client.interfaces.IAsyncCallback;

import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.DOM;

public class CallbackIntegerMarshall implements FormManager.Marshall<IAsyncCallback<Integer>>
{
	String prefix = DOM.createUniqueId();

	HashMap<String, IAsyncCallback<Integer>> map = new HashMap<String, IAsyncCallback<Integer>>();

	public JSONValue get( IAsyncCallback<Integer> object )
	{
		String key = prefix + "_" + DOM.createUniqueId();
		map.put( key, object );

		return new JSONString( key );
	}

	public IAsyncCallback<Integer> get( JSONValue value )
	{
		return map.get( value.isString().stringValue() );
	}
}
