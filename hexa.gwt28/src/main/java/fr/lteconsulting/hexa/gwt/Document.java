package fr.lteconsulting.hexa.gwt;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = JsPackage.GLOBAL )
public abstract class Document
{
	@JsProperty( namespace = JsPackage.GLOBAL, name = "document" )
	public static native Document get();

	public native <T extends HTMLElement> T createElement( String tagName );

	public native <T extends HTMLElement> T getElementById( String id );

	public native NodeList getElementsByTagName( String name );
}
