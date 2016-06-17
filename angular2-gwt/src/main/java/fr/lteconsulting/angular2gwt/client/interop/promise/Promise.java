package fr.lteconsulting.angular2gwt.client.interop.promise;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = JsPackage.GLOBAL, name = "Promise" )
public class Promise<V>
{
	@JsConstructor
	public Promise( Executor<V> executor )
	{
		// keep the constructor empty because in reality it is implemented in the javascript world
	}

	@JsMethod
	public native Promise<V> then( Resolution<V> resolution, Rejection rejection );
}
