package fr.lteconsulting.hexa.client.application.archi;

public interface PlaceTokenizer<Place>
{
	String getToken( Place place );

	Place getPlace( String token );
}
