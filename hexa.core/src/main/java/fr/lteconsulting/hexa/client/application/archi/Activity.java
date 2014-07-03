package fr.lteconsulting.hexa.client.application.archi;

import com.google.gwt.user.client.ui.AcceptsOneWidget;

public interface Activity
{
	// an init or constructor method should take specific Place and
	// ClientFactory in parameter...
	void start( AcceptsOneWidget container, PlaceController placeController );

	String mayStop();
}
