package fr.lteconsulting.angular2gwt.client.interop.angular;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "ng.core", name = "DirectiveMetadata")
public class DirectiveMetadata
{
	public String selector;
	public Host host;

	@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
	static class Host
	{
		String hostName;
	}
}
