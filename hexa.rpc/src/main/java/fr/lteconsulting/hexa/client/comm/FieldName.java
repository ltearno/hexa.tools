package fr.lteconsulting.hexa.client.comm;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention( RetentionPolicy.RUNTIME )
public @interface FieldName
{
	public String fieldName();
}
