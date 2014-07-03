package fr.lteconsulting.hexa.client.classinfo;

public class ClazzUtils
{
	public static Class<?> getBoxedType( Class<?> c )
	{
		if( c == int.class )
			return Integer.class;
		if( c == char.class )
			return Character.class;
		if( c == double.class )
			return Double.class;
		if( c == float.class )
			return Float.class;
		return c;
	}
}
