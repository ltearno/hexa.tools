package fr.lteconsulting.hexa.client.databinding;

public abstract class OneWayConverter implements Converter
{
	@Override
	public Object convertBack( Object value )
	{
		throw new RuntimeException( "OneWayConverter cannot convertBack !" );
	}
}
