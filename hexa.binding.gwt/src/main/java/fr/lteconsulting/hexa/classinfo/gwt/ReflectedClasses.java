package fr.lteconsulting.hexa.classinfo.gwt;

/**
 * Describes a list of classes for which introspection will be enabled
 * at runtime.
 * 
 * This annotation should be used on the register() method of the
 * {@link ClazzBundle} interface.
 * 
 * @author Arnaud
 */
public @interface ReflectedClasses
{
	Class<?>[] classes() default {};
}
