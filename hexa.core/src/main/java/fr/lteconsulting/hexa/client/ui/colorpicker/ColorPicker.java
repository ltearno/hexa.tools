package fr.lteconsulting.hexa.client.ui.colorpicker;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.Widget;

public class ColorPicker extends Widget implements HasSelectionHandlers<String>
{
	private static Bundle bundle = GWT.create( Bundle.class );
	
	interface Bundle extends ClientBundle
	{
		@Source( "ColorPicker.css" )
		Css css();
	}
	
	interface Css extends CssResource
	{
		String colorZone();
	}
	
	public ColorPicker()
	{
		bundle.css().ensureInjected();
		
		setElement( Document.get().createDivElement() );
		getElement().getStyle().setWidth( 180, Unit.PX );
		
		Element emptyColor = addDivForColor( "" );
		emptyColor.getStyle().setBorderColor( "black" );
		emptyColor.getStyle().setBorderWidth( 1, Unit.PX );
		for( String color : Colors.colors )
			addDivForColor( color );
		
		addDomHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				String color = findColor( Element.as( event.getNativeEvent().getEventTarget() ) );
				if( color != null )
					SelectionEvent.fire( ColorPicker.this, color );
			}
		}, ClickEvent.getType() );
	}
	
	private Element addDivForColor( String color )
	{
		Element div = Document.get().createDivElement();
		div.getStyle().setBackgroundColor( color );
		div.addClassName( bundle.css().colorZone() );
		div.setAttribute( "value", color );
		
		getElement().appendChild( div );
		
		return div;
	}
	
	private String findColor( Element element )
	{
		if( element == null )
			return null;
		
		if( element == getElement() )
			return null;
		
		String color = element.getAttribute( "value" );
		if( color != null )
			return color;
		
		return findColor( element.getParentElement() );
	}

	@Override
	public HandlerRegistration addSelectionHandler( SelectionHandler<String> handler )
	{
		return addHandler( handler, SelectionEvent.getType() );
	}
}
