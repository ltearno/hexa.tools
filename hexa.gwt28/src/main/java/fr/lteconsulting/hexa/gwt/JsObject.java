package fr.lteconsulting.hexa.gwt;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = JsPackage.GLOBAL, name = "Object" )
public class JsObject
{
	@JsOverlay
	public final JsObject set( String name, Object value )
	{
		JsTools.setImpl( this, name, value );
		return this;
	}

	@JsOverlay
	public final <T> T get( String name )
	{
		return JsTools.getImpl( this, name );
	}
}
