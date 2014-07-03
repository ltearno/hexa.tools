package fr.lteconsulting.hexa.client.comm;

public @interface Cache
{
	boolean inv() default false;

	boolean useCache() default true;

	String paramTable() default "";
}
