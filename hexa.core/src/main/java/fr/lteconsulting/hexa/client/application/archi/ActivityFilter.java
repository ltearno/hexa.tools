package fr.lteconsulting.hexa.client.application.archi;

/**
 * Filters activities before they are started
 */
public interface ActivityFilter<Place>
{
	/**
	 * Filters the transition to a new activity.
	 * 
	 * The filter has the opportunity to return another activity, which
	 * will be started instead of the original one.
	 * 
	 * If the methods returns null, the original activity will be used
	 */
	Activity<Place> canEnter( Activity<Place> activity, Place place );
}
