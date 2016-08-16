package fr.lteconsulting.angular2gwt.client.interop.angular;

import fr.lteconsulting.angular2gwt.client.JsObject;
import jsinterop.annotations.JsProperty;

public class HttpProviders
{
	@JsProperty( namespace = "ng.http", name = "HTTP_PROVIDERS" )
	public static native JsObject get();
}
