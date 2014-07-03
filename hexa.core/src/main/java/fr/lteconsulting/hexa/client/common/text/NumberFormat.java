package fr.lteconsulting.hexa.client.common.text;

import java.util.HashMap;

import com.google.gwt.core.shared.GWT;

public abstract class NumberFormat
{
	private static HashMap<String, NumberFormat> instances = new HashMap<String, NumberFormat>();

	public static NumberFormat getFormat( String pattern )
	{
		NumberFormat fmt = instances.get( pattern );
		if( fmt == null )
		{
			if( GWT.isClient() )
				fmt = new NumberFormatGWT( pattern );
			else
				fmt = new NumberFormatJRE( pattern );
		}

		return fmt;
	}

	public abstract String format( int value );
}
