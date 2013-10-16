package com.hexa.client.classinfo;

import java.util.HashMap;
import java.util.Set;

import com.google.gwt.core.shared.GWT;
import com.hexa.client.classinfo.internal.ObjectClazz;

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

		if( clazzz.containsKey( clazz.getReflectedClass() ) )
		{
			GWT.log( "Class " + clazz.getClassName() + " already registered..." );
			return;
		}

		clazzz.put( clazz.getReflectedClass(), clazz );
	}

	public static Clazz<?> FindClazz( String name )
	{
		for( Clazz<?> clazz : clazzz.values() )
			if( clazz.getClassName().equals( name ) )
				return clazz;

		return null;
	}

	public static Set<Class<?>> GetRegisteredClazz()
	{
		return clazzz.keySet();
	}

	private static void _ensureMap()
	{
		if( clazzz != null )
			return;

		clazzz = new HashMap<Class<?>, Clazz<?>>();
		clazzz.put( java.lang.Object.class, new ObjectClazz() );
	}
}
