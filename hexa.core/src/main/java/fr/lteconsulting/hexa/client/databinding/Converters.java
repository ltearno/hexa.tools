package fr.lteconsulting.hexa.client.databinding;

import fr.lteconsulting.hexa.client.classinfo.ClazzUtils;

/**
 * A collection of implementations of standard converters.
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 *
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
		from = ClazzUtils.getBoxedType( from );
		to = ClazzUtils.getBoxedType( to );

		if( from == String.class && to == Integer.class )
			return StringToInteger;
		if( from == Integer.class && to == String.class )
			return IntegerToString;

		return null;
	}
}
