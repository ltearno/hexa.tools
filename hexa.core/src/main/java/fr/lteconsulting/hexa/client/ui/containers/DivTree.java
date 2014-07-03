package fr.lteconsulting.hexa.client.ui.containers;

import java.util.Iterator;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class DivTree extends Panel
{
	private Node rootNode;

	public DivTree()
	{
		Element div = DOM.createDiv();
		div.setClassName( "DivTree-Container" );

		setElement( div );

		rootNode = new Node( div );
	}

	public Node getRootNode()
	{
		return rootNode;
	}

	class Node
	{
		Element nodeContainer;
		Element nodeDiv;
		Element childContainer;

		Widget nodeWidget = null;

		public Node( Element container )
		{
			nodeContainer = container;

			nodeDiv = DOM.createDiv();
			nodeDiv.setClassName( "DivTree-NodeDiv" );
			nodeContainer.appendChild( nodeDiv );
			nodeDiv.setInnerText( "Node salam aleikum salam aleikum salam aleikum salam aleikum salam aleikum salam" );

			childContainer = DOM.createDiv();
			// childContainer.getStyle().setDisplay( Display.INLINE_BLOCK );
			childContainer.setClassName( "DivTree-ChildrenContainer" );
			nodeContainer.appendChild( childContainer );
		}

		public void setNodeWidget( Widget widget )
		{
			widget.removeFromParent();

			if( nodeWidget != null )
			{
				try
				{
					orphan( nodeWidget );
				}
				finally
				{
					getElement().removeChild( nodeWidget.getElement() );
					nodeWidget = null;
				}
			}

			nodeWidget = widget;

			if( widget != null )
			{
				DOM.appendChild( nodeDiv, widget.getElement() );

				adopt( widget );
			}
		}

		public Node addNodeChild()
		{
			Element childNodeContainer = DOM.createDiv();
			childNodeContainer.setClassName( "DivTree-NodeContainer" );

			childContainer.appendChild( childNodeContainer );

			Node childNode = new Node( childNodeContainer );

			return childNode;
		}
	}

	@Override
	public boolean remove( Widget child )
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<Widget> iterator()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void doAttachChildren()
	{
	}

	@Override
	protected void doDetachChildren()
	{
	}
}
