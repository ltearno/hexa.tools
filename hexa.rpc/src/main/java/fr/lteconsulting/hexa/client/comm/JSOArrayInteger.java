package fr.lteconsulting.hexa.client.comm;

import com.google.gwt.core.client.JavaScriptObject;

public class JSOArrayInteger extends JavaScriptObject
{
	protected JSOArrayInteger()
	{
	}

	public native final int get( int i ) /*-{ return 1 * this[i]; }-*/;

	public native final int length() /*-{ return this.length; }-*/;
}
