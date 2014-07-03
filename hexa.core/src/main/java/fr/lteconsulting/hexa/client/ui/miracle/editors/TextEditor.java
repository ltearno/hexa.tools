package fr.lteconsulting.hexa.client.ui.miracle.editors;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import fr.lteconsulting.hexa.client.interfaces.IAsyncCallback;
import fr.lteconsulting.hexa.client.ui.css.Css;

public class TextEditor extends Composite
{
	TextBox tb = new TextBox();

	HandlerRegistration blurRegistration;
	HandlerRegistration keyUpRegistration;

	public TextEditor()
	{
		tb.addStyleName( Css.css().borderBoxSizing() );
		initWidget( tb );
	}

	@Override
	protected void onAttach()
	{
		super.onAttach();

		tb.setFocus( true );
		tb.selectAll();
	}

	public void edit( String currentText, final IAsyncCallback<String> callback, int width, int height )
	{
		tb.setWidth( width + "px" );

		tb.setText( currentText );

		// on lost focus, cancel edition
		if( blurRegistration != null )
			blurRegistration.removeHandler();
		blurRegistration = tb.addBlurHandler( new BlurHandler()
		{
			@Override
			public void onBlur( BlurEvent event )
			{
				callback.onSuccess( null );
			}
		} );

		if( keyUpRegistration != null )
			keyUpRegistration.removeHandler();
		keyUpRegistration = tb.addKeyUpHandler( new KeyUpHandler()
		{
			@Override
			public void onKeyUp( KeyUpEvent event )
			{
				if( event.getNativeKeyCode() == KeyCodes.KEY_ENTER )
				{
					event.preventDefault();
					event.stopPropagation();

					callback.onSuccess( tb.getText() );
				}
			}
		} );
	}
}