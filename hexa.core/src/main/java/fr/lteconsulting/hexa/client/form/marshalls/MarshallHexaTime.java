package fr.lteconsulting.hexa.client.form.marshalls;

import fr.lteconsulting.hexa.client.common.HexaTime;
import fr.lteconsulting.hexa.client.form.FormManager.Marshall;

import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class MarshallHexaTime implements Marshall<HexaTime>
{
	private static MarshallHexaTime INST = null;

	public static MarshallHexaTime get()
	{
		if( INST == null )
			INST = new MarshallHexaTime();
		return INST;
	}

	public HexaTime get( JSONValue value )
	{
		return new HexaTime( value.isString().stringValue() );
	}

	public JSONValue get( HexaTime object )
	{
		return new JSONString( object.getString() );
	}

}
