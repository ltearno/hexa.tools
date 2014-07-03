package fr.lteconsulting.hexa.client.common;

import java.util.Date;

import fr.lteconsulting.hexa.client.common.text.NumberFormat;

public class HexaTime
{
	private static NumberFormat format = NumberFormat.getFormat( "00" );

	int hours = 0;
	int minutes = 0;
	int seconds = 0;

	@SuppressWarnings( "deprecation" )
	public HexaTime()
	{
		Date d = new Date();
		this.hours = d.getHours();
		this.minutes = d.getMinutes();
		this.seconds = d.getSeconds();
	}

	public HexaTime( String string )
	{
		// assert( string.length() >= 5 );
		if( string.length() < 5 )
		{
			// GWT.log( "Invalid string " + string +
			// " for HexaTime initialization" );
			return;
		}

		hours = Integer.parseInt( string.substring( 0, 2 ) );
		minutes = Integer.parseInt( string.substring( 3, 5 ) );
		seconds = Integer.parseInt( string.substring( 6, 8 ) );
	}

	public HexaTime( int hours, int minutes, int seconds )
	{
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	public String toString()
	{
		assert false; // prevents automatic conversion to string
		return getDisplayString();
	}

	public String getDisplayString()
	{
		// TODO should be parametrized...
		return format.format( hours ) + ":" + format.format( minutes );
		// return getString();
	}

	public String getString()
	{
		return format.format( hours ) + ":" + format.format( minutes ) + ":" + format.format( seconds );
	}

	public int getHours()
	{
		return hours;
	}

	public int getMinutes()
	{
		return minutes;
	}

	public int getSeconds()
	{
		return seconds;
	}

	public void add( HexaTime op )
	{
		seconds += op.seconds;
		while( seconds >= 60 )
		{
			minutes++;
			seconds -= 60;
		}

		minutes += op.minutes;
		while( minutes >= 60 )
		{
			hours++;
			minutes -= 60;
		}

		hours += op.hours;
	}

	public int compareTo( HexaTime other )
	{
		int d = hours - other.hours;
		if( d != 0 )
			return d;
		d = minutes - other.minutes;
		if( d != 0 )
			return d;
		d = seconds - other.seconds;
		if( d != 0 )
			return d;
		return 0;
	}
}
