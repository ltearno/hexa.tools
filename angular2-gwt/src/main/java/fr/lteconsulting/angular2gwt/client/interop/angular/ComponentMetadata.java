package fr.lteconsulting.angular2gwt.client.interop.angular;

import fr.lteconsulting.angular2gwt.client.JsArray;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = "ng.core", name = "ComponentMetadata" )
public class ComponentMetadata
{
	public String selector;
	public String template;
	public String templateUrl;
	
	public JsArray<String> styles;
	public JsArray<String> styleUrls;
	public JsArray<String> inputs;
	public JsArray<String> outputs;

	// TODO should be declared as an AngularComponentConstructorFunction (well not sure because services do not always need to be annotated)
	public JsArray<Object> directives;
	// TODO should be declared as an AngularComponentConstructorFunction (well not sure because services do not always need to be annotated)
	public JsArray<Object> providers;
	// TODO should be declared as an AngularComponentConstructorFunction (well not sure because services do not always need to be annotated)
	public JsArray<Object> parameters;
	
}