package fr.lteconsulting.hexa.client.tools;

public class StringUtils
{
	public static boolean equals( String a, String b )
	{
		if( a==null && b==null )
			return true;
		if( a==null || b==null )
			return false;
		return a.equals( b );
	}

	public static String lowerFirstLetter(String s) {
		return s.substring(0, 1).toLowerCase() + s.substring(1);
	}

	public static String capitalizeFirstLetter(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
}
