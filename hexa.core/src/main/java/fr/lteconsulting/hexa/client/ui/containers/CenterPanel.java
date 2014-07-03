package fr.lteconsulting.hexa.client.ui.containers;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ProvidesResize;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.Widget;

public class CenterPanel extends Panel implements HasOneWidget, RequiresResize, ProvidesResize
{
	private static CenterPanelUiBinder uiBinder = GWT.create( CenterPanelUiBinder.class );

	interface CenterPanelUiBinder extends UiBinder<Element, CenterPanel>
	{
	}

	@UiField
	DivElement containerElement;

	Widget widget;

	public CenterPanel()
	{
		Element main = uiBinder.createAndBindUi( this );
		setElement( main );
	}

	public CenterPanel( Widget child )
	{
		this();
		setWidget( child );
	}

	@Override
	public void onResize()
	{
		if( widget == null && widget instanceof RequiresResize )
			((RequiresResize) widget).onResize();
	}

	/**
	 * Adds a widget to this panel.
	 * 
	 * @param w
	 *            the child widget to be added
	 */
	@Override
	public void add( Widget w )
	{
		if( widget != null )
			throw new IllegalStateException( "SimplePanel can only contain one child widget" );

		setWidget( w );
	}

	/**
	 * Gets the panel's child widget.
	 * 
	 * @return the child widget, or <code>null</code> if none is present
	 */
	@Override
	public Widget getWidget()
	{
		return widget;
	}

	@Override
	public Iterator<Widget> iterator()
	{
		// Return a simple iterator that enumerates the 0 or 1 elements in this
		// panel.
		return new Iterator<Widget>()
		{
			boolean hasElement = widget != null;
			Widget returned = null;

			@Override
			public boolean hasNext()
			{
				return hasElement;
			}

			@Override
			public Widget next()
			{
				if( !hasElement || (widget == null) )
					throw new NoSuchElementException();

				hasElement = false;
				return(returned = widget);
			}

			@Override
			public void remove()
			{
				if( returned != null )
					CenterPanel.this.remove( returned );
			}
		};
	}

	@Override
	public boolean remove( Widget w )
	{
		// Validate.
		if( widget != w )
			return false;

		// Orphan.
		try
		{
			orphan( w );
		}
		finally
		{
			// Physical detach.
			containerElement.removeChild( w.getElement() );

			// Logical detach.
			widget = null;
		}
		return true;
	}

	@Override
	public void setWidget( IsWidget w )
	{
		setWidget( asWidgetOrNull( w ) );
	}

	/**
	 * Sets this panel's widget. Any existing child widget will be removed.
	 * 
	 * @param w
	 *            the panel's new widget, or <code>null</code> to clear the
	 *            panel
	 */
	@Override
	public void setWidget( Widget w )
	{
		// Validate
		if( w == widget )
			return;

		// Detach new child.
		if( w != null )
			w.removeFromParent();

		// Remove old child.
		if( widget != null )
			remove( widget );

		// Logical attach.
		widget = w;

		if( w != null )
		{
			// Physical attach.
			DOM.appendChild( containerElement, widget.getElement() );

			adopt( w );
		}
	}
}