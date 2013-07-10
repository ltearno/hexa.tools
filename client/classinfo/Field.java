package com.hexa.client.classinfo;

public interface Field<T>
{
	String getName();

	Class<?> getType();

	void setValue( Object object, Object value );

	<OUT> OUT getValue( Object object );

	void copyValueTo( T source, T destination );
}
