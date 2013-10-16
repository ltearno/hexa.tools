package com.hexa.client.classinfo;

import java.util.List;

public interface Method
{
	String getName();

	Class<?> getReturnType();

	List<Class<?>> getParameterTypes();

	Object invoke( Object target, Object... parameters );
}
