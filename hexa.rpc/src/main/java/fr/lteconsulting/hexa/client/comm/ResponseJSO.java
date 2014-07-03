package fr.lteconsulting.hexa.client.comm;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;

public class ResponseJSO extends JavaScriptObject
{
	protected ResponseJSO()
	{
	}

	public native final int getInt( int i ) /*-{ return 1 * this[i]; }-*/;

	public native final double getDouble( int i ) /*-{ return 1.0 * this[i]; }-*/;

	public native final String getString( int i ) /*-{ return "" + this[i]; }-*/;

	public native final JSOArrayInteger getArrayInteger( int i ) /*-{ return this[i]; }-*/;

	public native final JsArrayString getArrayString( int i ) /*-{ return this[i]; }-*/;

	public native final JsArray<GenericJSO> getArray( int i ) /*-{ return this[i]; }-*/;

	public native final GenericJSO getJSO( int i ) /*-{ return this[i]; }-*/;
}
