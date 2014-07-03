package fr.lteconsulting.hexa.client.ui.containers;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import fr.lteconsulting.hexa.client.tools.HexaTools;
import fr.lteconsulting.hexa.client.tools.JQuery;

public class Accordion extends Panel
{
	public Accordion()
	{
		Element main = DOM.createDiv();
		main.setClassName( "Accordion" );

		setElement( main );
	}

	ArrayList<Item> items = new ArrayList<Item>();

	public Item addItem()
	{
		Item item = new Item();
		items.add( item );

		return item;
	}

	@Override
	public void clear()
	{
		while( items.size() > 0 )
			items.remove( 0 ).removeFromAccordion();
	}

	public class Item
	{
		boolean fExpanded = true;

		Element itemHeader;
		Element itemContentDecorator;
		Element itemContentContainer;

		Widget headerWidget = null;
		Widget contentWidget = null;

		public Item()
		{
			itemHeader = DOM.createDiv();
			itemHeader.setClassName( "Accordion-ItemHeader" );

			itemContentDecorator = DOM.createDiv();
			itemContentDecorator.setClassName( "Accordion-ItemContentDecorator" );

			itemContentContainer = DOM.createDiv();
			itemContentContainer.setClassName( "Accordion-ItemContentContainer" );
			itemContentDecorator.appendChild( itemContentContainer );

			getElement().appendChild( itemHeader );
			getElement().appendChild( itemContentDecorator );
		}

		public void removeFromAccordion()
		{
			setHeaderWidget( null );
			setContentWidget( null );

			itemContentDecorator.removeFromParent();
			itemHeader.removeFromParent();
		}

		public void setExpanded( boolean fExpanded )
		{
			this.fExpanded = fExpanded;

			String effect = "blind";

			if( !fExpanded )
				JQuery.get().jqHide( effect, itemContentDecorator, null );
			else
			{
				// for( Item item: items )
				// if( item != this )
				// item.setExpanded( false );
				JQuery.get().jqShow( effect, itemContentDecorator );
			}
		}

		public boolean getExpanded()
		{
			return fExpanded;
		}

		public void setHeaderWidget( Widget widget )
		{
			if( widget != null )
				widget.removeFromParent();

			if( headerWidget != null )
			{
				try
				{
					orphan( headerWidget );
				}
				finally
				{
					itemHeader.removeChild( headerWidget.getElement() );
					headerWidget = null;
				}
			}

			headerWidget = widget;

			if( widget != null )
			{
				DOM.appendChild( itemHeader, widget.getElement() );

				adopt( widget );
			}
		}

		public void setContentWidget( Widget widget )
		{
			if( widget != null )
				widget.removeFromParent();

			if( contentWidget != null )
			{
				try
				{
					orphan( contentWidget );
				}
				finally
				{
					itemContentContainer.removeChild( contentWidget.getElement() );
					contentWidget = null;
				}
			}

			contentWidget = widget;

			if( widget != null )
			{
				DOM.appendChild( itemContentContainer, widget.getElement() );

				adopt( widget );
			}
		}
	}

	@Override
	public boolean remove( Widget child )
	{
		return false;
	}

	class ItWidgets implements Iterator<Widget>
	{
		ArrayList<Widget> wList = new ArrayList<Widget>();
		int idx = 0;

		public ItWidgets()
		{
			for( Item item : items )
			{
				if( item.headerWidget != null )
					wList.add( item.headerWidget );
				if( item.contentWidget != null )
					wList.add( item.contentWidget );
			}
		}

		@Override
		public boolean hasNext()
		{
			return idx < wList.size();
		}

		@Override
		public Widget next()
		{
			Widget w = wList.get( idx );
			idx++;

			return w;
		}

		@Override
		public void remove()
		{
			HexaTools.alert( "Error !!! Remove not implemented in Accordion.java" );
		}

	}

	@Override
	public Iterator<Widget> iterator()
	{
		return new ItWidgets();
	}
}
