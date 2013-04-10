package com.hexa.client.common;

public class HexaDateDisplayFormatList
{
	private static HexaDateDisplayFormat[] s_formats = new HexaDateDisplayFormat[]{
			new HexaDateDisplayFormat1(),
			new HexaDateDisplayFormat2(),
			new HexaDateDisplayFormat3()
	};
	
	private static String[] s_possibleFormats = null;
	public static String[] getPossibleFormats()
	{
		if( s_possibleFormats != null )
			return s_possibleFormats;
		
		int nbFormats = s_formats.length;
		
		s_possibleFormats = new String[nbFormats];
		for( int i=0; i<nbFormats; i++ )
			s_possibleFormats[i] = s_formats[i].getName();
		
		return s_possibleFormats;
	}
	
	public static HexaDateDisplayFormat getFormat( String name )
	{
		int nbFormats = s_formats.length;
		
		for( int i=0; i<nbFormats; i++ )
		{
			if( s_formats[i].getName().equals( name ) )
				return s_formats[i];
		}
		
		// return the default one if nothing matching is found
		return s_formats[0];
	}
}
