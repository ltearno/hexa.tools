package fr.lteconsulting.hexa.databinding.properties;

import java.util.HashMap;

public class DynamicPropertyBag
{
	private HashMap<String, Object> map;

	void set( String propertyName, Object value )
	{
		if( map == null )
			map = new HashMap<>();

		map.put( propertyName, value );
	}

	Object get( String propertyName )
	{
		if( map == null )
			return null;

		return map.get( propertyName );
	}
	
	boolean contains( String propertyName )
	{
		if( map == null )
			return false;
		
		return map.containsKey( propertyName );
	}
}