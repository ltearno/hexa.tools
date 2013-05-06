package com.hexa.client.classinfo;

import java.util.HashMap;

public class ClassInfo
{
	private static HashMap<Class<?>, Clazz<?>> clazzz;

	@SuppressWarnings( "unchecked" )
	public static <T> Clazz<T> Clazz( Class<T> clazz )
	{
		_ensureMap();

		Clazz<T> res = (Clazz<T>) clazzz.get( clazz );
		if( res == null )
			throw new IllegalArgumentException( "Class not supported by ClassInfo : " + clazz.getName() );

		return res;
	}

	public static <T> void RegisterClazz( Clazz<T> clazz )
	{
		_ensureMap();

		clazzz.put( clazz.getReflectedClass(), clazz );
	}

	public static Clazz<?> FindClazz( String name )
	{
		for( Clazz<?> clazz : clazzz.values() )
			if( clazz.getClassName().equals( name ) )
				return clazz;

		return null;
	}

	private static void _ensureMap()
	{
		if( clazzz != null )
			return;

		clazzz = new HashMap<Class<?>, Clazz<?>>();
	}
}
