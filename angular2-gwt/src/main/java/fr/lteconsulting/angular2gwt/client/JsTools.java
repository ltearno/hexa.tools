package fr.lteconsulting.angular2gwt.client;

import fr.lteconsulting.angular2gwt.client.interop.PropertyDefinition;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;

public class JsTools {
	@JsMethod(namespace = JsPackage.GLOBAL, name = "setTimeout")
	public static native void setTimeout(JsCallback callback, int timeout);

	@JsMethod(namespace = JsPackage.GLOBAL, name = "setInterval")
	public static native int setInterval(JsCallback callback, int timeout);

	@JsMethod(namespace = JsPackage.GLOBAL, name = "clearInterval")
	public static native void clearInterval(int intervalId);

	@JsMethod(namespace = "console", name = "log")
	public static native void log(String message);

	@JsMethod(namespace = "lteconsulting", name = "defineProperty")
	public static native void defineProperty( Object object, String name, PropertyDefinition propertyDefinition );
	
	@JsMethod(namespace = "lteconsulting", name = "propertyInObject")
	public static native boolean propertyInObject( String property, Object object );
	
	@JsMethod(namespace = "lteconsulting", name = "convertObject")
	public static native <T> T convertObject( String prototypeName, Object template );

	public static native <T> T get(Object o, int index)
	/*-{
		return o[index] || null;
	}-*/;

	public static native <T> T get(Object o, String propertyName)
	/*-{
		return o[propertyName] || null;
	}-*/;

	public static native <T> void set(Object o, int index, T value)
	/*-{
		o[index] = value;
	}-*/;

	public static native <T> void set(Object o, String propertyName, T value)
	/*-{
		o[propertyName] = value;
	}-*/;
}
