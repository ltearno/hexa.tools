package fr.lteconsulting.hexa.client.ui.dialog;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.PopupPanel;

import fr.lteconsulting.hexa.client.css.HexaCss;

public class MyPopupPanel extends PopupPanel
{
	interface Css extends HexaCss
	{
		static final Css CSS = GWT.create( Css.class );

		String main();
	}

	public MyPopupPanel( boolean autoHide )
	{
		this( autoHide, false );
	}

	public MyPopupPanel( boolean autoHide, boolean modal )
	{
		super( autoHide, modal );

		setStylePrimaryName( Css.CSS.main() );
	}

	@Override
	public HandlerRegistration addCloseHandler( CloseHandler<PopupPanel> handler )
	{
		return super.addCloseHandler( handler );
	}
}
