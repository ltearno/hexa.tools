package com.hexa.client.common;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;

public class HexaDateDisplayFormat1 implements HexaDateDisplayFormat
{
	public String getName()
	{
		return "dd-mm-yyyy";
	}

	private static NumberFormat yearFormat = NumberFormat.getFormat( "0000" );
	private static NumberFormat monthFormat = NumberFormat.getFormat( "00" );
	private static NumberFormat dayFormat = NumberFormat.getFormat( "00" );

	static private DateTimeFormat HexaDateFormat = DateTimeFormat.getFormat( "yyyy-MM-dd" );
	static private DateTimeFormat DatePickerFormat = DateTimeFormat.getFormat( "dd-MM-yyyy" );

	public String format( boolean fInvalid, int year, int month, int date )
	{
		if( fInvalid )
			return "-";
		return dayFormat.format( date ) + "-" + monthFormat.format( month + 1 ) + "-" + yearFormat.format( year + 1900 );

		// other formatting
		/*
		 * if( fInvalid ) return "0000-00-00"; return yearFormat.format(year+1900) + "-" + monthFormat.format(month+1) + "-" + dayFormat.format(date);
		 */
	}

	public String getJQDatepickerFormat()
	{
		return "dd-mm-yy";

		// other formatting
		// return "yy-mm-dd";
	}

	public HexaDate getHexaDateFromDisplayString( String string )
	{
		try
		{
			Date date = DatePickerFormat.parse( string );
			return new HexaDate( HexaDateFormat.format( date ) );
		}
		catch( Exception e )
		{
			return new HexaDate( "-" );
		}

		// other formatting
		// return new HexaDate( string );
	}
}
