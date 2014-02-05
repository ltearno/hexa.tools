package com.hexa.client.ui.dialog;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

public class MyDialogBox
{
	//ResizablePanel impl = new ResizablePanel();
	//ResizablePanelForLayoutContent impl = new ResizablePanelForLayoutContent();
	
	boolean modal;

	public MyDialogBox()
	{
		this( true, true );
	}

	public MyDialogBox( boolean autoHide, boolean modal )
	{
		this.modal = modal;

		if( autoHide )
		{
//			impl.addCloseClickHandler( new ClickHandler()
//			{
//				@Override
//				public void onClick( ClickEvent event )
//				{
//					impl.hide();
//				}
//			} );
		}
	}

	public HandlerRegistration addCloseClickHandler( ClickHandler handler )
	{
		return null;//impl.addCloseClickHandler( handler );
	}

	public void setText( String text )
	{
//		impl.setText( text );
	}

	public void setTitle( String text )
	{
//		impl.setText( text );
	}

	public void add( Widget w )
	{
		setWidget( w );
	}

	public void setWidget( Widget w )
	{
//		impl.setContent( w );
	}

	public void show()
	{
//		impl.show( modal );
	}

	public void center()
	{
//		impl.show( modal );
	}

	public void hide()
	{
//		impl.hide();
	}
}
