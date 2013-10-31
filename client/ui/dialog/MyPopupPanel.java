package com.hexa.client.ui.dialog;

import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.PopupPanel;

public class MyPopupPanel extends PopupPanel
{
	public MyPopupPanel( boolean autoHide )
	{
		this( autoHide, false );
	}

	public MyPopupPanel( boolean autoHide, boolean modal )
	{
		super( autoHide, modal );

		setStylePrimaryName( "hexa-PopupPanel" );
	}

	@Override
	public HandlerRegistration addCloseHandler( CloseHandler<PopupPanel> handler )
	{
		return super.addCloseHandler( handler );
	}
}
