package fr.lteconsulting.angular2gwt;

public @interface NgModule {
	Class<?>[] imports() default {};
	
	Class<?>[] exports() default {};

	Class<?>[] declarations() default {};

	Class<?>[] providers() default {};
	
	Class<?>[] bootstrap() default {};
}
