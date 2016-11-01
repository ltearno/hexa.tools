package fr.lteconsulting.hexa.client.application.archi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;

import fr.lteconsulting.hexa.client.tools.HexaTools;

/**
 * Place controller
 * 
 * <p>
 * Associate the url's hash with Place objects and give them to the
 * activity manager.
 * 
 * <p>
 * When the url's hash part changes, a string token corresponding to
 * the hash part is emitted and received by the Place controller.
 * 
 * <p>
 * The controller then ask the tokenizer to transform the token
 * into a Place object.
 * 
 * <p>
 * The Place object is then given to the Activity Manager to start
 * the correct activity in its view.
 * 
 * <p>
 * When an activity requires to go to another place, it calls the
 * 'goTo' method which will ask the tokenizer to transform the required place
 * into a string which is set as the url's hash part. This in turn triggers
 * the previously mentionned mechanism.
 */
public class PlaceController<Place> implements ValueChangeHandler<String>
{
	ActivityManager<Place> activityMng = null;
	PlaceTokenizer<Place> placeTokenizer = null;

	private Place currentPlace = null;

	public void init( ActivityManager<Place> activityMng, PlaceTokenizer<Place> placeTokenizer )
	{
		this.activityMng = activityMng;
		this.placeTokenizer = placeTokenizer;
		History.addValueChangeHandler( this );
	}

	public void goTo( Place place )
	{
		goTo( place, true );
	}

	public void goTo( Place place, boolean fireEvent )
	{
		if( !fireEvent || activityMng.mayStop() )
		{
			String token = placeTokenizer.getToken( place );
			History.newItem( token, fireEvent );
		}
	}

	public void refreshCurrentPlace()
	{
		History.fireCurrentHistoryState();
	}

	public void changeLocale( String locale )
	{
		if( currentPlace == null )
			return;

		String token = placeTokenizer.getToken( currentPlace );

		Map<String, List<String>> curParams = new HashMap<String, List<String>>( Location.getParameterMap() );
		ArrayList<String> value = new ArrayList<String>();
		value.add( locale );
		curParams.put( "locale", value );
		String queryString = "?";
		boolean fAddAnd = false;
		for( Entry<String, List<String>> e : curParams.entrySet() )
		{
			if( fAddAnd )
				queryString += "&";
			fAddAnd = true;
			queryString += URL.encodeQueryString( e.getKey() ) + "=" + URL.encodeQueryString( HexaTools.arrayToString( e.getValue() ) );
		}

		String url = Location.getProtocol() + "//" + Location.getHost() + Location.getPath() + queryString + "#" + token;

		Window.Location.replace( url );
	}

	public String getPlaceUrl( Place place )
	{
		String token = placeTokenizer.getToken( place );
		return Location.getProtocol() + "//" + Location.getHost() + Location.getPort() + Location.getPath() + Location.getQueryString() + "#" + token;
	}

	@Override
	public void onValueChange( ValueChangeEvent<String> event )
	{
		currentPlace = placeTokenizer.getPlace( event.getValue() );
		if( currentPlace == null )
		{
			History.newItem( "" );
			return;
		}

		activityMng.setPlace( currentPlace, this );
	}
}
