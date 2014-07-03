package fr.lteconsulting.hexa.client.tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import fr.lteconsulting.hexa.client.ui.dialog.DialogBoxBuilder;
import fr.lteconsulting.hexa.client.ui.dialog.DialogBoxBuilder.DialogBox;

public class HexaTools
{
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

	public static String arrayToString( int[] array )
	{
		StringBuilder txt = new StringBuilder();
		boolean fAddComa = false;

		for( int o : array )
		{
			if( fAddComa )
				txt.append( "," );
			fAddComa = true;
			txt.append( o );
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

	public static List<Integer> stringToList( String s )
	{
		List<Integer> res = new ArrayList<Integer>();

		if( s.length() == 0 )
			return res;

		String[] ss = s.split( "," );
		for( int i = 0; i < ss.length; i++ )
			res.add( Integer.parseInt( ss[i] ) );

		return res;
	}

	public static List<String> stringToStringList( String s )
	{
		List<String> res = new ArrayList<>();

		if( s.length() == 0 )
			return res;

		String[] ss = s.split( "," );
		for( int i = 0; i < ss.length; i++ )
			res.add( ss[i] );

		return res;
	}

	public static void alert( String message )
	{
		alert( "No title", message );
	}

	public static void alert( String title, String message )
	{
		final DialogBox db = DialogBoxBuilder.create( title, new Label( message ) );
		db.show();
	}

	public static void alertHtml( String title, String html )
	{
		final DialogBox db = DialogBoxBuilder.create( title, new HTML( html ) );
		db.show();
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
