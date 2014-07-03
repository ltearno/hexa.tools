package fr.lteconsulting.hexa.client.common.hexadatedisplayformatfactory;

import fr.lteconsulting.hexa.client.common.HexaDate;
import fr.lteconsulting.hexa.client.common.HexaDateDisplayFormat;
import fr.lteconsulting.hexa.client.common.text.NumberFormat;

class HexaDateDisplayFormat2 implements HexaDateDisplayFormat
{
	public String getName()
	{
		return "yyyy-mm-dd";
	}

	private static NumberFormat yearFormatGwt;
	private static NumberFormat monthFormatGwt;
	private static NumberFormat dayFormatGwt;

	public String format( boolean fInvalid, int year, int month, int date )
	{
		if( fInvalid )
			return "-";

		ensureGWTFormat();
		return yearFormatGwt.format( year + 1900 ) + "-" + monthFormatGwt.format( month + 1 ) + "-" + dayFormatGwt.format( date );
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

	private void ensureGWTFormat()
	{
		if( yearFormatGwt == null )
			yearFormatGwt = NumberFormat.getFormat( "0000" );
		if( monthFormatGwt == null )
			monthFormatGwt = NumberFormat.getFormat( "00" );
		if( dayFormatGwt == null )
			dayFormatGwt = NumberFormat.getFormat( "00" );
	}
}
