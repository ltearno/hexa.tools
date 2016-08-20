package fr.lteconsulting.angular2gwt.ng.core;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention( RetentionPolicy.RUNTIME )
@Repeatable( HostsBinding.class )
public @interface HostBinding
{
	String event();

	String action();
}
