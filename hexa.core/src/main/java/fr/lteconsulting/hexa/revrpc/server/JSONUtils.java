package fr.lteconsulting.hexa.revrpc.server;

public class JSONUtils
{
	public static String toJSONString( Object obj )
	{
		if( obj instanceof JSONArray )
			return ((JSONArray) obj).toJSONString();
		if( obj instanceof JSONObject )
			return ((JSONObject) obj).toJSONString();
		if( obj instanceof String )
			return "\"" + (String) obj + "\"";
		if( obj instanceof Boolean )
			return ((Boolean) obj) ? "true" : "false";
		if( obj instanceof Integer )
			return String.valueOf( (Integer) obj );

		assert false : "Invalid type for serializer : " + obj.getClass().getCanonicalName();

		return "\"invalid entry type\"";
	}
}
