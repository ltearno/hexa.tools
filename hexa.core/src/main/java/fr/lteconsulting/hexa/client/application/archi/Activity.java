package fr.lteconsulting.hexa.client.application.archi;

import com.google.gwt.user.client.ui.AcceptsOneWidget;

public interface Activity<Place>
{
	void start( AcceptsOneWidget container, PlaceController<Place> placeController );

	String mayStop();
}
