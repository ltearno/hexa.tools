package com.hexa.client.ui;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Widget;

public class MyDialogBox2
{
	DialogBox box;

	public MyDialogBox2()
	{
		box = new DialogBox( true, true );
		box.setGlassEnabled( true );
	}

	public MyDialogBox2( boolean f1, boolean f2 )
	{
		box = new DialogBox( true, true );
		box.setGlassEnabled( true );
	}

	public void setText( String text )
	{
		box.setText( text );
	}

	public void setTitle( String text )
	{
		box.setText( text );
	}

	public void add( Widget w )
	{
		setWidget( w );
	}

	public void setWidget( Widget w )
	{
		box.setWidget( w );
	}

	public void show()
	{
		box.show();
	}

	public void center()
	{
		box.center();
	}

	public void hide()
	{
		box.hide();
	}
}
