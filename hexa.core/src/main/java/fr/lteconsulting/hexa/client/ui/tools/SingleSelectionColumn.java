package fr.lteconsulting.hexa.client.ui.tools;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.Button;

import fr.lteconsulting.hexa.client.ui.miracle.Printer;

public class SingleSelectionColumn<T> extends ROColumnMng<T> implements HasSelectionHandlers<T>
{
	private final SimpleEventBus eventBus = new SimpleEventBus();
	
	public SingleSelectionColumn( String title )
	{
		super( title );
	}

	@Override
	public void fillCell( Printer printer, final T record )
	{
		final Button button = new Button( "*" );
		printer.setWidget( button );
		
		button.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				SelectionEvent.fire( SingleSelectionColumn.this, record );
			}
		} );
	}

	@Override
	public void fireEvent( GwtEvent<?> event )
	{
		eventBus.fireEvent( event );
	}

	@Override
	public HandlerRegistration addSelectionHandler( SelectionHandler<T> handler )
	{
		return eventBus.addHandler( SelectionEvent.getType(), handler );
	}
}