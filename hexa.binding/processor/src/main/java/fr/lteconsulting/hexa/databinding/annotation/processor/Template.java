package fr.lteconsulting.hexa.databinding.annotation.processor;

import java.io.InputStream;
import java.util.Scanner;

public class Template
{
	private String value;

	public static Template fromResource( String path )
	{
		Template result = new Template();

		result.value = readResource( path );

		return result;
	}

	public static Template fromResource( String path, int index )
	{
		Template result = new Template();

		result.value = readResource( path, index );

		return result;
	}

	public Template replace( String target, String replacement )
	{
		value = value.replace( target, replacement );
		return this;
	}

	@Override
	public String toString()
	{
		return value;
	}

	private static String readResource( String path )
	{
		InputStream is = Template.class.getClassLoader().getResourceAsStream( path );

		Scanner s = new Scanner( is );
		s.useDelimiter( "\\A" );

		String result = s.hasNext() ? s.next() : "";

		s.close();
		return result;
	}

	private static String readResource( String path, int index )
	{
		InputStream is = Template.class.getClassLoader().getResourceAsStream( path );

		if( is == null )
			throw new RuntimeException( "Not found resource " + path );

		Scanner s = new Scanner( is );
		s.useDelimiter( "------" );

		String result;
		int i = -1;
		do
		{
			if( !s.hasNext() )
			{
				result = "";
				break;
			}

			result = s.next();
			i++;
		}
		while( i < index );

		s.close();

		return result;
	}
}
