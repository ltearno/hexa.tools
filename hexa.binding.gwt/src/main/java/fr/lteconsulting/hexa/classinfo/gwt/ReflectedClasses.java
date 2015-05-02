package fr.lteconsulting.hexa.classinfo.gwt;

public @interface ReflectedClasses
{
	Class<?>[] classes() default {};
}
