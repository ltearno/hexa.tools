package com.hexa.client.tools;

import com.hexa.client.ui.MyDialogBox;
import com.google.gwt.user.client.ui.HTML;

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
