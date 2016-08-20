package fr.lteconsulting.angular2gwt;

public @interface ViewChild {
	Class<?> component() default Void.class;

	String selector() default "";
}
