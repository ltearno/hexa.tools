package fr.lteconsulting.angular2gwt.client.interop.angular;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = "ng.core", name = "SimpleChange" )
public class SimpleChange
{
	@JsProperty
	Object previousValue;

	@JsProperty
	Object currentValue;
}
