package fr.lteconsulting.angular2gwt.client.interop.angular;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = "ng.core", name = "Directive" )
public class Directive implements AngularAnnotation
{
	@JsConstructor
	public Directive( DirectiveMetadata metadata )
	{
	}
}
