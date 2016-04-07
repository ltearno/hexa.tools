package fr.lteconsulting.hexa.client.application.archi;

/**
 * Does the correspondence between Places and Activities.
 * 
 * <p>
 * It is the application responsibility to provide an implementation
 * of this interface. Through this the application will be able
 * to customize the user's flow into the application
 */
public interface ActivityMapper<Place>
{
	Activity<Place> getActivityForPlace( Place place );
}
