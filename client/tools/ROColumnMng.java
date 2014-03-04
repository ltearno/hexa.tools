package com.hexa.client.tools;

import com.hexa.client.tools.ColumnsSet.IColumnMng;
import com.hexa.client.tools.ColumnsSet.IEditor;
import com.hexa.client.ui.treetable.TreeTable.Row;

public abstract class ROColumnMng<T> implements IColumnMng<T>
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
	abstract public void fillCell( int ordinal, Row row, T record );

	@Override
	public final IEditor editCell( T record )
	{
		return null;
	}
}