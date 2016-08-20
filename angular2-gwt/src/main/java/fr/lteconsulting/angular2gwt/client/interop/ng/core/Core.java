package fr.lteconsulting.angular2gwt.client.interop.ng.core;

import jsinterop.annotations.JsMethod;

public class Core
{
	@JsMethod( namespace = "ng.core", name = "provide" )
	public static native <T> T provide( Class<?> provider, Object options );

	@JsMethod( namespace = "ng.core" )
	public static native void enableProdMode();
}
