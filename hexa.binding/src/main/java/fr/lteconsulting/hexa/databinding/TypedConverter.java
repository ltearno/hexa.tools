package fr.lteconsulting.hexa.databinding;

/**
 * Interface for converting a value between two types
 * 
 * @author Arnaud Tournier
 *
 * @param <I> The input type
 * @param <O> The output type
 */
public interface TypedConverter<I, O>
{
	/**
	 * The implementation should have a way to return an instance of O corresponding to the input I
	 * 
	 * @param value The value to be converted.
	 */
	O convert( I value );

	/**
	 * The implementation should have a way to return an instance of I corresponding to the input O
	 * 
	 * @param value The value to be converted back.
	 */
	I convertBack( O value );
}
