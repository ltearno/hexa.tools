package fr.lteconsulting.hexa.client.ui.treetable.event;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class TableHeaderClickEvent extends GwtEvent<TableHeaderClickEvent.TableHeaderClickHandler>
{
	public interface TableHeaderClickHandler extends EventHandler
	{
		void onTableHeaderClick( int column, ClickEvent clickEvent );
	}

	private final static Type<TableHeaderClickHandler> TYPE = new Type<>();

	private final int column;
	private final ClickEvent clickEvent;

	public TableHeaderClickEvent( int column, ClickEvent clickEvent )
	{
		this.column = column;
		this.clickEvent = clickEvent;
	}

	public static Type<TableHeaderClickHandler> getType()
	{
		return TYPE;
	}

	@Override
	public Type<TableHeaderClickHandler> getAssociatedType()
	{
		return TYPE;
	}

	@Override
	protected void dispatch( TableHeaderClickHandler handler )
	{
		handler.onTableHeaderClick( column, clickEvent );
	}
}
