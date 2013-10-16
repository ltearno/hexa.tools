package com.hexa.client.classinfo.internal;

import com.hexa.client.classinfo.Field;

public abstract class FieldBase implements Field
{
	private final Class<?> _class;
	private final String _fieldName;
	private int _modifier;

	protected FieldBase( Class<?> clazz, String fieldName, int modifier )
	{
		_class = clazz;
		_fieldName = fieldName;
		_modifier = modifier;
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

	@Override
	public int getModifier()
	{
		return _modifier;
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