package fr.lteconsulting.hexa.client.interfaces;

import com.google.gwt.user.client.ui.Widget;

public interface IStackPanelRow
{
	// void setWidth( int width );
	void setHeight( int height );

	void addItem( Widget w, int x, int y, int sx, int sy );

	void repositionWidget( Widget w, int x, int y, int sx, int sy );

	void removeItem( Widget w );

	void clearAll();

	void setVisible( boolean visible );

	IStackPanelRow createSubRow();
}
