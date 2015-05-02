package fr.lteconsulting.hexa.databinding;

/**
 * An abstract class to implement a converter that does only the conversion forward. If it is 
 * called for a backward conversion, it will throw a RuntimeException.
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 *
 */
public abstract class OneWayConverter implements Converter
{
	@Override
	public final Object convertBack( Object value )
	{
		throw new RuntimeException( "OneWayConverter cannot convertBack !" );
	}
}
