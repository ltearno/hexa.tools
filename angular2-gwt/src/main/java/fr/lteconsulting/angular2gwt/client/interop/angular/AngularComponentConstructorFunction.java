package fr.lteconsulting.angular2gwt.client.interop.angular;

import fr.lteconsulting.angular2gwt.client.JsArray;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = JsPackage.GLOBAL, name = "Object" )
public class AngularComponentConstructorFunction
{
	@JsProperty
	public JsArray<AngularAnnotation> annotations;

	// TODO should be declared as an AngularComponentConstructorFunction (well not sure because services do not always need to be annotated)
	@JsProperty
	public JsArray<Object> parameters;
	
	@JsProperty( name="prototype" )
	public Object proto;
}