package fr.lteconsulting.hexa.server.qpath;

public class JavaDBNames
{
	public static String javaToDBName( String className )
	{
		if( className.endsWith( "DTO" ) )
			className = className.substring( 0, className.length() - 3 );

		int length = className.length();

		StringBuilder sb = new StringBuilder();

		for( int i = 0; i < length; i++ )
		{
			char c = className.charAt( i );
			if( Character.isLowerCase( c ) || Character.isDigit( c ) )
				sb.append( c );
			else if( i == 0 )
				sb.append( Character.toLowerCase( c ) );
			else
				sb.append( "_" ).append( Character.toLowerCase( c ) );
		}

		return sb.toString();
	}
}
