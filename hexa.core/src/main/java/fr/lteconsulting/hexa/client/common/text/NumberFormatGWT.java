package fr.lteconsulting.hexa.client.common.text;

class NumberFormatGWT extends NumberFormat
{
	private com.google.gwt.i18n.client.NumberFormat internal;

	public NumberFormatGWT( String pattern )
	{
		internal = com.google.gwt.i18n.client.NumberFormat.getFormat( pattern );
	}

	@Override
	public String format( int value )
	{
		return internal.format( value );
	}
}
