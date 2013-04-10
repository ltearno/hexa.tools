package com.hexa.client.ui;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class MyDialogBox3 implements IsWidget
{
	DialogBox dialog;
	
	public MyDialogBox3()
	{
		this( true, true );
	}
	
	public MyDialogBox3( boolean autoHide, boolean modal )
	{
		dialog = new DialogBox( autoHide, modal );
		dialog.setGlassEnabled( true );
		dialog.setAnimationEnabled( true );
		
		dialog.setStylePrimaryName( "hexa-DialogBox" );
	}

	@Override
	public Widget asWidget()
	{
		return dialog;
	}
	
	public void setText( String text )
	{
		dialog.setText(text);
	}
	
	public void setTitle( String text )
	{
		dialog.setText( text );
	}
	
	public void add( Widget w )
	{
		setWidget( w );
	}
	
	public void setWidget( Widget w )
	{
		dialog.setWidget( w );
	}
	
	public void show()
	{
		dialog.show();
	}
	
	public void center()
	{
		dialog.center();
	}
	
	public void hide()
	{
		dialog.hide();
	}
}
