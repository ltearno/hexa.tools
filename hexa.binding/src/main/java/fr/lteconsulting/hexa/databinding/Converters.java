package fr.lteconsulting.hexa.databinding;

/**
 * A collection of implementations of standard converters.
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 */
public enum Converters implements Converter
{
	/**
	 * A String to Integer converter. Quietly catches conversion exceptions
	 */
	StringToInteger
	{
		@Override
		public Object convert( Object value )
		{
			if( value == null )
				return null;

			try
			{
				return Integer.parseInt( ((String) value) );
			}
			catch( Exception e )
			{
				return null;
			}
		}

		@Override
		public Object convertBack( Object value )
		{
			if( value == null )
				return null;

			return "" + value;
		}
	},

	/**
	 * An Integer to String converter. Quietly catches conversion exceptions
	 */
	IntegerToString
	{
		@Override
		public Object convert( Object value )
		{
			return StringToInteger.convertBack( value );
		}

		@Override
		public Object convertBack( Object value )
		{
			return StringToInteger.convert( value );
		}
	};

	/**
	 * Dynamically finds the appropriate converter to use between two objects
	 * of different classes.<br/>
	 * Returns <code>null</code> if no appropriate converter is found.
	 * 
	 * @param from The input class type
	 * @param to The output class type
	 * @return
	 */
	public static Converter findConverter( Class<?> from, Class<?> to )
	{
		from = getBoxedType( from );
		to = getBoxedType( to );

		if( from == String.class && to == Integer.class )
			return StringToInteger;
		if( from == Integer.class && to == String.class )
			return IntegerToString;

		return null;
	}
	
	private static Class<?> getBoxedType( Class<?> c )
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
