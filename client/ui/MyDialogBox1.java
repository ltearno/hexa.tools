package com.hexa.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class MyDialogBox1 extends SimplePanel
{
	Widget widget = null;

	public MyDialogBox1( boolean f1, boolean f2 )
	{
		this();
	}

	public MyDialogBox1()
	{
		super( DOM.createDiv() );

		DOM.appendChild( getElement(), DOM.createDiv() );
	}

	final public void setText( String text )
	{
		getElement().setAttribute( "title", text );
	}

	public void setTitle( String text )
	{
		getElement().setAttribute( "title", text );
	}

	private native void dialogify( Element e, int w, int mh ) /*-{
																$wnd.$( e ).dialog( { width: w, maxHeight: mh } );
																}-*/;

	private native void closeDialog( Element e ) /*-{
													$wnd.$( e ).dialog( "close" );
													}-*/;

	public void show()
	{
		RootPanel.get().add( this );
		dialogify( getElement(), RootPanel.get().getOffsetWidth() / 2, (4 * Window.getClientHeight()) / 5 );
	}

	public void hide()
	{
		RootPanel.get().remove( this );
		closeDialog( getElement() );
	}

	public void center()
	{
	}
}