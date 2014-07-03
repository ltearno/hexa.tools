package fr.lteconsulting.hexa.client.tools;

import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Float;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.WidgetCollection;

public class HTMLStream extends ComplexPanel
{
	Element div;

	Element currentParagraph = null;

	HashMap<String, String> curSet = new HashMap<String, String>();
	HashMap<String, String> curParCurSet = new HashMap<String, String>();

	public HTMLStream()
	{
		div = DOM.createDiv();
		setElement( div );
	}

	@Override
	public void clear()
	{
		clearAll();
	}

	@Override
	public void add( Widget widget )
	{
		addDown( widget );
	}

	public HTMLStream clearAll()
	{
		WidgetCollection childs = getChildren();
		while( childs.size() > 0 )
		{
			childs.remove( 0 );
		}
		div.setInnerHTML( "" );
		currentParagraph = null;
		curSet.clear();
		curParCurSet.clear();

		return this;
	}

	public HTMLStream set( String attr, String value )
	{
		if( attr == null )
			return this;

		if( value == null )
			curSet.remove( attr );
		else
			curSet.put( attr, value );

		return this;
	}

	public HTMLStream unset( String attr )
	{
		return set( attr, null );
	}

	public HTMLStream bold( boolean fOn )
	{
		if( fOn )
			curSet.put( "weight", FontWeight.BOLD.name() );
		else
			curSet.put( "weight", FontWeight.NORMAL.name() );
		return this;
	}

	public HTMLStream text( String text )
	{
		for( Entry<String, String> e : curSet.entrySet() )
		{
			assert (e.getValue() != null);

			String curValue = curParCurSet.get( e.getKey() );
			if( curValue != null && curValue.equals( e.getValue() ) )
				continue;

			curParCurSet.put( e.getKey(), e.getValue() );

			// reset the current paragraph so it is recreated with correct
			// attributes
			currentParagraph = null;
		}

		for( Entry<String, String> e : curParCurSet.entrySet() )
		{
			String wantedValue = curSet.get( e.getKey() );
			if( wantedValue != null && wantedValue.equals( e.getValue() ) )
				continue;

			if( wantedValue == null )
				curParCurSet.remove( e.getKey() );
			else
				curParCurSet.put( e.getKey(), wantedValue );

			// reset the current paragraph so it is recreated with correct
			// attributes
			currentParagraph = null;
		}

		ensureCurPar();

		currentParagraph.setInnerHTML( currentParagraph.getInnerHTML() + text );

		return this;
	}

	public HTMLStream html( String html )
	{
		// reset the current paragraph so it is recreated later with correct
		// attributes
		currentParagraph = null;
		curParCurSet.clear();

		Element wrap = DOM.createSpan();
		div.appendChild( wrap );

		wrap.setInnerHTML( html );

		return this;
	}

	public HTMLStream br()
	{
		currentParagraph = null;

		div.appendChild( DOM.createElement( "br" ) );

		return this;
	}

	public HTMLStream addLeft( Widget widget )
	{
		Element container = addPrivate( widget );

		container.getStyle().setFloat( Float.LEFT );

		return this;
	}

	public HTMLStream addRight( Widget widget )
	{
		Element container = addPrivate( widget );

		container.getStyle().setFloat( Float.RIGHT );

		return this;
	}

	public HTMLStream addDown( Widget widget )
	{
		addPrivate( widget );

		return this;
	}

	public HTMLStream addInline( IsWidget widget )
	{
		return addInline( widget.asWidget() );
	}

	public HTMLStream addInline( Widget widget )
	{
		Element element = addInlinePrivate( widget );
		element.getStyle().setDisplay( Display.INLINE );

		return this;
	}

	public HTMLStream clFl()
	{
		currentParagraph = null;

		Element clearer = DOM.createDiv();
		clearer.getStyle().setProperty( "clear", "both" );

		div.appendChild( clearer );

		return this;
	}

	private Element addPrivate( Widget widget )
	{
		currentParagraph = null;

		Element container = DOM.createDiv();

		add( widget, container );

		div.appendChild( container );

		return container;
	}

	private Element addInlinePrivate( Widget widget )
	{
		currentParagraph = null;

		add( widget, div );

		return widget.getElement();
	}

	private void ensureCurPar()
	{
		if( currentParagraph == null )
		{
			currentParagraph = DOM.createSpan();
			div.appendChild( currentParagraph );

			for( Entry<String, String> e : curSet.entrySet() )
			{
				if( e.getKey().equals( "color" ) )
					currentParagraph.getStyle().setColor( e.getValue() );
				else if( e.getKey().equals( "weight" ) )
					currentParagraph.getStyle().setFontWeight( FontWeight.valueOf( e.getValue() ) );
				else
					currentParagraph.getStyle().setProperty( e.getKey(), e.getValue() );
			}
		}
	}
}
