package fr.lteconsulting.hexa.client.application.archi;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class ActivityMng<Place>
{
	AcceptsOneWidget appWidget = null;
	Activity<Place> currentActivity = null;
	ActivityMapper<Place> activityMapper = null;
	PlaceController<Place> placeController = null;
	ActivityFilter<Place> activityFilter = null;

	public void init( AcceptsOneWidget appWidget, ActivityMapper<Place> activityMapper, PlaceController<Place> placeController, ActivityFilter<Place> activityFilter )
	{
		this.placeController = placeController;
		this.appWidget = appWidget;
		this.activityMapper = activityMapper;
		this.activityFilter = activityFilter;
	}

	public boolean mayStop()
	{
		if( currentActivity == null )
			return true;

		String res = currentActivity.mayStop();
		if( res != null )
		{
			Window.alert( res );
			return false;
		}

		return true;
	}

	public void setPlace( Place place )
	{
		Activity<Place> activity = activityMapper.getActivity( place );

		// filter the place by application
		Activity<Place> res = activityFilter.canEnter( activity, place );
		if( res == null )
			currentActivity = activity;
		else
			currentActivity = res;

		currentActivity.start( appWidget, placeController );
	}
}
