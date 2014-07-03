package fr.lteconsulting.hexa.client.application.archi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import fr.lteconsulting.hexa.client.tools.MD5;
import com.google.gwt.user.client.Window;

public class KeyValuePairTokenizer
{
	HashMap<String, String> values = new HashMap<String, String>();

	// encoding part

	public void init()
	{
		values.clear();
	}

	public void add( String name, String value )
	{
		values.put( name, value );
	}

	public void add( String name, int value )
	{
		add( name, String.valueOf( value ) );
	}

	public void add( String name, double value )
	{
		add( name, String.valueOf( (int) (value * 100.0) ) );
	}

	public String getToken()
	{
		StringBuilder b = new StringBuilder();
		boolean fAddAnd = false;

		values.put( "z", chk() );

		for( Entry<String, String> entry : values.entrySet() )
		{
			if( fAddAnd )
				b.append( "&" );
			fAddAnd = true;

			b.append( entry.getKey() );
			b.append( "=" );
			b.append( entry.getValue() );
		}

		return b.toString();
	}

	// decoding part

	public void initToken( String token )
	{
		values.clear();

		if( token == null )
			return;

		String[] parts = token.split( "&" );
		for( int i = 0; i < parts.length; i++ )
		{
			String[] sub = parts[i].split( "=" );
			if( sub.length < 1 )
				continue;
			String name = sub[0];
			String value = "";
			if( sub.length >= 2 )
				value = sub[1];

			values.put( name, value );
		}

		String chksum = values.get( "z" );
		if( !chksum.equals( chk() ) )
		{
			Window.alert( "Invalid link checksum " + chksum + " / " + chk() );
			values.clear();
		}
	}

	public String getTokenValue( String name )
	{
		return values.get( name );
	}

	public Integer getTokenValueInt( String name )
	{
		Integer value = null;

		String res = getTokenValue( name );
		if( res == null )
			return value;

		try
		{
			value = Integer.parseInt( res );
		}
		catch( Exception e )
		{
		}

		return value;
	}

	public Double getTokenValueDouble( String name )
	{
		Double value = null;

		String res = getTokenValue( name );
		if( res == null )
			return value;

		try
		{
			value = ((double) Integer.parseInt( res )) / 100.0;
		}
		catch( Exception e )
		{
		}

		return value;
	}

	// security

	private String chk()
	{
		List<Entry<String, String>> entries = new ArrayList<Entry<String, String>>();
		entries.addAll( values.entrySet() );
		Collections.sort( entries, new Comparator<Entry<String, String>>()
		{
			public int compare( Entry<String, String> arg0, Entry<String, String> arg1 )
			{
				return arg0.getKey().compareTo( arg1.getKey() );
			}
		} );

		StringBuilder b = new StringBuilder();
		for( Entry<String, String> e : entries )
		{
			if( e.getKey().equals( "z" ) )
				continue;

			b.append( e.getKey() );
			b.append( "HEXASYS" );
			b.append( e.getValue() );
			b.append( "SECURITY" );
		}

		return MD5.md5( b.toString() );
	}
}