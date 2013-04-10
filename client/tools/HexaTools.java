package com.hexa.client.tools;

import java.util.Iterator;

import com.hexa.client.ui.MyDialogBox;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.ui.Label;

public class HexaTools
{
	public static native void firefox3compatibility()
	/*-{
		if (!$doc.getBoxObjectFor) {
			$doc.getBoxObjectFor = function (element) {
				var box = element.getBoundingClientRect();
				return { "x" : box.left, "y" : box.top, "width" : box.width, "height" : box.height };
			}
		}
	}-*/;

	public static String arrayToStringHuman( Iterable<?> array, String coordinatorWord )
	{
		StringBuilder txt = new StringBuilder();
		boolean fAddComa = false;

		Iterator<?> it = array.iterator();
		while( it.hasNext() )
		{
			Object o = it.next();

			if( fAddComa )
			{
				if( it.hasNext() )
					txt.append( ", " );
				else
					txt.append( " " + coordinatorWord + " " );
			}
			fAddComa = true;

			txt.append( o.toString() );
		}

		return txt.toString();
	}

	public static String arrayToString( Iterable<?> array )
	{
		StringBuilder txt = new StringBuilder();
		boolean fAddComa = false;

		for( Object o : array )
		{
			if( fAddComa )
				txt.append( "," );
			fAddComa = true;
			txt.append( o.toString() );
		}

		return txt.toString();
	}

	public static int[] stringToArray( String s )
	{
		if( s.length() == 0 )
			return new int[0];
		String[] ss = s.split( "," );
		int[] res = new int[ss.length];
		for( int i = 0; i < ss.length; i++ )
			res[i] = Integer.parseInt( ss[i] );
		return res;
	}

	public static void alert( String message )
	{
		alert( "No title", message );
	}

	public static void alert( String title, String message )
	{
		MyDialogBox db = new MyDialogBox();
		db.setTitle( title );
		db.setWidget( new Label( message ) );
		db.show();
		db.center();
	}

	public static native JavaScriptObject evalJSO( String params )
	/*-{
		return eval('(' + params + ')');
	}-*/;

	public static <T extends JavaScriptObject> String toJSON( T jso )
	{
		JSONObject obj = new JSONObject( jso );
		return obj.toString();
	}
}
