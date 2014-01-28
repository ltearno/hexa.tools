package com.hexa.client.common.hexadatedisplayformatfactory;

import com.hexa.client.common.HexaDate;
import com.hexa.client.common.HexaDateDisplayFormat;
import com.hexa.client.common.text.DateTimeFormat;

public class HexaDateDisplayFormat3 implements HexaDateDisplayFormat
{
	DateTimeFormat fmt = DateTimeFormat.getFormat( "MM d, yy" );// DateTimeFormat.PredefinedFormat.DATE_LONG
																// );

	public String getName()
	{
		return "Month day, year";
	}

	public String format( boolean fInvalid, int year, int month, int date )
	{
		if( fInvalid )
			return "-";

		HexaDate hdate = new HexaDate( year, month, date );
		String res = fmt.format( hdate.getJavaDate() );

		return res;
	}

	public String getJQDatepickerFormat()
	{
		return "MM d, yy";
	}

	public HexaDate getHexaDateFromDisplayString( String string )
	{
		try
		{
			HexaDate res = new HexaDate( fmt.parse( string ) );
			return res;
		}
		catch( Exception e )
		{
		}

		return new HexaDate( "-" );

		/*
		 * JavaScriptObject obj = DateJS.create( string ); HexaDate date = new
		 * HexaDate( DateJS.toConformity( obj ) );
		 * 
		 * GWT.log( "HexaDateDisplayFormat3 parse " + string + " => " +
		 * date.getString() );
		 * 
		 * return date;
		 */
	}
}
