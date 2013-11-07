package com.hexa.client.ui.miracle;

public abstract class HtmlColumn<T> implements IColumnMng<T>
{
	protected abstract String getHtml( T data );

	PrintsOn<Void> hdrPrintsOn;
	PrintsOn<T> printsOn;

	public HtmlColumn( final String title )
	{
		hdrPrintsOn = new PrintsOn<Void>()
		{
			@Override
			public boolean print( Void data, Printer printer )
			{
				printer.setText( title );
				return false;
			}
		};

		printsOn = new PrintsOn<T>()
		{
			@Override
			public boolean print( T data, Printer printer )
			{
				printer.setHTML( getHtml( data ) );
				return false;
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
