package fr.lteconsulting.hexa.client.tools;

import java.util.Iterator;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class JsArrayIt<T extends JavaScriptObject> implements Iterable<T>
{
	JsArray<T> js;

	public JsArrayIt( JsArray<JavaScriptObject> js )
	{
		this.js = js.cast();
	}

	public Iterator<T> iterator()
	{
		return new JsIterator<T>( js.cast() );
	}

}