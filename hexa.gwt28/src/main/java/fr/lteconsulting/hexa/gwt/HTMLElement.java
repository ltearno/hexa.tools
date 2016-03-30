package fr.lteconsulting.hexa.gwt;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = JsPackage.GLOBAL )
public abstract class HTMLElement
{
	public String className;
	public String innerHTML;

	@JsProperty
	public native void setText( String text );

	@JsProperty
	public native String getText();

	@JsOverlay
	public final HTMLElement innerHTML( String html )
	{
		this.innerHTML = html;
		return this;
	}

	@JsOverlay
	public final String innerHTML()
	{
		return innerHTML;
	}

	@JsOverlay
	public final HTMLElement className( String className )
	{
		this.className = className;
		return this;
	}

	@JsOverlay
	public final String className()
	{
		return className;
	}

	public native void blur();

	public native void click();

	public native void focus();

	public native void appendChild( HTMLElement child );
}
