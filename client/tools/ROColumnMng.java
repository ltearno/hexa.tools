package com.hexa.client.tools;

import com.hexa.client.tools.ColumnsSet.IColumnMng;
import com.hexa.client.ui.ITreeTableEditorManager;
import com.hexa.client.ui.TreeTable;
import com.google.gwt.user.client.ui.Widget;

public abstract class ROColumnMng<T> implements IColumnMng<T>
{
	private String title;

	public ROColumnMng( String title )
	{
		this.title = title;
	}

	// simple print
	public final String getTitle()
	{
		return title;
	}

	abstract public void fillCell( int ordinal, TreeTable table, Object item, T record );

	// from TreeTableEditorManager
	public final void getAsyncCellEditorWidget( int ordinal, Object item, T record, ITreeTableEditorManager callback )
	{
	}

	public final void onCellEditorValidation( int ordinal, Widget editor, final TreeTable table, final Object item, T record )
	{
	}
}