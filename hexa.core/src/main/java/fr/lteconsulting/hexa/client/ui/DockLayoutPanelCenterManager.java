package fr.lteconsulting.hexa.client.ui;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.IsWidget;

public class DockLayoutPanelCenterManager implements AcceptsOneWidget
{
	DockLayoutPanel panel;
	IsWidget inMainPanel;

	public DockLayoutPanelCenterManager( DockLayoutPanel panel )
	{
		this.panel = panel;
	}

	@Override
	public void setWidget( IsWidget w )
	{
		if( inMainPanel != null )
			panel.remove( inMainPanel );

		inMainPanel = w;

		if( inMainPanel != null )
			panel.add( inMainPanel );
	}
}
