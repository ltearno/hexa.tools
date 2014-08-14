package fr.lteconsulting.hexa.client.ui.miracle;

import java.util.HashMap;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Node;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class MiracleTable extends FlexTable
{
	Element thead = null;

	HashMap<Element, Widget> hdrWidgets = new HashMap<Element, Widget>();

	public MiracleTable()
	{
		super();
	}

	public HandlerRegistration addMouseDownHandler( MouseDownHandler handler )
	{
		sinkEvents( Event.ONMOUSEDOWN );
		return addDomHandler( handler, MouseDownEvent.getType() );
	}

	@SuppressWarnings( "deprecation" )
	@Override
	public com.google.gwt.user.client.Element getBodyElement()
	{
		int nbc = getElement().getChildCount();
		for( int c = 0; c < nbc; c++ )
		{
			Node node = getElement().getChild( c );
			if( node.getNodeName().equalsIgnoreCase( "tbody" ) )
				return node.<com.google.gwt.user.client.Element>cast();
		}
		return null;
	}

	public Element getHeaderElement()
	{
		int nbc = getElement().getChildCount();
		for( int c = 0; c < nbc; c++ )
		{
			Node node = getElement().getChild( c );
			if( node.getNodeName().equalsIgnoreCase( "thead" ) )
				return (com.google.gwt.dom.client.Element.as( node ));
		}
		return null;
	}

	public HdrInFlexTablePrinter getHdrPrinter( int column )
	{
		ensureHeaderCell( column );
		return new HdrInFlexTablePrinter( this, column );
	}

	public void setHdrText( int col, String text )
	{
		ensureHeaderCell( col );

		Element th = Element.as( thead.getChild( col ) );

		clearTH( th, false );

		th.setInnerText( text );
	}

	public void setHdrHTML( int col, String html )
	{
		ensureHeaderCell( col );

		Element th = Element.as( thead.getChild( col ) );

		clearTH( th, false );

		th.setInnerHTML( html );
	}

	public void setHdrWidget( int col, Widget widget )
	{
		ensureHeaderCell( col );

		Element th = Element.as( thead.getChild( col ) );

		clearTH( th, true );

		if( widget != null )
		{
			widget.removeFromParent();

			// Logical attach.
			hdrWidgets.put( th, widget );

			// Physical attach.
			DOM.appendChild( th, widget.getElement() );

			adopt( widget );
		}
	}

	private void clearTH( Element th, boolean fClearHTML )
	{
		Widget w = hdrWidgets.remove( th );
		if( w != null )
		{
			remove( w );
		}
		else
		{
			if( fClearHTML )
				th.setInnerHTML( "" );
		}
	}

	public int getHeaderForEvent( NativeEvent event )
	{
		Element th = getEventTargetHeader( Event.as( event ) );
		if( th == null )
			return -1;

		return DOM.getChildIndex( DOM.getParent( th ), th );
	}

	public Element getElementTargetHeader( Element th )
	{
		Element headElem = getHeaderElement();

		for( ; th != null; th = DOM.getParent( th ) )
		{
			if( th.getTagName().equalsIgnoreCase( "th" ) )
			{
				Element head = DOM.getParent( th );
				if( head == headElem )
				{
					return th;
				}
			}
			// If we run into this table's head, we're out of options.
			if( th == headElem )
			{
				return null;
			}
		}
		return null;
	}

	public Element getEventTargetHeader( Event event )
	{
		return getElementTargetHeader( DOM.eventGetTarget( event ) );
	}

	public Element getRowElement( int row )
	{
		return getRowFormatter().getElement( row );
	}

	private void ensureHeader()
	{
		if( thead != null )
			return;

		thead = DOM.createTHead();

		getElement().insertBefore( thead, getBodyElement() );
	}

	private void ensureHeaderCell( int col )
	{
		ensureHeader();

		while( thead.getChildCount() <= col )
			thead.appendChild( DOM.createTH() );
	}

	public static class HdrInFlexTablePrinter implements Printer
	{
		final MiracleTable table;
		final int col;

		HdrInFlexTablePrinter( MiracleTable table, int col )
		{
			this.table = table;
			this.col = col;
		}

		@Override
		public void setHTML( String html )
		{
			table.setHdrHTML( col, html );
		}

		@Override
		public void setText( String text )
		{
			table.setHdrText( col, text );
		}

		@Override
		public void setWidget( Widget widget )
		{
			table.setHdrWidget( col, widget );
		}
	}
}
