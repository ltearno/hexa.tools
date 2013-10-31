/**
 *
 */
package com.hexa.client.ui.treetable;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.hexa.client.comm.HexaFramework;
import com.hexa.client.ui.widget.ImageButton;

/**
 * @author Arnaud
 *
 */
public class TreeTable extends TreeTableBase
{
	@UiConstructor
	public TreeTable( ImageResource treeMinus, ImageResource treePlus )
	{
		super();
	}

	public TreeTable()
	{
		super();
	}

	@Override
	public void setText( Object item, int column, String text )
	{
		if( column == 0 )
			setWidget( item, column, new Label( text ) );
		else
			super.setText( item, column, text );
	}

	@Override
	public void setText( Object item, int column, int text )
	{
		setText( item, column, String.valueOf( text ) );
	}

	@Override
	public void setText( Object item, int column, double text )
	{
		setText( item, column, String.valueOf( text ) );
	}

	@Override
	public void setHTML( Object item, int column, String text )
	{
		if( column == 0 )
			setWidget( item, column, new HTML( text ) );
		else
			super.setHTML( item, column, text );
	}

	@Override
	public void setWidget( Object item, int column, Widget w )
	{
		if( column == 0 )
			w = new ExpShrinkWidget( (TreeTableBase.Item) item, w );

		super.setWidget( item, column, w );
	}

	class ExpShrinkWidget extends Composite implements TreeTableBase.IItemStateCallback, ClickHandler
	{
		TreeTableBase.Item item;

		ImageResource treePlus = HexaFramework.images.treePlus();
		ImageResource treeMinus = HexaFramework.images.treeMinus();
		ImageResource blank = HexaFramework.images.blank();

		ImageButton im = new ImageButton( blank, "Expand" );

		public ExpShrinkWidget( Item item, Widget child )
		{
			this.item = item;

			HorizontalPanel panel = new HorizontalPanel();
			panel.add( im );
			panel.add( child );
			initWidget( panel );

			item.addStateChangeCallback( this );
			update();

			im.addClickHandler( this );
		}

		void update()
		{
			if( item.getChilds().size() == 0 )
			{
				im.getElement().getStyle().setDisplay( Display.NONE );
				// im.setResource( blank );
				return;
			}

			im.getElement().getStyle().clearDisplay();

			if( !item.getExpanded() )
				im.setResource( treePlus );
			else
				im.setResource( treeMinus );
		}

		@Override
		public void onItemStateChange()
		{
			update();
		}

		@Override
		public void onClick( ClickEvent event )
		{
			event.preventDefault();
			event.stopPropagation();

			item.setExpanded( !item.getExpanded() );
		}
	}
}