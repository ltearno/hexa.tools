package fr.lteconsulting.angular2gwt.client.interop.angular;

import fr.lteconsulting.angular2gwt.client.JsObject;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = "ng.http", name = "Headers" )
public class Headers
{
	@JsConstructor
	public Headers( JsObject headers )
	{
	}
}
