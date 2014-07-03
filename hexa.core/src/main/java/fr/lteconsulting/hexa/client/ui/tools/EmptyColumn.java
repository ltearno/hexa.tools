package fr.lteconsulting.hexa.client.ui.tools;

import fr.lteconsulting.hexa.client.ui.miracle.Printer;
import fr.lteconsulting.hexa.client.ui.tools.IColumn;
import fr.lteconsulting.hexa.client.ui.tools.IEditor;

public class EmptyColumn<T> implements IColumn<T>
{
	@Override
	public void fillCell( Printer printer, T record )
	{
	}

	@Override
	public IEditor editCell( T record )
	{
		return null;
	}

	@Override
	public String getTitle()
	{
		return "";
	}
}
