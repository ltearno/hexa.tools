package fr.lteconsulting.angular2gwt.client.interop.angular;

import fr.lteconsulting.angular2gwt.client.JsArray;
import jsinterop.annotations.JsMethod;

public class Angular
{
	@JsMethod( namespace = "ng.platformBrowserDynamic", name = "bootstrap" )
	public static native void bootstrap( Object applicationComponent );

	@JsMethod( namespace = "ng.platformBrowserDynamic", name = "bootstrap" )
	public static native void bootstrap( Object applicationComponent, JsArray<?> providers );
	
	@JsMethod( namespace = "ng.platformBrowserDynamic", name = "platformBrowserDynamic" )
	public static native PlatformBrowserDynamic platformBrowserDynamic();

	@JsMethod( namespace = "ng.core", name = "provide" )
	public static native <T> T provide( Class<?> provider, Object options );

	@JsMethod( namespace = "ng.router" )
	public static native JsArray<Object> provideRouter( JsArray<RouterConfig> routes );

	@JsMethod( namespace = "ng.core" )
	public static native void enableProdMode();
}
