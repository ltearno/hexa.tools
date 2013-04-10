package com.hexa.client.application.archi;

public interface PlaceTokenizer
{
	String getToken( Place place );

	Place getPlace( String token );
}
