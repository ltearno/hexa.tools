package fr.lteconsulting.angular2gwt.client.interop.angular;

import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "ng", name = "platformBrowserDynamic")
public class PlatformBrowserDynamic {
	public native void bootstrapModule(Object module);
}
