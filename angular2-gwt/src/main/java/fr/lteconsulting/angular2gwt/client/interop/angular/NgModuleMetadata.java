package fr.lteconsulting.angular2gwt.client.interop.angular;

import fr.lteconsulting.angular2gwt.client.JsArray;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = "ng.core", name = "NgModuleMetadata" )
public class NgModuleMetadata {
	public JsArray<Object> _providers;
	public JsArray<Object> declarations;
	public JsArray<Object> imports;
	public JsArray<Object> exports;
	public JsArray<Object> bootstrap;
}
