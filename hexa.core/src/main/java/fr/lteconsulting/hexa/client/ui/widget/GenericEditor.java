package fr.lteconsulting.hexa.client.ui.widget;

import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.ui.tools.IEditor;
import fr.lteconsulting.hexa.client.ui.tools.IEditorHost;

public abstract class GenericEditor<T extends Widget> implements IEditor
{
	private IEditorHost editorHost;
	private final Validator<T> validator;
	private final T widget;

	abstract protected void onValidate( T widget );

	public GenericEditor( T widget, boolean fShowCancel )
	{
		this( widget, fShowCancel, true );
	}
	
	public GenericEditor( final T widget, boolean fShowCancel, boolean fShowValidator )
	{
		this.widget = widget;
		
		if( fShowValidator ) 
		{
			validator = new Validator<>();
			validator.setEditor( widget, fShowCancel );
			validator.setCallback( validatorCallback );
		}
		else
		{
			validator = null;
		}
		
		if( widget instanceof Focusable )
		{
			widget.addAttachHandler( new AttachEvent.Handler()
			{
				@Override
				public void onAttachOrDetach( AttachEvent event )
				{
					((Focusable)widget).setFocus( true );
				}
			} );
		}
	}

	@Override
	public final void setHost( IEditorHost editorHost )
	{
		this.editorHost = editorHost;
	}

	@Override
	public final Widget getWidget()
	{
		return validator != null ? validator : widget;
	}
	
	protected final T getEditorWidget()
	{
		return widget;
	}

	protected final void finishedEdition()
	{
		editorHost.finishedEdition();
	}

	protected final IEditorHost getEditorHost()
	{
		return editorHost;
	}

	private final ValidatorCallback validatorCallback = new ValidatorCallback()
	{
		@Override
		public void onValidatorAction( Button button )
		{
			switch( button )
			{
				case Cancel:
					editorHost.finishedEdition();
					break;

				case Ok:
					onValidate( validator.getEditor() );
			}
		}
	};
}
