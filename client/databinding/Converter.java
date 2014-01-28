package com.hexa.client.databinding;

public interface Converter
{
	Object convert( Object value );

	Object convertBack( Object value );
}
