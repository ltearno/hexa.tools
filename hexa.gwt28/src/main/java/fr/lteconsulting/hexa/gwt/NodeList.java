package fr.lteconsulting.hexa.gwt;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = JsPackage.GLOBAL )
public abstract class NodeList
{
	@JsProperty
	public native int getLength();

	public native HTMLElement item( int index );
}
