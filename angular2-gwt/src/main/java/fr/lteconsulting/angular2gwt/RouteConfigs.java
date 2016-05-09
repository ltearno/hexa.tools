package fr.lteconsulting.angular2gwt;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RouteConfigs {
	RouteConfig[] value();
}
