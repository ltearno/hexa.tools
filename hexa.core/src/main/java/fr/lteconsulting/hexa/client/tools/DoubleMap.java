package fr.lteconsulting.hexa.client.tools;

import java.util.HashMap;
import java.util.Map;

public class DoubleMap<U, V>
{
	private Map<U, V> uv = new HashMap<>();
	private Map<V, U> vu = new HashMap<>();

	public V get( U key )
	{
		return uv.get( key );
	}

	public U getReverse( V value )
	{
		return vu.get( value );
	}

	public void put( U key, V value )
	{
		uv.put( key, value );
		vu.put( value, key );
	}
}
