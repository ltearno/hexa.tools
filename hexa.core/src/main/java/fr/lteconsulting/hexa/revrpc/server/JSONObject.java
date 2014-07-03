package fr.lteconsulting.hexa.revrpc.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class JSONObject
{
	Map<String, Object> map = new HashMap<String, Object>();

	public void put( String key, Object value )
	{
		map.put( key, value );
	}

	@Override
	public String toString()
	{
		return toJSONString();
	}

	public String toJSONString()
	{
		StringBuilder b = new StringBuilder();

		b.append( "{" );

		boolean addComa = false;
		for( Entry<String, Object> e : map.entrySet() )
		{
			if( addComa )
				b.append( ", " );
			addComa = true;

			b.append( "\"" + e.getKey() + "\" : " );
			b.append( JSONUtils.toJSONString( e.getValue() ) );
		}

		b.append( "}" );

		return b.toString();
	}
}