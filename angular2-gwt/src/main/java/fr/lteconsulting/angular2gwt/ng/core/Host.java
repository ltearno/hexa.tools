package fr.lteconsulting.angular2gwt.ng.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * For @Host on component constructor parameters
 */
@Retention( RetentionPolicy.RUNTIME )
@Target(ElementType.PARAMETER)
public @interface Host {
}
