package com.hexa.client.common;

public class HexaDateTime
{
	HexaDate date = null;
	HexaTime time = null;

	public HexaDateTime()
	{
		date = new HexaDate();
		time = new HexaTime();
	}

	public HexaDateTime( String string )
	{
		// assert( string.length() == 19 ) : ("Invalid string length for : '" +
		// string + "'");
		if( string.length() != 19 )
		{
			// GWT.log( "Invalid string " + string +
			// " for HexaDateTime initialization" );
			// date = new HexaDate( "" );
			// time = new HexaTime( "" );
			return;
		}

		date = new HexaDate( string.substring( 0, 10 ) );
		time = new HexaTime( string.substring( 11 ) );
	}

	public HexaDateTime( HexaDate date )
	{
		this( date, new HexaTime( 0, 0, 0 ) );
	}

	public HexaDateTime( HexaDate date, HexaTime time )
	{
		this.date = date;
		this.time = time;
	}

	public static HexaDateTime now()
	{
		return new HexaDateTime();
	}

	public HexaDate getHexaDate()
	{
		return date;
	}

	public HexaTime getHexaTime()
	{
		return time;
	}

	public void setTime( HexaTime time )
	{
		this.time = time;
	}

	public String getString()
	{
		if( date == null && time == null )
			return "";
		return (date != null ? date.getString() : "") + " " + (time != null ? time.getString() : "");
	}

	public String getDisplayString()
	{
		if( date == null && time == null )
			return "";
		return (date != null ? date.getDisplayString() : "") + " at " + (time != null ? time.getDisplayString() : "");
	}

	public int compareTo( HexaDateTime other )
	{
		return getString().compareTo( other.getString() );
	}
}
