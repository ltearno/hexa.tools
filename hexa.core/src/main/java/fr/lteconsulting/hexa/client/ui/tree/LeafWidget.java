package fr.lteconsulting.hexa.client.ui.tree;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LeafWidget<NODE_DATA, LEAF_DATA> extends LeafWidgetBase<NODE_DATA, LEAF_DATA>
{
	Anchor add = new Anchor( " combine " );
	// ImageButton add = new ImageButton( Resources.get().images.add(),
	// "Combine with another condition" );
	Anchor delete = new Anchor( " del " );
	// ImageButton delete = new ImageButton( Resources.get().images.delete(),
	// "Delete from parent" );
	Widget widget = null;

	HorizontalPanel panel = new HorizontalPanel();

	public LeafWidget( boolean fReadOnly )
	{
		add.getElement().getStyle().setPaddingLeft( 5, Unit.PX );
		delete.getElement().getStyle().setPaddingLeft( 5, Unit.PX );

		if( !fReadOnly )
		{
			panel.add( add );
			panel.add( delete );
		}

		initWidget( panel );

		delete.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				event.preventDefault();
				event.stopPropagation();
				if( parent != null )
					parent.removeChild( LeafWidget.this );
			}
		} );

		add.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				event.preventDefault();
				event.stopPropagation();

				if( parent == null )
					return;

				// instead of our parent having us as a child,
				// replace with a node having us as a child
				INodeWidget<NODE_DATA, LEAF_DATA> node = parent.createEmptyNode();
				parent.replaceChild( LeafWidget.this, node );

				node.addChild( LeafWidget.this );

				node.childWantsAdd( add );
			}
		} );
	}

	@Override
	public void setWidget( Widget widget )
	{
		if( this.widget != null )
			panel.remove( 0 );
		if( widget != null )
			panel.insert( widget, 0 );
		this.widget = widget;
	}
}
