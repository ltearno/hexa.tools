package fr.lteconsulting.hexa.client.ui.tools;

import fr.lteconsulting.hexa.client.ui.miracle.Size;

public interface IEditorHost
{
	// allows the editors to ask what is the best pixel size to adopt
	// (so that in a table for example, the layout keeps being beautiful)
	Size getPreferredSize();

	// editor can say it has finished its job
	void finishedEdition();
}
