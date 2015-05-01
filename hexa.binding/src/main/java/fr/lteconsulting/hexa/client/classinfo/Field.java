package fr.lteconsulting.hexa.client.classinfo;

public interface Field
{
	String getName();

	Class<?> getType();

	void setValue( Object object, Object value );

	<OUT> OUT getValue( Object object );

	void copyValueTo( Object source, Object destination );

	int getModifier();
}
