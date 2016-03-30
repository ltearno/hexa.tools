package fr.lteconsulting.hexa.gwt;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = JsPackage.GLOBAL, name = "Object" )
public class JsTypedObject<K, V>
{
	@JsOverlay
	public final JsTypedObject<K, V> set( K name, V value )
	{
		JsTools.setImpl( this, name, value );
		return this;
	}

	@JsOverlay
	public final V get( K name )
	{
		return JsTools.getImpl( this, name );
	}
}
