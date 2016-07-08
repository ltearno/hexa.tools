package fr.lteconsulting.angular2gwt.client.interop.angular;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = "ng.core", name = "Component" )
public class Component implements AngularAnnotation
{
	@JsConstructor
	public Component( ComponentMetadata metadata )
	{
	}
}