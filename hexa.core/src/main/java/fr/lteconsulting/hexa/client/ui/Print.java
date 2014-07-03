package fr.lteconsulting.hexa.client.ui;

/**
 * <pre>
 * Generic printing class
 * can be used to print the Window it self, DOM.Elements, UIObjects
 (Widgets) and plain HTML
 *
 * Usage:
 *      You must insert this iframe in your host page:
 *              <iframe id="__printingFrame" style="width:0;height:0;border:0"></iframe>
 *
 *      Window:
 *              Print.it();
 *
 *      Objects/HTML:
 *              Print.it(RootPanel.get("myId"));
 *              Print.it(DOM.getElementById("myId"));

 *              Print.it("Just <b>Print.it()</b>!");
 *
 *      Objects/HTML using styles:
 *              Print.it("<link rel='StyleSheet' type='text/css' media='paper' href='/paperStyle.css'>", RootPanel.get('myId'));
 *              Print.it("<style type='text/css' media='paper'> .newPage { page-break-after: always; } </style>", "Hi<p class='newPage'></p>By");
 * </pre>
 */

import java.util.ArrayList;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.UIObject;

public class Print
{

	// public static native void it() /*-{
	// $wnd.print();
	// }-*/;

	private static void ensureFrame()
	{
		com.google.gwt.dom.client.Element frame = Document.get().getElementById( "__printingFrame" );
		if( frame == null )
		{
			frame = DOM.createIFrame();
			frame.setId( "__printingFrame" );
			frame.setAttribute( "style", "width:0;height:0;border:0" );

			Document.get().getBody().appendChild( frame );
		}
	}

	public static void it( String html )
	{
		ensureFrame();
		itNative( html );
	}

	public static native void itNative( String html ) /*-{
														var frame = $doc.getElementById('__printingFrame');
														if (!frame) {
														//<iframe src="javascript:''" id="__printingFrame" style="width:0;height:0;border:0"></iframe>

														$wnd.alert("Error: Can't find printing frame.");
														return;
														}
														frame = frame.contentWindow;
														var doc = frame.document;
														doc.open();
														doc.write(html);
														doc.close();
														frame.focus();
														frame.print();
														}-*/;

	public static void it( UIObject obj )
	{
		it( "", obj.getElement().getInnerHTML() );
	}

	public static void it( ArrayList<UIObject> obj )
	{
		it( "", obj );
	}

	public static void it( Element element )
	{
		it( "", element.getInnerHTML() );
	}

	public static void it( String style, String it )
	{
		it( "<html><head>" + style + "</head><body>" + it + "</body></html>" );
	}

	public static void it( String style, UIObject obj )
	{
		it( style, obj.getElement().getInnerHTML() );
	}

	public static void it( String style, ArrayList<UIObject> obj )
	{
		StringBuilder build = new StringBuilder();
		for( UIObject o : obj )
			build.append( o.getElement().getInnerHTML() );

		it( style, build.toString() );
	}

	public static void it( String style, Element element )
	{
		it( style, element.getInnerHTML() );
	}
}