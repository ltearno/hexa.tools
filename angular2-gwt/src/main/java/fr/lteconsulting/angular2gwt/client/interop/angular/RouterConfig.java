package fr.lteconsulting.angular2gwt.client.interop.angular;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = JsPackage.GLOBAL, name = "Object" )
public class RouterConfig
{
	public String path;

	public Object component;

	@JsOverlay
	public static RouterConfig route( String path, Object component )
	{
		RouterConfig res = new RouterConfig();

		res.path = path;
		res.component = component;

		return res;
	}
}