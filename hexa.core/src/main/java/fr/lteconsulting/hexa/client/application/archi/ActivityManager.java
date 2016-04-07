package fr.lteconsulting.hexa.client.application.archi;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Associates a view, a place controller and an activity filter.
 * 
 * <p>
 * The activity manager receives Places through its 'setPlace' method and
 * executes required checks before initializing the new activity.
 * 
 * <p>
 * The activity manager is responsible of a part of the screen materialized
 * as the 'viewPlaceholder' field.
 */
public class ActivityManager<Place>
{
	AcceptsOneWidget viewPlaceholder = null;
	Activity<Place> currentActivity = null;
	ActivityMapper<Place> activityMapper = null;
	ActivityFilter<Place> activityFilter = null;

	public void init( AcceptsOneWidget viewPlaceholder, ActivityMapper<Place> activityMapper )
	{
		init( viewPlaceholder, activityMapper, null );
	}

	public void init( AcceptsOneWidget viewPlaceholder, ActivityMapper<Place> activityMapper, ActivityFilter<Place> activityFilter )
	{
		this.viewPlaceholder = viewPlaceholder;
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

	public void setPlace( Place place, PlaceController<Place> placeController )
	{
		Activity<Place> activity = activityMapper.getActivityForPlace( place );

		Activity<Place> activityToStart = null;
		if( activityFilter != null )
			activityToStart = activityFilter.canEnter( activity, place );

		if( activityToStart == null )
			activityToStart = activity;

		currentActivity = activityToStart;
		currentActivity.start( viewPlaceholder, placeController );
	}
}
