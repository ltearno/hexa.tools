package fr.lteconsulting.hexa.client.application.archi;

import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Represents a task done by the user through an UI.
 */
public interface Activity<Place>
{
	/**
	 * Requires to start the activity.
	 * 
	 * <p>
	 * The activity can add any widget inside the container and may use the placeController
	 * to ask to go to other application places.
	 */
	void start( AcceptsOneWidget container, PlaceController<Place> placeController );

	String mayStop();
}
