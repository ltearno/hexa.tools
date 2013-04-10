/**
 * 
 */
package com.hexa.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.hexa.client.ui.TreeTableBase.Item;

/**
 * @author Arnaud
 *
 */
public interface TreeTableHandler
{
	public void onTableHeaderClick( int column, ClickEvent event );
	public void onTableCellClick( Item item, int column, ClickEvent event );
}
