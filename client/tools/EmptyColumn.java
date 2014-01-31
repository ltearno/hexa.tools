package com.hexa.client.tools;

import com.google.gwt.user.client.ui.Widget;
import com.hexa.client.tools.ColumnsSet.IColumnMng;
import com.hexa.client.ui.ITreeTableEditorManager;
import com.hexa.client.ui.treetable.TreeTable.Row;

public class EmptyColumn<T> implements IColumnMng<T>
{
	@Override
	public void fillCell( int ordinal, Row row, T record )
	{
	}

	@Override
	public void getAsyncCellEditorWidget( int ordinal, Row row, T record, ITreeTableEditorManager callback )
	{
	}

	@Override
	public String getTitle()
	{
		return "";
	}

	@Override
	public void onCellEditorValidation( int ordinal, Widget editor, Row row, T record )
	{
	}

}
