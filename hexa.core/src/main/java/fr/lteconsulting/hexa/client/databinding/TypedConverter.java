package fr.lteconsulting.hexa.client.databinding;

public interface TypedConverter<I, O>
{
	O convert( I value );

	I convertBack( O value );
}
