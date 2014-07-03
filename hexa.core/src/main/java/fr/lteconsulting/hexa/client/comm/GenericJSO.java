package fr.lteconsulting.hexa.client.comm;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayString;

public class GenericJSO extends JavaScriptObject
{
	protected GenericJSO()
	{
	}

	public final native void setString( String key, String value ) /*-{ this[key] = value; }-*/;

	public final native void setInt( String key, int value ) /*-{ this[key] = value; }-*/;

	public final native <T extends JavaScriptObject> void setObject( String key, T value ) /*-{ this[key] = value; }-*/;

	public final native JsArray<GenericJSO> getAsArray() /*-{ return this; }-*/;

	public final native <T extends JavaScriptObject> JsArray<T> getAsArrayEx() /*-{ return this; }-*/;

	public final native JsArrayInteger getAsArrayOfInt() /*-{ return this; }-*/;

	public final native JsArrayString getAsArrayOfString() /*-{ return this; }-*/;

	public final native int getIntByIdx( int idx ) /*-{ var v=1*this[idx]; if(isNaN(v)) v=-1; return v; }-*/;

	public final native double getDoubleByIdx( int idx ) /*-{ var v=1.0*(Math.round(100.0*this[idx])/100.0); if(isNaN(v)) v=-12567.0; return v; }-*/;

	public final native String getStringByIdx( int idx ) /*-{ return ""+ this[idx]; }-*/;

	public final native <T extends JavaScriptObject> T getJavaScriptObjectByIdx( int idx ) /*-{ return this[idx]; }-*/;

	public final native GenericJSO getGenericJSO( String fieldName ) /*-{ return this[fieldName] || null; }-*/;

	public final native GenericJSO getGenericJSOByIdx( int idx ) /*-{ return this[idx]; }-*/;

	// public final native int getInt( String fieldName ) /*-{ return 1*
	// this[fieldName]; }-*/;
	public final int getInt( String fieldName )
	{
		int v = getInt_REAL( fieldName );
		if( v == -12566 ) // magik value !!!
			GWT.log( "NaN when getting field " + fieldName, null );
		// assert v != -12566;
		return v;
	}

	public final native int getInt_REAL( String fieldName )
	/*-{
		var v = 1 * this[fieldName];
		if( isNaN( v ) )
			return -12566; // magik value !!!
		return v;
	}-*/;

	private final native boolean isNull( String fieldName )
	/*-{
		if( this[fieldName] === null )
			return true;
		return false;
	}-*/;

	private final native boolean isNull( int idx )
	/*-{
		if( this[idx] === null )
			return true;
		return false;
	}-*/;

	public final Integer getInteger( String fieldName )
	{
		if( isNull( fieldName ) )
			return null;
		return getInt( fieldName );
	}

	public final Integer getIntegerByIdx( int idx )
	{
		if( isNull( idx ) )
			return null;
		return getIntByIdx( idx );
	}

	public final native void setDouble( String fieldName, double value ) /*-{ this[fieldName] = value; }-*/;

	public final native double getDouble( String fieldName ) /*-{ return 1.0* (Math.round(100.0*this[fieldName])/100.0); }-*/;

	public final native String getString( String fieldName ) /*-{ return ""+ this[fieldName]; }-*/;

	public final native boolean getBoolean( String fieldName ) /*-{ return this[fieldName]>0?true:false; }-*/;

	public final native <T extends JavaScriptObject> JsArray<T> getArray( String fieldName ) /*-{ return this[fieldName]; }-*/;

	public final native <T extends JavaScriptObject> JsArray<T> getArrayByIdx( int idx ) /*-{ return this[idx]; }-*/;

	public final native JsArrayInteger getArrayOfInt( String fieldName ) /*-{ return this[fieldName]; }-*/;

	public final native JsArrayString getArrayOfString( String fieldName ) /*-{ return this[fieldName]; }-*/;

	public final native int length()
	/*-{
	   	return this.length;
	}-*/;

	public final String dump()
	{
		return _dumpX( this, 0 );
	}

	public final native String _dumpX( JavaScriptObject arr, int level )
	/*-{
		$wnd["dumped"] = this;
	}-*/;

	public final native String _dump( JavaScriptObject arr, int level )
	/*-{
		f = function dump( arr, level )
		{
			var dumped_text = "";
			var level = 0;

			//The padding given at the beginning of the line.
			var level_padding = "";
			for(var j=0;j<level+1;j++) level_padding += "    ";

			if(typeof(arr) == 'object') { //Array/Hashes/Objects
				for(var item in arr) {
					var value = arr[item];

					if(typeof(value) == 'object') { //If it is an array,
						dumped_text += level_padding + "'" + item + "' ...\n";
						dumped_text += dump(value,level+1);
					} else {
						dumped_text += level_padding + "'" + item + "' => \"" + value + "\"\n";
					}
				}
			} else { //Stings/Chars/Numbers etc.
				dumped_text = "===>"+arr+"<===("+typeof(arr)+")";
			}
			return dumped_text;
		}
		return f( arr, level );
	}-*/;
}
