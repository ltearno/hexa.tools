package fr.lteconsulting.angular2gwt;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention( RetentionPolicy.RUNTIME )
public @interface Hosts
{
	Host[] value();
}
