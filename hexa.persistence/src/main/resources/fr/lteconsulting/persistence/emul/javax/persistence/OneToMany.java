package javax.persistence;

public @interface OneToMany
{
	String mappedBy();
	boolean orphanRemoval() default false;
}
