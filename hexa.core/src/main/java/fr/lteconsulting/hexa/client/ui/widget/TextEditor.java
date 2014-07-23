package fr.lteconsulting.hexa.client.ui.widget;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.ui.TextBox;

public abstract class TextEditor extends GenericEditor<TextBox>
{
	protected abstract void onValidate( String newValue );

	public TextEditor( String text, boolean fShowCancel )
	{
		this( text, fShowCancel, true );
	}
	
	public TextEditor( String text, boolean fShowCancel, boolean fShowValidator )
	{
		super( new TextBox(), fShowCancel, fShowValidator );
		
		getEditorWidget().addAttachHandler( new AttachEvent.Handler()
		{
			@Override
			public void onAttachOrDetach( AttachEvent event )
			{
				getEditorWidget().selectAll();
			}
		} );
		
		getEditorWidget().addKeyPressHandler( new KeyPressHandler()
		{
			@Override
			public void onKeyPress( KeyPressEvent event )
			{
				if( event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER )
				{
					onValidate( getEditorWidget().getText() );
					event.stopPropagation();
				}
				else if( event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE )
				{
					finishedEdition();
					event.stopPropagation();
				}
			}
		} );

		getEditorWidget().setText( text );
	}

	@Override
	protected final void onValidate( TextBox widget )
	{
		onValidate( widget.getText() );
	}

	protected void displayMessage( String text )
	{
		getEditorWidget().setText( text );
		getEditorWidget().setEnabled( false );
	}
}
