package fr.lteconsulting.hexa.revrpc.client;

import com.google.gwt.json.client.JSONObject;

public class CallDeserializerUtil
{
	public static String getParameterString( JSONObject json, int index )
	{
		if( json == null )
			return null;

		JSONObject prm = getParam( json, index, "java.lang.String" );

		return prm.get( "value" ).isString().stringValue();
	}

	public static Boolean getParameterboolean( JSONObject json, int index )
	{
		if( json == null )
			return null;

		JSONObject prm = getParam( json, index, "java.lang.Boolean" );

		return prm.get( "value" ).isBoolean().booleanValue();
	}

	public static Integer getParameterint( JSONObject json, int index )
	{
		return getParameterInteger( json, index );
	}

	public static Integer getParameterInteger( JSONObject json, int index )
	{
		if( json == null )
			return null;

		JSONObject prm = getParam( json, index, "java.lang.Integer" );

		return (int) prm.get( "value" ).isNumber().doubleValue();
	}

	public static JSONObject getParam( JSONObject json, int index, String type )
	{
		JSONObject prm = json.get( "args" ).isArray().get( index ).isObject();

		assert prm.get( "type" ).isString().stringValue().equals( type );

		return prm;
	}
}
