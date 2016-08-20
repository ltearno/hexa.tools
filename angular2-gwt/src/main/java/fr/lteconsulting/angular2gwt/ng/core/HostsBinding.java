package fr.lteconsulting.angular2gwt.ng.core;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention( RetentionPolicy.RUNTIME )
public @interface HostsBinding
{
	HostBinding[] value();
}
