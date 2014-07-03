package fr.lteconsulting.hexa.revrpc.server;

import java.util.ArrayList;

public class JSONArray
{
	ArrayList<Object> map = new ArrayList<Object>();

	public void set( int index, Object value )
	{
		if( index < map.size() )
		{
			map.set( index, value );
		}
		else
		{
			assert index == map.size();
			map.add( value );
		}
	}

	@Override
	public String toString()
	{
		return toJSONString();
	}

	public String toJSONString()
	{
		StringBuilder b = new StringBuilder();

		b.append( "[" );

		boolean addComa = false;
		for( int i = 0; i < map.size(); i++ )
		{
			if( addComa )
				b.append( ", " );
			addComa = true;

			b.append( JSONUtils.toJSONString( map.get( i ) ) );
		}

		b.append( "]" );

		return b.toString();
	}
}