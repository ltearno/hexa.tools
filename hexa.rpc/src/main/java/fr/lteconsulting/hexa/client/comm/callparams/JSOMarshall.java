package fr.lteconsulting.hexa.client.comm.callparams;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

public class JSOMarshall implements ICallParamMarshall<JavaScriptObject>
{
	public JSONValue marshall( JavaScriptObject value )
	{
		return new JSONObject( value );
	}
}
