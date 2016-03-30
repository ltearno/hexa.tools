package fr.lteconsulting.hexa.gwt.tardigrade;

import fr.lteconsulting.hexa.gwt.HTMLElement;
import fr.lteconsulting.hexa.gwt.JsTypedObject;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = JsPackage.GLOBAL, name = "TardigradeEngine" )
public class TardigradeEngine
{
	public native void addTemplate( String name, Object spec );

	public native TemplateDescriptor getTemplateDescriptor( String name );

	public native String buildHtml( String templateName, Object dto );

	public native String buildNodeHtml( String templateName, String nodeId, Object dto );

	public native HTMLElement getPoint( HTMLElement templateElement, String templateName, JsTypedObject<String, Integer> intermediates );

	public native JsTypedObject<String, Integer> getLocation( HTMLElement templateElement, String templateName, HTMLElement element );
}
