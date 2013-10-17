package com.hexa.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;

public class MyDialogBox
{
	ResizablePanelImpl impl = new ResizablePanelImpl();
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
			impl.addCloseClickHandler( new ClickHandler()
			{
				@Override
				public void onClick( ClickEvent event )
				{
					impl.hide();
				}
			} );
		}
	}

	public void setText( String text )
	{
		impl.setText( text );
	}

	public void setTitle( String text )
	{
		impl.setText( text );
	}

	public void add( Widget w )
	{
		setWidget( w );
	}

	public void setWidget( Widget w )
	{
		impl.setContent( w );
	}

	public void show()
	{
		impl.show( modal );
	}

	public void center()
	{
	}

	public void hide()
	{
		impl.hide();
	}
}
