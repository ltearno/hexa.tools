package fr.lteconsulting.angular2gwt.client;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;

public class JsTools
{
	@JsMethod( namespace = JsPackage.GLOBAL, name = "setTimeout" )
	public static native void setTimeout( JsCallback callback, int timeout );

	@JsMethod( namespace = JsPackage.GLOBAL, name = "setInterval" )
	public static native int setInterval( JsCallback callback, int timeout );

	@JsMethod( namespace = JsPackage.GLOBAL, name = "clearInterval" )
	public static native void clearInterval( int intervalId );

	@JsMethod( namespace = "console", name = "log" )
	public static native void log( String message );

	static native <T> T get( Object o, int index )
	/*-{
		return o[index] || null;
	}-*/;
	
	static native <T> void set( Object o, int index, T value )
	/*-{
		o[index] = value;
	}-*/;
}
