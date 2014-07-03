package fr.lteconsulting.hexa.client.common.text;

import java.util.Date;
import java.util.HashMap;

import com.google.gwt.core.shared.GWT;

public abstract class DateTimeFormat
{
	private static HashMap<String, DateTimeFormat> instances = new HashMap<String, DateTimeFormat>();

	public static DateTimeFormat getFormat( String pattern )
	{
		DateTimeFormat fmt = instances.get( pattern );
		if( fmt == null )
		{
			if( GWT.isClient() )
				fmt = new DateTimeFormatGWT( pattern );
			else
				fmt = new DateTimeFormatJRE( pattern );
		}

		return fmt;
	}

	public abstract String format( Date value );

	public abstract Date parse( String value );
}
