package com.hexa.client.tools;

import com.google.gwt.user.client.ui.HTML;
import com.hexa.client.ui.dialog.MyDialogBox;

public class MessageBox
{
	public static void show( String title, String message )
	{
		MyDialogBox db = new MyDialogBox();

		db.setTitle( title );
		db.setWidget( new HTML( message ) );

		db.show();
	}
}
