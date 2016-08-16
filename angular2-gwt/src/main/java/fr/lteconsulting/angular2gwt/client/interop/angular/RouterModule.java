package fr.lteconsulting.angular2gwt.client.interop.angular;

import fr.lteconsulting.angular2gwt.client.JsArray;
import fr.lteconsulting.angular2gwt.client.interop.angular.RouterConfig;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "ng.router", name = "RouterModule")
public class RouterModule {
	public static native Object forRoot(JsArray<RouterConfig> routes);
}
