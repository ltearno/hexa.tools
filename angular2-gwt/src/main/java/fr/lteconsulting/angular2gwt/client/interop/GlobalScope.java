package fr.lteconsulting.angular2gwt.client.interop;

import fr.lteconsulting.angular2gwt.client.JsCallback;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;

public class GlobalScope
{
	@JsMethod( namespace = JsPackage.GLOBAL )
	public static native void requestAnimationFrame( JsCallback callback );
}
