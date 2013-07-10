package com.hexa.client.classinfo.internal;

import java.util.ArrayList;
import java.util.List;

import com.hexa.client.classinfo.Clazz;
import com.hexa.client.classinfo.Field;
import com.hexa.client.classinfo.Method;

public abstract class ClazzBase<T> implements Clazz<T>
{
	protected Class<T> _reflectedClass;
	protected String _className;
	protected List<Field<T>> _fields;
	protected List<Method> _methods;

	@SuppressWarnings( "unused" )
	private ClazzBase()
	{
	}

	protected ClazzBase( Class<T> reflectedClass, String className )
	{
		_reflectedClass = reflectedClass;
		_className = className;
	}

	@Override
	public String getClassName()
	{
		return _className;
	}

	@Override
	public Class<T> getReflectedClass()
	{
		return _reflectedClass;
	}

	@Override
	public List<Field<T>> getFields()
	{
		if( _fields == null )
		{
			_fields = new ArrayList<Field<T>>();
			_addFields();
		}

		return _fields;
	}

	@Override
	public Field<T> getField( String fieldName )
	{
		for( Field<T> field : getFields() )
			if( field.getName().equals( fieldName ) )
				return field;
		return null;
	}

	@Override
	public List<Method> getMethods()
	{
		if( _methods == null )
		{
			_methods = new ArrayList<Method>();
			_addMethods();
		}

		return _methods;
	}

	@Override
	public Method getMethod( String methodName )
	{
		for( Method method : getMethods() )
			if( method.getName().equals( methodName ) )
				return method;
		return null;
	}

	// To implement :
	// @Override
	// public T NEW()
	// {
	// return new T();
	// }

	// To implement :
	// _fields.add( new something_extending_FieldBase )
	protected abstract void _addFields();

	protected abstract void _addMethods();
}