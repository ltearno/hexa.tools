package fr.lteconsulting.hexa.client.form.marshalls;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONValue;

import fr.lteconsulting.hexa.client.form.FormManager.Marshall;

public class MarshallBoolean implements Marshall<Boolean>
{
	private static MarshallBoolean INST = null;

	public static MarshallBoolean get()
	{
		if( INST == null )
			INST = new MarshallBoolean();
		return INST;
	}

	public Boolean get( JSONValue value )
	{
		return (int) value.isNumber().doubleValue() > 0 ? true : false;
	}

	public JSONValue get( Boolean object )
	{
		return new JSONNumber( object ? 1 : 0 );
	}
}
