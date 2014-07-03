package fr.lteconsulting.hexa.client.ui.miracle;

public abstract class TextColumn<T> implements IColumnMng<T>
{
	protected abstract String getText( T data );

	PrintsOn<Void> hdrPrintsOn;
	PrintsOn<T> printsOn;

	public TextColumn( final String title )
	{
		hdrPrintsOn = new PrintsOn<Void>()
		{
			@Override
			public void print( Void data, Printer printer )
			{
				printer.setText( title );
			}
		};

		printsOn = new PrintsOn<T>()
		{
			@Override
			public void print( T data, Printer printer )
			{
				printer.setText( getText( data ) );
			}
		};
	}

	@Override
	public PrintsOn<T> getPrintsOn()
	{
		return printsOn;
	}

	@Override
	public Edits<T> getEdits()
	{
		return null;
	}

	@Override
	public CellClickMng<T> getClicks()
	{
		return null;
	}

	@Override
	public PrintsOn<Void> getHdrPrintsOn()
	{
		return hdrPrintsOn;
	}

	@Override
	public CellClickMng<Void> getHdrClickMng()
	{
		return null;
	}
}
