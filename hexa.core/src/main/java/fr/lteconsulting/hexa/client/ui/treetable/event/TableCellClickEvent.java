package fr.lteconsulting.hexa.client.ui.treetable.event;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import fr.lteconsulting.hexa.client.ui.treetable.Row;

public class TableCellClickEvent extends GwtEvent<TableCellClickEvent.TableCellClickHandler>
{
	public interface TableCellClickHandler extends EventHandler
	{
		void onTableCellClick( Row row, int column, ClickEvent clickEvent );
	}

	private final static Type<TableCellClickHandler> TYPE = new Type<>();

	private final Row row;
	private final int column;
	private final ClickEvent clickEvent;

	public TableCellClickEvent( Row row, int column, ClickEvent clickEvent )
	{
		this.row = row;
		this.column = column;
		this.clickEvent = clickEvent;
	}

	public static Type<TableCellClickHandler> getType()
	{
		return TYPE;
	}

	@Override
	public Type<TableCellClickHandler> getAssociatedType()
	{
		return TYPE;
	}

	@Override
	protected void dispatch( TableCellClickHandler handler )
	{
		handler.onTableCellClick( row, column, clickEvent );
	}
}
