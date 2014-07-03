package fr.lteconsulting.hexa.client.application.archi;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class ActivityMng
{
	AcceptsOneWidget appWidget = null;
	Activity currentActivity = null;
	ActivityMapper activityMapper = null;
	PlaceController placeController = null;
	ActivityFilter activityFilter = null;

	public void init( AcceptsOneWidget appWidget, ActivityMapper activityMapper, PlaceController placeController, ActivityFilter activityFilter )
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
		Activity activity = activityMapper.getActivity( place );

		// filter the place by application
		Activity res = activityFilter.canEnter( activity, place );
		if( res == null )
			currentActivity = activity;
		else
			currentActivity = res;

		currentActivity.start( appWidget, placeController );
	}
}
