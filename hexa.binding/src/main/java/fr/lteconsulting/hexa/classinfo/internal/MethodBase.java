package fr.lteconsulting.hexa.classinfo.internal;

import java.util.ArrayList;
import java.util.List;

import fr.lteconsulting.hexa.classinfo.Method;

public abstract class MethodBase implements Method
{
	private final Class<?> _returnValueClass;
	private final String _fieldName;
	private final Class<?>[] _parameterTypes;
	private List<Class<?>> _parameterTypesList;

	protected MethodBase( Class<?> clazz, String fieldName, Class<?>[] parameterTypes )
	{
		_returnValueClass = clazz;
		_fieldName = fieldName;
		_parameterTypes = parameterTypes;
	}

	@Override
	public String getName()
	{
		return _fieldName;
	}

	@Override
	public Class<?> getReturnType()
	{
		return _returnValueClass;
	}

	@Override
	public List<Class<?>> getParameterTypes()
	{
		if( _parameterTypesList == null )
		{
			_parameterTypesList = new ArrayList<Class<?>>();
			for( int i = 0; i < _parameterTypes.length; i++ )
				_parameterTypesList.add( _parameterTypes[i] );
		}

		return _parameterTypesList;
	}
}
