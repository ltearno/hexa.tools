package fr.lteconsulting.hexa.gwt;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = JsPackage.GLOBAL )
public abstract class Window {
	@JsProperty(namespace = JsPackage.GLOBAL, name = "window")
	public static native Window get();

	public native void alert(String message);
}
