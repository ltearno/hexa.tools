package fr.lteconsulting.hexa.revrpc.client;

import java.util.HashMap;

import com.google.gwt.json.client.JSONObject;

public class RevRPCServices
{
	private HashMap<String, ICallDeserializer<?>> deserializers = new HashMap<String, ICallDeserializer<?>>();

	public <T> void registerService( String serviceName, Class<T> serviceInterface, ICallDeserializer<T> callDeserializer )
	{
		deserializers.put( getId( serviceName, serviceInterface.getName() ), callDeserializer );
	}

	public void newServiceCall( JSONObject json )
	{
		assert json != null;

		String serviceName = json.get( "service" ).isString().stringValue();
		String interfaceName = json.get( "interface" ).isString().stringValue();
		JSONObject call = json.get( "value" ).isObject();

		ICallDeserializer<?> deserializer = deserializers.get( getId( serviceName, interfaceName ) );
		assert deserializer != null;

		deserializer.newCall( call );
	}

	private String getId( String serviceName, String interfaceName )
	{
		return serviceName + "-" + interfaceName;
	}
}
