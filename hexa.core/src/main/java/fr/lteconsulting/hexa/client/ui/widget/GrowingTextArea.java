package fr.lteconsulting.hexa.client.ui.widget;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.FontStyle;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.TextArea;

public class GrowingTextArea extends TextArea implements KeyDownHandler, KeyUpHandler
{
	Element fakeDiv = null;

	public GrowingTextArea()
	{
		addKeyDownHandler( this );
		addKeyUpHandler( this );

		applyStyle( getElement() );
	}

	void applyStyle( Element element )
	{
		element.getStyle().setPadding( 5, Unit.PX );
		element.getStyle().setBorderColor( "black" );
		element.getStyle().setBorderStyle( BorderStyle.SOLID );
		element.getStyle().setBorderWidth( 1, Unit.PX );

		element.getStyle().setProperty( "fontFamily", "Arial Unicode MS,Arial,sans-serif" );

		element.getStyle().setFontSize( 12, Unit.PT );
		element.getStyle().setFontStyle( FontStyle.NORMAL );
		element.getStyle().setFontWeight( FontWeight.NORMAL );
	}

	void resize()
	{
		if( fakeDiv == null )
		{
			fakeDiv = DOM.createDiv();
			fakeDiv.getStyle().setPosition( Position.FIXED );
			fakeDiv.getStyle().setLeft( -10000, Unit.PX );
			getElement().getParentElement().insertAfter( fakeDiv, getElement() );
			applyStyle( fakeDiv );
		}

		int width = getElement().getAbsoluteRight() - getElement().getAbsoluteLeft();
		fakeDiv.getStyle().setWidth( width - 10 - 2, Unit.PX );

		fakeDiv.setInnerText( getText() );

		int height = getElement().getClientHeight();
		int newHeight = fakeDiv.getClientHeight() + 25;
		if( height < newHeight )
			getElement().getStyle().setHeight( newHeight, Unit.PX );
	}

	@Override
	public void onKeyUp( KeyUpEvent event )
	{
		resize();
	}

	@Override
	public void onKeyDown( KeyDownEvent event )
	{
		resize();
	}
}
