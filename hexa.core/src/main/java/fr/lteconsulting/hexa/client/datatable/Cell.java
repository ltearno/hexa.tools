package fr.lteconsulting.hexa.client.datatable;

import com.google.gwt.dom.client.TableCellElement;

import fr.lteconsulting.hexa.client.ui.miracle.Printer;
import fr.lteconsulting.hexa.client.ui.miracle.Size;

/**
 * This interface models a table cell
 * 
 * @author Arnaud
 */
public interface Cell extends Printer
{
	void scrollIntoView();

	void addClassName( String className );

	void removeClassName( String className );

	Cell getNextCell();

	Cell getPreviousCell();

	Row getParentRow();

	int getCellIndex();

	Size getDisplaySize();

	TableCellElement getTd();
}