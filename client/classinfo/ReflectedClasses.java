package com.hexa.client.classinfo;

public @interface ReflectedClasses
{
	Class<?>[] classes() default {};
}
