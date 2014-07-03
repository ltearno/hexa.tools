package fr.lteconsulting.hexa.client.ui.dialog;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import fr.lteconsulting.hexa.client.ui.UiBuilder;
import fr.lteconsulting.hexa.client.ui.dialog.DialogBoxBuilder.DialogBox;

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
		Button yes = new Button( "Yes" );
		Button no = new Button( "No" );
		HorizontalPanel buttonBar = UiBuilder.addIn( new HorizontalPanel(), yes, no );
		
		final DialogBox db = DialogBoxBuilder.create( title, UiBuilder.addIn( new VerticalPanel(), new HTML( message ), buttonBar ) );
		
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
		
		db.show( false );
	}
}
