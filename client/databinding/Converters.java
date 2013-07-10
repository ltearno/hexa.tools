package com.hexa.client.databinding;

public enum Converters implements Converter
{
	StringToInteger
	{
		@Override
		public Object convert( Object value )
		{
			if( value == null )
				return null;
			
			try
			{
				return Integer.parseInt( ((String)value) );
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
	}
	;
}
