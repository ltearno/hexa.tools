package fr.lteconsulting.hexa.client.common.text;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

class DateTimeFormatGWT extends fr.lteconsulting.hexa.client.common.text.DateTimeFormat
{
	private DateTimeFormat internal;

	public DateTimeFormatGWT( String pattern )
	{
		internal = DateTimeFormat.getFormat( pattern );
	}

	@Override
	public String format( Date value )
	{
		return internal.format( value );
	}

	@Override
	public Date parse( String value )
	{
		return internal.parse( value );
	}
}
