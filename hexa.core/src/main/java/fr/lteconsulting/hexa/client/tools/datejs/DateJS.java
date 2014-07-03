package fr.lteconsulting.hexa.client.tools.datejs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/*
 * This class wraps calls to the Date.js script
 */

public class DateJS
{
	interface DateJSBundle extends ClientBundle
	{
		@Source( "date.js" )
		TextResource DateJs();
	}

	interface DateFrJSBundle extends ClientBundle
	{
		@Source( "date-fr-FR.js" )
		TextResource DateJsFr();
	}

	private static boolean loaded;

	// tries to parse a textual represented date and
	// returns a String in the "yyyy-MM-dd" format if successfull, or null if
	// not
	public static String parseDate( String text )
	{
		// loads the script if not loaded yet
		if( !loaded )
		{
			loaded = true;

			String scriptContent = null;
			if( LocaleInfo.getCurrentLocale().getLocaleName().startsWith( "fr" ) )
			{
				DateFrJSBundle bundle = GWT.create( DateFrJSBundle.class );
				scriptContent = bundle.DateJsFr().getText();
			}
			else
			{
				DateJSBundle bundle = GWT.create( DateJSBundle.class );
				scriptContent = bundle.DateJs().getText();
			}

			Document doc = Document.get();
			ScriptElement sqljs = doc.createScriptElement();
			sqljs.setAttribute( "type", "text/javascript" );
			sqljs.setInnerText( scriptContent );
			doc.getDocumentElement().getFirstChildElement().appendChild( sqljs );
		}

		JavaScriptObject date = create( text );
		if( date == null )
			return null;

		String res = toConformity( date );

		return res;
	}

	private final static native JavaScriptObject create( String text )
	/*-{
		var date;

		date = $wnd.Date.parseExact( text, "yyyy-MM-dd" );
		if( date == null )
			date = $wnd.Date.parseExact( text, ["d MMMM yyyy","d MMM yyyy"] );
		if( date == null )
			date = $wnd.Date.parse( text );

		//@com.google.gwt.core.client.GWT::log(Ljava/lang/String;)( "JSNative datejs for " + text + " : " + date );

		if( date == null )
			return null;

		var object = { content: date };

		return object;
	}-*/;

	// gives the value of the date in DB UTC format
	private final static native String toConformity( JavaScriptObject date )
	/*-{
		if( date.content == null )
			return "";
		return date.content.toString( "yyyy-MM-dd" );
	}-*/;
}
