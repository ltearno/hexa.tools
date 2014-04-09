package com.hexa.client.application.archi;

import com.hexa.client.tools.Action1;

public interface PlaceTokenizer
{
	String getToken( Place place );

	void getPlaceAsync( String token, Action1<Place> created );
}
