package fr.lteconsulting.angular2gwt.client;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = JsPackage.GLOBAL, name = "Object" )
public class JsObject
{
	@JsOverlay
	public final <T> T get( String propertyName )
	{
		@SuppressWarnings( "unchecked" )
		T result = (T) JsTools.getObjectProperty( this, propertyName );
		return result;
	}

	@JsOverlay
	public final JsObject set( String propertyName, Object value )
	{
		JsTools.setObjectProperty( this, propertyName, value );
		return this;
	}

	public static native JsArray<String> getOwnPropertyNames( JsObject object );
}