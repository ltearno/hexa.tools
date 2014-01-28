package com.hexa.client.ui.treetable;

import com.google.gwt.event.dom.client.ClickEvent;
import com.hexa.client.ui.treetable.TreeTableBase.Row;

/**
 * @author Arnaud
 * 
 */
public interface TreeTableHandler
{
	public void onTableHeaderClick( int column, ClickEvent event );

	public void onTableCellClick( Row item, int column, ClickEvent event );
}
