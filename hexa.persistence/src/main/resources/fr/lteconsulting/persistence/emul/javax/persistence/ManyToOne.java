package javax.persistence;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;

public @interface ManyToOne
{
	FetchType fetch() default FetchType.LAZY;
	CascadeType[] cascade() default {};
}
