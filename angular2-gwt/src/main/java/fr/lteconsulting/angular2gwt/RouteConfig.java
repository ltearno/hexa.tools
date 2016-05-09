package fr.lteconsulting.angular2gwt;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RouteConfigs.class)
public @interface RouteConfig {
	String path();

	String name();

	Class<?> component();

	boolean useAsDefault() default false;
}
