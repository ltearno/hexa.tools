package fr.lteconsulting.angular2gwt;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention( RetentionPolicy.RUNTIME )
@Repeatable( Hosts.class )
public @interface Host
{
	String event();

	String action();
}
