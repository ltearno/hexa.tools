package fr.lteconsulting.hexa.client.ui.containers;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import fr.lteconsulting.hexa.client.interfaces.IStackPanel;
import fr.lteconsulting.hexa.client.interfaces.IStackPanelRow;

public class AbsoluteStackPanel extends Composite implements IStackPanel
{
	FlowPanel m_stack = new FlowPanel();

	public AbsoluteStackPanel()
	{
		m_stack.setWidth( "100%" );
		initWidget( m_stack );
	}

	private class RowItem implements IStackPanelRow
	{
		AbsolutePanel row;

		RowItem()
		{
			row = new AbsolutePanel();
			row.setWidth( "100%" );
		}

		@Override
		public void setHeight( int height )
		{
			row.setHeight( height + "px" );
		}

		@Override
		public void addItem( Widget w, int x, int y, int sx, int sy )
		{
			row.add( w, x, y );
			w.setPixelSize( sx, sy );
		}

		@Override
		public void repositionWidget( Widget w, int x, int y, int sx, int sy )
		{
			row.setWidgetPosition( w, x, y );
			w.setPixelSize( sx, sy );
		}

		@Override
		public void removeItem( Widget w )
		{
			row.remove( w );
		}

		@Override
		public void clearAll()
		{
			row.clear();
		}

		@Override
		public void setVisible( boolean visible )
		{
			row.setVisible( visible );
		}

		@Override
		public IStackPanelRow createSubRow()
		{
			RowItem item = new RowItem();
			m_stack.insert( item.row, m_stack.getWidgetIndex( row ) + 1 );

			return item;
		}
	}

	@Override
	public IStackPanelRow addRow()
	{
		RowItem item = new RowItem();

		m_stack.add( item.row );

		return item;
	}

	@Override
	public void clear()
	{
		m_stack.clear();
	}
}
