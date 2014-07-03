package fr.lteconsulting.hexa.client.common;

public interface HexaDateDisplayFormat
{
	// returns the name of the formatting
	public String getName();

	public String format( boolean fInvalid, int year, int month, int date );

	public HexaDate getHexaDateFromDisplayString( String string );
}
