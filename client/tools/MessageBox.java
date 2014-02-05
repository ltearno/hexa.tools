package com.hexa.client.tools;

import com.google.gwt.user.client.ui.HTML;
import com.hexa.client.ui.dialog.DialogBoxBuilder;
import com.hexa.client.ui.dialog.DialogBoxBuilder.DialogBox;

public class MessageBox
{
	public static void show( String title, String message )
	{
		final DialogBox db = DialogBoxBuilder.create( title, new HTML( message ) );
		db.show();
	}
}
