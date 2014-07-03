package fr.lteconsulting.hexa.client.ui.tools;

import fr.lteconsulting.hexa.client.ui.miracle.Printer;
import fr.lteconsulting.hexa.client.ui.tools.IEditor;

public interface IColumn<T>
{
	public String getTitle();

	public void fillCell( Printer printer, T record );

	// begins a editing session.
	// callee should return an IEditor implementation
	// it is its role to update the data if needed.
	// when finished, callee should call the IEditorHost.finishedEdition();
	// then the host will redraw the cell
	IEditor editCell( T record );
}
