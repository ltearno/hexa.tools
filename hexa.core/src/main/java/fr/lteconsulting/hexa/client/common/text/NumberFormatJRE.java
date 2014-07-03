package fr.lteconsulting.hexa.client.common.text;

import java.text.DecimalFormat;

class NumberFormatJRE extends NumberFormat
{
	DecimalFormat internal;

	public NumberFormatJRE( String pattern )
	{
		internal = new DecimalFormat( pattern );
	}

	@Override
	public String format( int value )
	{
		return internal.format( value );
	}
}
