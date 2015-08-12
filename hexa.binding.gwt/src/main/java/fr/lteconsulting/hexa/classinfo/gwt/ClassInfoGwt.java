package fr.lteconsulting.hexa.classinfo.gwt;

import java.util.HashMap;
import java.util.Set;

import fr.lteconsulting.hexa.classinfo.Clazz;
import fr.lteconsulting.hexa.classinfo.IClassInfo;
import fr.lteconsulting.hexa.classinfo.internal.ObjectClazz;

public class ClassInfoGwt implements IClassInfo
{
	private HashMap<Class<?>, Clazz<?>> clazzz;

	/**
	 * Obtain a runtime type information on a class.<br/>
	 * <br/>
	 * 
	 * Throws a RuntimeException if the type information provider is not found.
	 * 
	 * @param clazz
	 *            The class object for which type information is required
	 * @return The runtime information interface
	 */
	public <T> Clazz<T> Clazz( Class<T> clazz )
	{
		_ensureMap();

		@SuppressWarnings( "unchecked" )
		Clazz<T> res = (Clazz<T>) clazzz.get( clazz );
		if( res == null )
			throw new IllegalArgumentException( "Class not supported by ClassInfo : " + clazz.getName() );

		return res;
	}

	/**
	 * Register a runtime type information provider
	 */
	public <T> void RegisterClazz( Clazz<T> clazz )
	{
		_ensureMap();

		if( clazzz.containsKey( clazz.getReflectedClass() ) )
			return;

		clazzz.put( clazz.getReflectedClass(), clazz );
	}

	/**
	 * Obtain a runtime type information on a class.
	 * 
	 * @param name
	 *            Name of the class for which type information is required
	 * @return The runtime information interface
	 */
	public Clazz<?> FindClazz( String name )
	{
		if( clazzz == null )
			return null;

		for( Clazz<?> c : clazzz.values() )
			if( c.getClassName().equals( name ) )
				return c;

		return null;
	}

	/**
	 * Obtain a runtime type information on a class.
	 * 
	 * @param clazz
	 *            The class object for which type information is required
	 * @return The runtime information interface
	 */
	public <T> Clazz<T> FindClazz( Class<T> clazz )
	{
		if( clazzz == null )
			return null;

		for( Clazz<?> c : clazzz.values() )
			if( c.getReflectedClass() == clazz )
			{
				@SuppressWarnings( "unchecked" )
				Clazz<T> result = (Clazz<T>) c;
				return result;
			}

		return null;
	}

	/**
	 * Retrieve the set of registered type information providers.
	 */
	public Set<Class<?>> GetRegisteredClazz()
	{
		if( clazzz == null )
			return null;

		return clazzz.keySet();
	}

	private void _ensureMap()
	{
		if( clazzz != null )
			return;

		clazzz = new HashMap<Class<?>, Clazz<?>>();
		clazzz.put( java.lang.Object.class, new ObjectClazz() );
	}
}
