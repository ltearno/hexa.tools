package com.hexa.client.classinfo;

import java.util.List;

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
