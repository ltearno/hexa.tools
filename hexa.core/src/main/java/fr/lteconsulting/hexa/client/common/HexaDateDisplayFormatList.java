package fr.lteconsulting.hexa.client.common;

import fr.lteconsulting.hexa.client.common.hexadatedisplayformatfactory.HexaDateDisplayFormatFactory;
import fr.lteconsulting.hexa.client.common.hexadatedisplayformatfactory.HexaDateDisplayFormatFactory.Format;

public class HexaDateDisplayFormatList
{
	private static HexaDateDisplayFormat[] s_formats = new HexaDateDisplayFormat[] { HexaDateDisplayFormatFactory.get( Format.FORMAT_1 ), HexaDateDisplayFormatFactory.get( Format.FORMAT_2 ), HexaDateDisplayFormatFactory.get( Format.FORMAT_3 ) };

	private static String[] s_possibleFormats = null;

	public static String[] getPossibleFormats()
	{
		if( s_possibleFormats != null )
			return s_possibleFormats;

		int nbFormats = s_formats.length;

		s_possibleFormats = new String[nbFormats];
		for( int i = 0; i < nbFormats; i++ )
			s_possibleFormats[i] = s_formats[i].getName();

		return s_possibleFormats;
	}

	public static HexaDateDisplayFormat getFormat( String name )
	{
		int nbFormats = s_formats.length;

		for( int i = 0; i < nbFormats; i++ )
		{
			if( s_formats[i].getName().equals( name ) )
				return s_formats[i];
		}

		// return the default one if nothing matching is found
		return s_formats[0];
	}

	public static HexaDateDisplayFormat getFormat( Format format )
	{
		switch( format )
		{
			case FORMAT_1:
				return s_formats[0];
			case FORMAT_2:
				return s_formats[1];
			case FORMAT_3:
				return s_formats[2];
		}

		return null;
	}
}
