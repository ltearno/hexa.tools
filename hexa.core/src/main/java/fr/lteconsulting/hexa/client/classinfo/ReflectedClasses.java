package fr.lteconsulting.hexa.client.classinfo;

public @interface ReflectedClasses
{
	Class<?>[] classes() default {};
}
