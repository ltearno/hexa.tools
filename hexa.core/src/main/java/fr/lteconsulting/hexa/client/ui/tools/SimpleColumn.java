package fr.lteconsulting.hexa.client.ui.tools;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

import fr.lteconsulting.hexa.client.ui.miracle.Printer;
import fr.lteconsulting.hexa.client.ui.widget.TextEditor;

public abstract class SimpleColumn<T> implements IColumn<T>, HasValueChangeHandlers<T>
{
	private final String title;
	
	private SimpleEventBus eventBus;
	
	protected abstract Object getRecordValue( T record );
	protected abstract void setRecordValue( T record, Object value );
	protected abstract Class<?> getPropertyType();
	
	public SimpleColumn( String title )
	{
		this.title = title;
	}

	@Override
	public String getTitle()
	{
		return title;
	}

	@Override
	public void fillCell( Printer printer, T record )
	{
		printer.setHTML( getValue( record ) );
	}
	
	private String getValue( T record )
	{
		Object value = getRecordValue( record );
		
		if( value == null )
			return "";
		else
			return value.toString();
	}

	@Override
	public IEditor editCell( final T record )
	{
		Class<?> propertyType = getPropertyType();
		
		if( propertyType == String.class )
		{
			TextEditor editor = new TextEditor( getValue( record ), true, false )
			{
				@Override
				protected void onValidate( String newValue )
				{
					getEditorHost().finishedEdition();
					setRecordValue( record, newValue );
					
					ValueChangeEvent.fire( SimpleColumn.this, record );
				}
			};
			
			return editor;
		}
		else if( propertyType == Integer.class || propertyType == int.class )
		{
			TextEditor editor = new TextEditor( getValue( record ), true, false )
			{
				@Override
				protected void onValidate( String newValue )
				{
					Integer intValue = Integer.parseInt( newValue );
					getEditorHost().finishedEdition();
					
					setRecordValue( record, intValue );
					
					ValueChangeEvent.fire( SimpleColumn.this, record );
				}
			};
			
			return editor;
		}
		else if( propertyType == Double.class || propertyType == double.class )
		{
			TextEditor editor = new TextEditor( getValue( record ), true, false )
			{
				@Override
				protected void onValidate( String newValue )
				{
					Double doubleValue = Double.parseDouble( newValue );
					getEditorHost().finishedEdition();
					
					setRecordValue( record, doubleValue );
					
					ValueChangeEvent.fire( SimpleColumn.this, record );
				}
			};
			
			return editor;
		}
		
		return null;
	}
	
	private EventBus getEventBus()
	{
		if( eventBus == null )
			eventBus = new SimpleEventBus();
		return eventBus;
	}

	@Override
	public void fireEvent( GwtEvent<?> event )
	{
		getEventBus().fireEvent( event );
	}

	@Override
	public HandlerRegistration addValueChangeHandler( ValueChangeHandler<T> handler )
	{
		return getEventBus().addHandler( ValueChangeEvent.getType(), handler );
	}
}