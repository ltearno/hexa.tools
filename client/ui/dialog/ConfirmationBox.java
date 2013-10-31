package com.hexa.client.ui.dialog;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ConfirmationBox
{
	public interface Callback
	{
		void onConfirmedBox();
	}

	public static void ask( final Callback callback )
	{
		ask( "You are about to execute an action, do you confirm ?", callback );
	}

	public static void ask( String message, final Callback callback )
	{
		ask( "Confirmation", message, callback );
	}

	public static void ask( String title, String message, final Callback callback )
	{
		final MyDialogBox db = new MyDialogBox( false, true );

		db.setText( title );

		VerticalPanel vp = new VerticalPanel();
		vp.add( new HTML( message ) );
		Button yes = new Button( "Yes" );
		Button no = new Button( "No" );
		vp.add( yes );
		vp.add( no );

		no.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				db.hide();
			}
		} );

		yes.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				db.hide();
				callback.onConfirmedBox();
			}
		} );

		db.add( vp );

		db.center();
		db.show();
	}
}
