package fr.lteconsulting.hexa.revrpc.client;

import com.google.gwt.json.client.JSONObject;

public interface ICallDeserializer<T>
{
	void registerImplementation( T implementation );

	void newCall( JSONObject json );
}
