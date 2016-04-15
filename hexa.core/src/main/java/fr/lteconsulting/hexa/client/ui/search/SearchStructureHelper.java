package fr.lteconsulting.hexa.client.ui.search;

import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class SearchStructureHelper
{
	public static JSONValue And( JSONValue... operands )
	{
		JSONObject obj = new JSONObject();
		obj.put( "op", new JSONString( "and" ) );

		JSONArray ops = new JSONArray();
		for( int i = 0; i < operands.length; i++ )
			ops.set( i, operands[i] );
		obj.put( "ops", ops );

		return obj;
	}

	public static JSONValue And( List<JSONValue> operands )
	{
		JSONObject obj = new JSONObject();
		obj.put( "op", new JSONString( "and" ) );

		JSONArray ops = new JSONArray();
		int i = 0;
		for( JSONValue op : operands )
			ops.set( i++, op );
		obj.put( "ops", ops );

		return obj;
	}

	public static JSONValue Field( String fieldName, String operator, JSONValue value )
	{
		JSONObject obj = new JSONObject();

		obj.put( "field", new JSONString( fieldName ) );
		obj.put( "op", new JSONString( operator ) );
		obj.put( "value", value );

		return obj;
	}

	public static JSONValue PointingTable( String pointingTableName, JSONValue subSearch )
	{
		JSONObject obj = new JSONObject();

		obj.put( "op", new JSONString( "<-" ) );
		obj.put( "pointing_table", new JSONString( pointingTableName ) );
		obj.put( "sub", subSearch );

		return obj;
	}
}
