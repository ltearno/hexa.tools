package fr.lteconsulting.hexa.client.ui.Ribbon;

import com.google.gwt.user.client.ui.IsWidget;

public interface RibbonView extends IsWidget
{
	void selectButton( Object obj );

	void setButtonText( Object obj, String text );

	public void setTabText( String id, String text );
}