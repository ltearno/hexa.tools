package fr.lteconsulting.hexa.client.ui.treetable.event;

import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import fr.lteconsulting.hexa.client.ui.treetable.Row;

public class TableCellDoubleClickEvent extends GwtEvent<TableCellDoubleClickEvent.TableCellDoubleClickHandler>
{
	public interface TableCellDoubleClickHandler extends EventHandler
	{
		void onTableCellDoubleClick( Row row, int column, DoubleClickEvent clickEvent );
	}

	private final static Type<TableCellDoubleClickHandler> TYPE = new Type<>();

	private final Row row;
	private final int column;
	private final DoubleClickEvent clickEvent;

	public TableCellDoubleClickEvent( Row row, int column, DoubleClickEvent clickEvent )
	{
		this.row = row;
		this.column = column;
		this.clickEvent = clickEvent;
	}

	public static Type<TableCellDoubleClickHandler> getType()
	{
		return TYPE;
	}

	@Override
	public Type<TableCellDoubleClickHandler> getAssociatedType()
	{
		return TYPE;
	}

	@Override
	protected void dispatch( TableCellDoubleClickHandler handler )
	{
		handler.onTableCellDoubleClick( row, column, clickEvent );
	}
}
