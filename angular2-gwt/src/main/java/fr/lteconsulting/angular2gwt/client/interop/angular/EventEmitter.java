package fr.lteconsulting.angular2gwt.client.interop.angular;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = "ng.core", name = "EventEmitter" )
public class EventEmitter<T>
{
	@JsMethod
	public final native void emit( T event );
}
