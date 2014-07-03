package fr.lteconsulting.hexa.client.ui.tools;

import fr.lteconsulting.hexa.client.ui.miracle.Printer;
import fr.lteconsulting.hexa.client.ui.tools.IColumn;
import fr.lteconsulting.hexa.client.ui.tools.IEditor;

public abstract class ROColumnMng<T> implements IColumn<T>
{
	private final String title;

	public ROColumnMng( String title )
	{
		this.title = title;
	}

	// simple print
	@Override
	public final String getTitle()
	{
		return title;
	}

	@Override
	abstract public void fillCell( Printer printer, T record );

	@Override
	public final IEditor editCell( T record )
	{
		return null;
	}
}