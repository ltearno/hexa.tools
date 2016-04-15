package fr.lteconsulting.hexa.client.form.marshalls;

import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

import fr.lteconsulting.hexa.client.form.FormManager.Marshall;

public class MarshallString implements Marshall<String>
{
	private static MarshallString INSTANCE = null;

	public static MarshallString get()
	{
		if( INSTANCE == null )
			INSTANCE = new MarshallString();

		return INSTANCE;
	}

	private MarshallString()
	{
	}

	public String get( JSONValue value )
	{
		return value.isString().stringValue();
	}

	public JSONValue get( String object )
	{
		return new JSONString( object );
	}
}
