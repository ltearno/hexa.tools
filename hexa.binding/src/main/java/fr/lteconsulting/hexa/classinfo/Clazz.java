package fr.lteconsulting.hexa.classinfo;

import java.util.List;

/**
 * Runtime type information interface about a class
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 *
 * @param <T> The class for which runtime type information is provided
 */
public interface Clazz<T>
{
	String getClassName();

	Class<T> getReflectedClass();

	Clazz<? super T> getSuperclass();

	List<Field> getAllFields();

	Field getAllField( String fieldName );

	List<Field> getFields();

	Field getField( String fieldName );

	List<Field> getDeclaredFields();

	Field getDeclaredField( String fieldName );

	List<Method> getMethods();

	Method getMethod( String methodName );

	T NEW();
}
