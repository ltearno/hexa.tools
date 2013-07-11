package com.hexa.client.databinding;

import com.hexa.client.classinfo.ClazzUtils;

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
	
	public static Converter findConverter( Class<?> from, Class<?> to )
	{
		from = ClazzUtils.getBoxedType( from );
		to = ClazzUtils.getBoxedType( to );
		
		if( from==String.class && to==Integer.class )
			return StringToInteger;
		if( from==Integer.class && to==String.class )
			return IntegerToString;
		
		return null;
	}
}
