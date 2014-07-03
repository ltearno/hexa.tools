package fr.lteconsulting.hexa.client.common.text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class DateTimeFormatJRE extends DateTimeFormat
{
	SimpleDateFormat internal;

	public DateTimeFormatJRE( String pattern )
	{
		internal = new SimpleDateFormat( pattern );
	}

	@Override
	public String format( Date value )
	{
		return internal.format( value );
	}

	@Override
	public Date parse( String value )
	{
		try
		{
			return internal.parse( value );
		}
		catch( ParseException e )
		{
			return null;
		}
	}
}
