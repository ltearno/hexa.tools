package fr.lteconsulting.hexa.client.common;

import com.google.gwt.i18n.client.NumberFormat;

public class HexaDateDisplayFormat2 implements HexaDateDisplayFormat
{
	public String getName()
	{
		return "yyyy-mm-dd";
	}

	private static NumberFormat yearFormat = NumberFormat.getFormat( "0000" );
	private static NumberFormat monthFormat = NumberFormat.getFormat( "00" );
	private static NumberFormat dayFormat = NumberFormat.getFormat( "00" );

	public String format( boolean fInvalid, int year, int month, int date )
	{
		if( fInvalid )
			return "-";
		return yearFormat.format( year + 1900 ) + "-" + monthFormat.format( month + 1 ) + "-" + dayFormat.format( date );
	}

	public HexaDate getHexaDateFromDisplayString( String string )
	{
		try
		{
			return new HexaDate( string );
		}
		catch( Exception e )
		{
			return new HexaDate( "-" );
		}
	}
}
