package fr.lteconsulting.hexa.client.tools;

import java.util.Iterator;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class JsIterator<T extends JavaScriptObject> implements Iterator<T>
{
	JsArray<T> js;
	int curIdx = 0;

	public JsIterator( JavaScriptObject jso )
	{
		js = jso.cast();
	}

	public JsIterator( JsArray<T> js )
	{
		this.js = js;
	}

	public boolean hasNext()
	{
		return curIdx < js.length();
	}

	public T next()
	{
		return js.get( curIdx++ );
	}

	public void remove()
	{
		assert false : "remove forbidden";
	}
}