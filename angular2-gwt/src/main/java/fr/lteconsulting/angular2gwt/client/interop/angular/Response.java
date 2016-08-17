package fr.lteconsulting.angular2gwt.client.interop.angular;

import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = "ng.http", name = "Response" )
public class Response
{
	public final native <T> T json();
}
