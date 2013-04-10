package com.hexa.client.application.archi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.hexa.client.tools.HexaTools;

public class PlaceController implements ValueChangeHandler<String>
{
	ActivityMng activityMng = null;
	PlaceTokenizer placeTokenizer = null;
	
	private Place currentPlace = null;
	
	public void init( ActivityMng activityMng, PlaceTokenizer placeTokenizer )
	{
		this.activityMng = activityMng;
		this.placeTokenizer = placeTokenizer;
		History.addValueChangeHandler( this );
	}
	
	public void goTo( Place place )
	{
		if( activityMng.mayStop() )
		{
			String token = placeTokenizer.getToken( place );
			GWT.log( "PlaceController.goTo( " + token + ")" );
			History.newItem( token );
		}
	}
	
	public void changeLocale( String locale )
	{
		if( currentPlace == null )
		{
			return;
		}
		
		String token = placeTokenizer.getToken( currentPlace );
		
		// TODO Is this really the right way get our information ? Let's check sometime...
		Map<String, List<String>> curParams = new HashMap<String, List<String>>( Location.getParameterMap() );
		ArrayList<String> value = new ArrayList<String>();
		value.add( locale );
		curParams.put( "locale", value );
		String queryString = "?";
		boolean fAddAnd = false;
		for( Entry<String,List<String>> e : curParams.entrySet() )
		{
			if( fAddAnd )
				queryString += "&";
			fAddAnd = true;
			queryString += URL.encodeQueryString( e.getKey() ) + "=" + URL.encodeQueryString( HexaTools.arrayToString(e.getValue()) );
		}
		
		String url = Location.getProtocol() + "//" + Location.getHost() + Location.getPort() + Location.getPath() + queryString/*Location.getQueryString()*/ + "#" + token;
		
		Window.Location.replace( url );
	}
	
	public String getPlaceUrl( Place place )
	{
		String token = placeTokenizer.getToken( place );
		// TODO Is this really the right way get our information ? Let's check sometime...
		return Location.getProtocol() + "//" + Location.getHost() + Location.getPort() + Location.getPath() + Location.getQueryString() + "#" + token;
	}
	
	@Override
	public void onValueChange(ValueChangeEvent<String> event)
	{
		GWT.log( "History : " + event.getValue() );
		currentPlace = placeTokenizer.getPlace( event.getValue() );
		if( currentPlace == null )
		{
			GWT.log( "NULL PLACE FOR PLACE '" + event.getValue() + "'" );
			History.newItem( "" );
			return;
		}
		
		activityMng.setPlace( currentPlace );
	}
}
