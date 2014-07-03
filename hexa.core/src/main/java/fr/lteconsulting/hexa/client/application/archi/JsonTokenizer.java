package fr.lteconsulting.hexa.client.application.archi;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import fr.lteconsulting.hexa.client.comm.GenericJSO;

public class JsonTokenizer
{
	JSONObject values = null;

	// encoding part

	public void init()
	{
		values = new JSONObject();
	}

	public void add( String name, String value )
	{
		values.put( name, new JSONString( value ) );
	}

	public void add( String name, int value )
	{
		values.put( name, new JSONNumber( value ) );
	}

	public void add( String name, GenericJSO jso )
	{
		values.put( name, new JSONObject( jso.cast() ) );
	}

	public String getToken()
	{
		return values.toString();
	}

	// decoding part

	public void initToken( String token )
	{
		try
		{
			values = JSONParser.parseStrict( token ).isObject();
		}
		catch( Exception e )
		{
			values = new JSONObject();
		}

	}

	public String getTokenValue( String name )
	{
		JSONValue value = values.get( name );
		if( value == null )
			return "";

		JSONString string = value.isString();
		if( string == null )
			return "";

		String res = string.stringValue();
		if( res == null )
			return "";

		return res;
	}

	public Integer getTokenValueInt( String name )
	{
		JSONValue value = values.get( name );
		if( value == null )
			return null;

		JSONNumber number = value.isNumber();
		if( number == null )
			return null;

		return (int) (number.doubleValue());
	}

	public GenericJSO getTokenValueJSO( String name )
	{
		JSONValue value = values.get( name );
		if( value == null )
			return null;

		JSONObject obj = value.isObject();
		if( obj == null )
			return null;

		return obj.getJavaScriptObject().cast();
	}
}
