package fr.lteconsulting.hexa.classinfo;

import java.util.Set;

/**
 * Frontal singleton entry point providing the Reflection system API
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 *
 */
public class ClassInfo
{
	private static IClassInfo impl = ClassInfoProvider.get();
	
	/**
	 * Obtain a runtime type information on a class.<br><br>
	 * 
	 * Throws a RuntimeException if the type information provider is not found.
	 * 
	 * @param clazz The class object for which type information is required
	 * @return The runtime information interface
	 */
	public static <T> Clazz<T> Clazz( Class<T> clazz )
	{
		return impl.Clazz( clazz );
	}

	/**
	 * Register a runtime type information provider
	 * 
	 * @param clazz
	 */
	public static <T> void RegisterClazz( Clazz<T> clazz )
	{
		impl.RegisterClazz( clazz );
	}

	/**
	 * Obtain a runtime type information on a class.
	 * 
	 * @param name Name of the class for which type information is required
	 * @return The runtime information interface
	 */
	public static Clazz<?> FindClazz( String name )
	{
		return impl.FindClazz( name );
	}

	/**
	 * Obtain a runtime type information on a class.
	 * 
	 * @param clazz The class object for which type information is required
	 * @return The runtime information interface
	 */
	public static <T> Clazz<T> FindClazz( Class<T> clazz )
	{
		return impl.FindClazz( clazz );
	}

	/**
	 * Retrieve the set of registered type information providers
	 * @return
	 */
	public static Set<Class<?>> GetRegisteredClazz()
	{
		return impl.GetRegisteredClazz();
	}
}
