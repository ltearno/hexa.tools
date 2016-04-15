package fr.lteconsulting.hexa.client.form.marshalls;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONValue;

import fr.lteconsulting.hexa.client.form.FormManager.Marshall;

public class MarshallInteger implements Marshall<Integer>
{
	private static MarshallInteger INST = null;

	public static MarshallInteger get()
	{
		if( INST == null )
			INST = new MarshallInteger();
		return INST;
	}

	public Integer get( JSONValue value )
	{
		return (int) value.isNumber().doubleValue();
	}

	public JSONValue get( Integer object )
	{
		return new JSONNumber( object );
	}
}
