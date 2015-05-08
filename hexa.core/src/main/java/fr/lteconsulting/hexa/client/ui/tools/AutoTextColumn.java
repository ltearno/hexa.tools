package fr.lteconsulting.hexa.client.ui.tools;

import static fr.lteconsulting.hexa.classinfo.ClassInfo.Clazz;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

import fr.lteconsulting.hexa.classinfo.Clazz;
import fr.lteconsulting.hexa.client.ui.miracle.Printer;
import fr.lteconsulting.hexa.client.ui.widget.TextEditor;
import fr.lteconsulting.hexa.databinding.TypedConverter;
import fr.lteconsulting.hexa.databinding.properties.Properties;

public class AutoTextColumn<T> implements IColumn<T>, HasValueChangeHandlers<T>
{
	TypedConverter<Object, String> displayConverter;
	
	String title;
	Clazz<T> dtoClazz;
	String fieldName;
	
	SimpleEventBus eventBus;
	
	public AutoTextColumn( Class<T> dtoClass, String fieldName )
	{
		this( dtoClass, fieldName, fieldName, null );
	}
	
	public AutoTextColumn( Class<T> dtoClass, String fieldName, TypedConverter<Object, String> displayConverter )
	{
		this( dtoClass, fieldName, fieldName, displayConverter );
	}
	
	public AutoTextColumn( Class<T> dtoClass, String fieldName, String title, TypedConverter<Object, String> displayConverter )
	{
		this.title = title;
		this.fieldName = fieldName;
		
		dtoClazz = Clazz( dtoClass );
		if( ! Properties.hasSomethingToGetField( dtoClazz, fieldName ) )
			throw new RuntimeException( "Cannot handle property " + fieldName + " of class " + dtoClass.getSimpleName() );
		
		if( displayConverter != null )
			this.displayConverter = displayConverter;
		else
			this.displayConverter = createDefaultDisplayConverter();
	}
	
	private TypedConverter<Object, String> createDefaultDisplayConverter()
	{
		Class<?> propertyType = Properties.getPropertyType( dtoClazz, fieldName );
		if( propertyType == String.class )
		{
			return new TypedConverter<Object, String>()
			{
				@Override
				public Object convertBack( String value )
				{
					return value;
				}
				
				@Override
				public String convert( Object value )
				{
					return value == null ? "" : value.toString();
				}
			};
		}
		else if( propertyType == Integer.class || propertyType == int.class )
		{
			return new TypedConverter<Object, String>()
			{
				@Override
				public Object convertBack( String value )
				{
					return Integer.parseInt( value );
				}
				
				@Override
				public String convert( Object value )
				{
					return value == null ? "" : value.toString();
				}
			};
		}
		else
		{
			return new TypedConverter<Object, String>()
			{
				@Override
				public Object convertBack( String value )
				{
					return value;
				}
				
				@Override
				public String convert( Object value )
				{
					return value != null ? value.toString() : "";
				}
			};
		}
	}

	@Override
	public String getTitle()
	{
		return title;
	}

	@Override
	public void fillCell( Printer printer, T record )
	{
		printer.setText( getValue( record ) );
	}
	
	private String getValue( T record )
	{
		Object value = Properties.getValue( record, fieldName );
		
		String display = displayConverter.convert( value );
		
		return display;
	}

	@Override
	public IEditor editCell( final T record )
	{
		TextEditor editor = new TextEditor( getValue( record ), true, false )
		{
			@Override
			protected void onValidate( String newValue )
			{
				Object realValue = displayConverter.convertBack( newValue );
				
				Properties.setValue( record, fieldName, realValue );
				
				getEditorHost().finishedEdition();
				
				ValueChangeEvent.fire( AutoTextColumn.this, record );
			}
		};
		
		return editor;
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