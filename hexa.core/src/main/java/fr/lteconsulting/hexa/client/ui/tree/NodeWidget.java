package fr.lteconsulting.hexa.client.ui.tree;

import java.util.ArrayList;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.comm.HexaFramework;
import fr.lteconsulting.hexa.client.common.Pair;
import fr.lteconsulting.hexa.client.css.HexaCss;
import fr.lteconsulting.hexa.client.tools.HTMLStream;
import fr.lteconsulting.hexa.client.ui.widget.ImageButton;
import fr.lteconsulting.hexa.client.ui.widget.ListBoxDiscrete;

public class NodeWidget<NODE_DATA, LEAF_DATA> extends NodeWidgetBase<NODE_DATA, LEAF_DATA>
{
	public interface INodeWidgetFactory<NODE_DATA, LEAF_DATA>
	{
		NodeWidget<NODE_DATA, LEAF_DATA> create();
	}

	interface Css extends HexaCss
	{
		static final Css CSS = GWT.create( Css.class );

		String main();
	}

	ArrayList<Pair<NODE_DATA, String>> operators;

	HTMLStream stream = new HTMLStream();

	VerticalPanel panel = new VerticalPanel();
	HorizontalPanel buttonBar = new HorizontalPanel();
	ImageButton add = new ImageButton( HexaFramework.images.ellipsis(), "Add a new condition" );
	ImageButton delete = new ImageButton( HexaFramework.images.delete(), "Delete from parent" );
	ListBoxDiscrete<NODE_DATA> opsLb = new ListBoxDiscrete<NODE_DATA>( HexaFramework.images.dropdown(), HexaFramework.images.dropdown() );

	INodeWidgetFactory<NODE_DATA, LEAF_DATA> nodeFactory;

	public void addButton( Widget w )
	{
		w.getElement().getStyle().setDisplay( Display.NONE );
		buttonBar.add( w );
	}

	public NodeWidget( ArrayList<Pair<NODE_DATA, String>> operators, XNodeWidget<NODE_DATA, LEAF_DATA> callback, INodeWidgetFactory<NODE_DATA, LEAF_DATA> nodeFactory, boolean fReadOnly )
	{
		super( callback );

		this.nodeFactory = nodeFactory;

		this.operators = operators;

		boolean selected = false;
		for( Pair<NODE_DATA, String> op : operators )
		{
			opsLb.addItem( op.last, op.first );
			if( !selected )
				opsLb.setSelected( op.first );
			selected = true;
		}

		opsLb.setEnabled( !fReadOnly );

		// build ui
		stream.addInline( opsLb );
		if( !fReadOnly )
			stream.addRight( delete ).clFl();
		stream.addDown( panel );
		buttonBar.add( add );
		if( !fReadOnly )
			stream.addDown( buttonBar );
		stream.setStylePrimaryName( Css.CSS.main() );

		initWidget( stream );
		updateUI();

		add.addMouseOverHandler( new MouseOverHandler()
		{
			@Override
			public void onMouseOver( MouseOverEvent event )
			{
				add.setResource( HexaFramework.images.add() );
				
				int nb = buttonBar.getWidgetCount();
				for( int i = 0; i < nb; i++ )
				{
					Widget w = buttonBar.getWidget( i );
					if( w != add )
						w.getElement().getStyle().clearDisplay();
				}
			}
		} );

		buttonBar.addDomHandler( new MouseOutHandler()
		{
			@Override
			public void onMouseOut( MouseOutEvent event )
			{
				add.setResource( HexaFramework.images.ellipsis() );
				
				int nb = buttonBar.getWidgetCount();
				for( int i = 0; i < nb; i++ )
				{
					Widget w = buttonBar.getWidget( i );
					if( w != add )
						w.getElement().getStyle().setDisplay( Display.NONE );
				}
			}
		}, MouseOutEvent.getType() );

		delete.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				if( parent != null )
				{
					NodeWidget.this.callback.onWantDelete( parent, NodeWidget.this, delete );
					// parent.removeChild( NodeWidget.this );
				}
			}
		} );

		add.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				beginNewChildProcess( add );
			}
		} );
	}

	public NodeWidget( boolean vue, ArrayList<Pair<NODE_DATA, String>> operators, XNodeWidget<NODE_DATA, LEAF_DATA> callback, INodeWidgetFactory<NODE_DATA, LEAF_DATA> nodeFactory )
	{
		super( callback );

		this.nodeFactory = nodeFactory;

		this.operators = operators;

		boolean selected = false;
		for( Pair<NODE_DATA, String> op : operators )
		{
			opsLb.addItem( op.last, op.first );
			if( !selected )
				opsLb.setSelected( op.first );
			selected = true;
		}

		// build ui
		stream.addInline( opsLb );
		stream.addDown( panel );
		stream.setStylePrimaryName( Css.CSS.main() );

		initWidget( stream );
		updateUI();

		delete.setVisible( false );

		add.setVisible( false );
	}

	@Override
	public void setData( NODE_DATA data )
	{
		opsLb.setSelected( data );
	}

	@Override
	public NODE_DATA getData()
	{
		return opsLb.getSelected();
	}

	@Override
	public INodeWidget<NODE_DATA, LEAF_DATA> createEmptyNode()
	{
		return nodeFactory.create();
		// return new NodeWidget<NODE_DATA, LEAF_DATA>( operators, callback );
	}

	private void updateUI()
	{
		if( children.size() < 2 )
		{
			panel.getElement().getStyle().setPaddingLeft( 0, Unit.PX );
			opsLb.getElement().getStyle().setDisplay( Display.NONE );
			delete.getElement().getStyle().setDisplay( Display.NONE );
		}
		else
		{
			panel.getElement().getStyle().setPaddingLeft( 10, Unit.PX );
			opsLb.getElement().getStyle().setDisplay( Display.INLINE_BLOCK );
			delete.getElement().getStyle().setDisplay( Display.INLINE_BLOCK );
		}
	}

	@Override
	void addChildWidget( IsWidget isWidget )
	{
		panel.add( isWidget );
		updateUI();
	}

	@Override
	void removeChildWidget( IsWidget isWidget )
	{
		panel.remove( isWidget );
		updateUI();
	}

	@Override
	void replaceChildWidget( IsWidget oldIsWidget, IsWidget newIsWidget )
	{
		int idx = panel.getWidgetIndex( oldIsWidget );
		panel.remove( idx );
		panel.insert( newIsWidget, idx );
	}
}