package javax.persistence;

import javax.persistence.GenerationType;

public @interface GeneratedValue
{
	GenerationType strategy() default GenerationType.IDENTITY;
}
