package fr.lteconsulting.hexa.client.application.archi;

/**
 * A {@link PlaceTokenizer} is able to transform a string into a Place instance and vice-versa.
 * 
 * It is the role of the application to provide such a Place class through the use of the template
 * parameter.
 *
 * @param <Place> The class used as the Place information, holding a description of the wanted location in the application
 */
public interface PlaceTokenizer<Place>
{
	/**
	 * Transforms a Place instance into a String which will be used in the URL hash
	 */
	String getToken( Place place );

	/**
	 * Transforms a URL hash token into a Place instance describing the wanted location in the application
	 */
	Place getPlace( String token );
}
