package fr.lteconsulting.hexa.client.ui.widget;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
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
		
		getEditorWidget().addKeyUpHandler( new KeyUpHandler()
		{
			@Override
			public void onKeyUp( KeyUpEvent event )
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
