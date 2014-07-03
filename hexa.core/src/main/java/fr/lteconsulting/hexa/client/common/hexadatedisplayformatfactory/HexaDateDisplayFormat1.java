package fr.lteconsulting.hexa.client.common.hexadatedisplayformatfactory;

import fr.lteconsulting.hexa.client.common.HexaDate;
import fr.lteconsulting.hexa.client.common.HexaDateDisplayFormat;
import fr.lteconsulting.hexa.client.common.text.DateTimeFormat;
import fr.lteconsulting.hexa.client.common.text.NumberFormat;

class HexaDateDisplayFormat1 implements HexaDateDisplayFormat
{
	public String getName()
	{
		return "dd-mm-yyyy";
	}

	private static NumberFormat yearFormat = NumberFormat.getFormat( "0000" );
	private static NumberFormat monthFormat = NumberFormat.getFormat( "00" );
	private static NumberFormat dayFormat = NumberFormat.getFormat( "00" );

	static private DateTimeFormat DatePickerFormat = DateTimeFormat.getFormat( "dd-MM-yyyy" );

	public String format( boolean fInvalid, int year, int month, int date )
	{
		if( fInvalid )
			return "-";
		return dayFormat.format( date ) + "-" + monthFormat.format( month + 1 ) + "-" + yearFormat.format( year + 1900 );
	}

	public HexaDate getHexaDateFromDisplayString( String string )
	{
		try
		{
			return new HexaDate( DatePickerFormat.parse( string ) );
		}
		catch( Exception e )
		{
			return new HexaDate( "-" );
		}
	}
}
