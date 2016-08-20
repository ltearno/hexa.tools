package fr.lteconsulting.angular2gwt.client.interop.ng.core;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = "ng.core", name = "EventEmitter" )
public class EventEmitter<T>
{
	@JsMethod
	public final native void emit( T event );
}
