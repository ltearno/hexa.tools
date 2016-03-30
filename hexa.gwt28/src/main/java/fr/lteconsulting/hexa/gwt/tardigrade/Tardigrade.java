package fr.lteconsulting.hexa.gwt.tardigrade;

import fr.lteconsulting.hexa.gwt.HTMLElement;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType( isNative = true )
public class Tardigrade
{
	@JsProperty( namespace = JsPackage.GLOBAL, name = "tardigrade" )
	public static native Tardigrade get();

	@JsProperty( name = "tardigradeEngine" )
	public native TardigradeEngine tardigradeEngine();

	public native HTMLElement[] domChain( HTMLElement parent, HTMLElement child );

	public native HTMLElement createElement( String html );
}
