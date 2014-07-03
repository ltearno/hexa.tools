package fr.lteconsulting.hexa.client.tools;

import com.google.gwt.user.client.ui.HTML;
import fr.lteconsulting.hexa.client.ui.dialog.DialogBoxBuilder;
import fr.lteconsulting.hexa.client.ui.dialog.DialogBoxBuilder.DialogBox;

public class MessageBox
{
	public static void show( String title, String message )
	{
		final DialogBox db = DialogBoxBuilder.create( title, new HTML( message ) );
		db.show();
	}
}
