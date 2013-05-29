package com.hexa.server.qpath;

import java.lang.reflect.Field;

import com.hexa.server.qpath.QPathResult.QPathResultRow;

public class AutoDTO<T>
{
	private final Class<T> target;

	private final String tablePrefix;
	private final Field[] fields;
	private final String[] dbFields;

	public AutoDTO( Class<T> target, QPath qpath )
	{
		this.target = target;

		/*
		 * Proxy.newProxyInstance(target.getClassLoader(), new Class<?>[] {target}, new InvocationHandler() {
		 * 
		 * @Override public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable { return null; } });
		 */

		tablePrefix = qpath.pluralize( JavaDBNames.javaToDBName( target.getSimpleName() ) );

		fields = target.getFields();
		dbFields = new String[fields.length];
		for( int i = 0; i < fields.length; i++ )
			dbFields[i] = JavaDBNames.javaToDBName( fields[i].getName() );
	}

	public T convert( QPathResultRow row )
	{
		if( row == null )
			return null;

		try
		{
			T instance = target.newInstance();

			for( int i = 0; i < fields.length; i++ )
			{
				String dbFieldName = tablePrefix + "." + dbFields[i];
				Object fieldValue = row.get( dbFieldName );

				Field field = fields[i];
				Class<?> destinationType = field.getType();

				if( fieldValue == null )
				{
					field.set( instance, null );
					continue;
				}

				Class<?> sourceType = fieldValue.getClass();

				if( destinationType.isEnum() )
				{
					assert sourceType == String.class;

					field.set( instance, field.getType().getMethod( "valueOf", String.class ).invoke( null, fieldValue ) );
				}
				else
				{
					// default serialization : just an assignation
					field.set( instance, fieldValue );
				}
			}

			return instance;
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

		return null;
	}
}
