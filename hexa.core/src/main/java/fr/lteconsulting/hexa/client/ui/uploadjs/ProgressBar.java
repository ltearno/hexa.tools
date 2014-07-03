package fr.lteconsulting.hexa.client.ui.uploadjs;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

public class ProgressBar extends Widget implements HasValue<Integer>
{
	int currentValue = 0;

	Element progress = DOM.createDiv();
	Element text = DOM.createSpan();

	public ProgressBar()
	{
		Element div = DOM.createDiv();
		div.getStyle().setWidth( 100, Unit.PX );
		div.getStyle().setHeight( 20, Unit.PX );
		div.getStyle().setBorderWidth( 1, Unit.PX );
		div.getStyle().setBorderColor( "black" );
		div.getStyle().setBorderStyle( BorderStyle.SOLID );
		div.getStyle().setPosition( Position.ABSOLUTE );

		progress.getStyle().setWidth( currentValue, Unit.PCT );
		progress.getStyle().setHeight( 100, Unit.PCT );
		progress.getStyle().setBackgroundColor( "grey" );

		text.getStyle().setColor( "white" );
		text.getStyle().setPosition( Position.ABSOLUTE );
		text.getStyle().setTop( 0, Unit.PX );
		text.getStyle().setLeft( 0, Unit.PX );

		div.appendChild( progress );
		div.appendChild( text );

		Element main = DOM.createDiv();
		main.appendChild( div );
		main.getStyle().setWidth( 102, Unit.PX );
		main.getStyle().setHeight( 22, Unit.PX );
		setElement( main );
	}

	@Override
	public HandlerRegistration addValueChangeHandler( ValueChangeHandler<Integer> handler )
	{
		return addHandler( handler, ValueChangeEvent.getType() );
	}

	@Override
	public Integer getValue()
	{
		return currentValue;
	}

	@Override
	public void setValue( Integer value )
	{
		setValue( value, true );
	}

	@Override
	public void setValue( Integer value, boolean fireEvents )
	{
		currentValue = value;

		progress.getStyle().setWidth( currentValue, Unit.PCT );
		if( currentValue >= 0 )
			text.setInnerText( currentValue + "%" );
		else
			text.setInnerText( "Error" );

		if( fireEvents )
			ValueChangeEvent.fire( this, value );
	}
}
