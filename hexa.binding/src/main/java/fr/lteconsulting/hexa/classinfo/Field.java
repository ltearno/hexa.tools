package fr.lteconsulting.hexa.classinfo;

/**
 * Represents a Java field
 * 
 * @author Arnaud
 *
 */
public interface Field
{
	String getName();

	Class<?> getType();

	void setValue( Object object, Object value );

	<OUT> OUT getValue( Object object );

	void copyValueTo( Object source, Object destination );

	int getModifier();
}
