package com.hexa.client.classinfo.internal;

import com.hexa.client.classinfo.Field;

public abstract class FieldBase<T> implements Field<T>
{
	private final Class<?> _class;
	private final String _fieldName;

	protected FieldBase( Class<?> clazz, String fieldName )
	{
		_class = clazz;
		_fieldName = fieldName;
	}

	@Override
	public String getName()
	{
		return _fieldName;
	}

	@Override
	public Class<?> getType()
	{
		return _class;
	}

	// @Override
	// public void copyValueTo(T source, T destination) {
	// if (_class == int.class)
	// setValue(destination, getValueInt(source));
	// else
	// setValue(destination, getValue(source));
	// }

	// To implement :
	// @Override
	// public native void setValue( T object, Object value )
	// /*-{
	// object@com.hexa.client.classinfo.internal.T::FIELD = value;
	// }-*/;
}