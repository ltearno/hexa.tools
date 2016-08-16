package fr.lteconsulting.angular2gwt.client.interop.angular;

import fr.lteconsulting.angular2gwt.client.JsObject;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = "ng.http", name = "Response" )
public class Response
{
	public final native JsObject json();
}
