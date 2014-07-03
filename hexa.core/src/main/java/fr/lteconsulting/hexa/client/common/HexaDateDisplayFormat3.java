package fr.lteconsulting.hexa.client.common;

import com.google.gwt.i18n.client.DateTimeFormat;

public class HexaDateDisplayFormat3 implements HexaDateDisplayFormat
{
	DateTimeFormat fmt = DateTimeFormat.getFormat( DateTimeFormat.PredefinedFormat.DATE_FULL );

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
	}
}
