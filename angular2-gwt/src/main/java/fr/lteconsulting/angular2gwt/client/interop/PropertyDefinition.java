package fr.lteconsulting.angular2gwt.client.interop;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = JsPackage.GLOBAL, name = "Object" )
public class PropertyDefinition
{
	@JsProperty
	public PropertySetter set;
	
	@JsProperty
	public PropertyGetter get;
	
	@JsOverlay
	public static PropertyDefinition create( PropertyGetter getter, PropertySetter setter )
	{
		PropertyDefinition result = new PropertyDefinition();
		
		if( getter != null )
			result.get = getter;
		
		if( setter != null )
			result.set = setter;
		
		return result;
	}
}