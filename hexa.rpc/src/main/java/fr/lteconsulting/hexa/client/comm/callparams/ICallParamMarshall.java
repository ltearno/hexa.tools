package fr.lteconsulting.hexa.client.comm.callparams;

import com.google.gwt.json.client.JSONValue;

public interface ICallParamMarshall<T>
{
	JSONValue marshall( T value );
}
