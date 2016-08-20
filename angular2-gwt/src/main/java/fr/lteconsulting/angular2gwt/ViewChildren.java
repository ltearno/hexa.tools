package fr.lteconsulting.angular2gwt;

public @interface ViewChildren {
	Class<?> component() default Void.class;

	String selector() default "";
}
