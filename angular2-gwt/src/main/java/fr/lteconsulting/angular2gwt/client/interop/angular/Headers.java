package fr.lteconsulting.angular2gwt.client.interop.angular;

import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "ng.http", name = "Headers")
public class Headers {
	public native void append(String name, String value);
}
