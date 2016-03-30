package fr.lteconsulting.hexa.gwt;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = JsPackage.GLOBAL )
public abstract class JSON
{
	public static native String stringify( Object object );

	public static native <T> T parse( String object );
}
