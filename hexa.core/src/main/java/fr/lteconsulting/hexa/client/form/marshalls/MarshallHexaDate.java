package fr.lteconsulting.hexa.client.form.marshalls;

import fr.lteconsulting.hexa.client.common.HexaDate;
import fr.lteconsulting.hexa.client.form.FormManager.Marshall;

import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class MarshallHexaDate implements Marshall<HexaDate>
{
	private static MarshallHexaDate INST = null;

	public static MarshallHexaDate get()
	{
		if( INST == null )
			INST = new MarshallHexaDate();
		return INST;
	}

	public HexaDate get( JSONValue value )
	{
		return new HexaDate( value.isString().stringValue() );
	}

	public JSONValue get( HexaDate object )
	{
		return new JSONString( object.getString() );
	}
}
