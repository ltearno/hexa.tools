package fr.lteconsulting.hexa.persistence.client.legacy.persistence;

public @interface WithEntityClasses
{
	Class<?>[] classes() default {};
}
