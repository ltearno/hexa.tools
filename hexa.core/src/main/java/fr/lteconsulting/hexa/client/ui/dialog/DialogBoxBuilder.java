package fr.lteconsulting.hexa.client.ui.dialog;

import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.Widget;

public class DialogBoxBuilder
{
	public interface DialogBox
	{
		void show();
		void show( boolean isAutoHide );
		void hide();
		HandlerRegistration addCloseHandler( CloseHandler<DialogBox> handler );
	}
	
	private DialogBoxBuilder()
	{
	}
	
	public static DialogBox create( String title, IsWidget content )
	{
		if( content == null )
			return null;
		
		Widget w = content.asWidget();
		if( w == null )
			return null;
		
		DialogBox db = null;
		if( w instanceof RequiresResize )
			db = new DialogBoxForLayoutWidget( title, w );
		else
			db = new DialogBoxForNormalWidget( title, w );
		
		return db;
	}
}
