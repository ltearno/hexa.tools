package com.hexa.client.tools;

import com.google.gwt.user.client.ui.Widget;
import com.hexa.client.tools.ColumnsSet.IColumnMng;
import com.hexa.client.ui.ITreeTableEditorManager;
import com.hexa.client.ui.treetable.TreeTable.Row;

public abstract class ROColumnMng<T> implements IColumnMng<T>
{
	private String title;

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

	// from TreeTableEditorManager
	@Override
	public final void getAsyncCellEditorWidget( int ordinal, Row row, T record, ITreeTableEditorManager callback )
	{
	}

	@Override
	public final void onCellEditorValidation( int ordinal, Widget editor, Row row, T record )
	{
	}
}