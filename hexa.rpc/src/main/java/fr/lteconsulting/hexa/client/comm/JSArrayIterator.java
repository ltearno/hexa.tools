package fr.lteconsulting.hexa.client.comm;

import java.util.Iterator;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class JSArrayIterator<T extends JavaScriptObject> implements Iterable<T>
{
	JsArray<T> obj;

	public JSArrayIterator( JsArray<T> obj )
	{
		this.obj = obj;
	}

	public Iterator<T> iterator()
	{
		return new Iterator<T>()
		{
			int idx = 0;

			public boolean hasNext()
			{
				return idx < obj.length();
			}

			public T next()
			{
				return obj.get( idx++ );
			}

			public void remove()
			{
				assert false : "remove forbidden";
			}
		};
	}
}