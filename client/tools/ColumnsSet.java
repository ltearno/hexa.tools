package com.hexa.client.tools;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Widget;
import com.hexa.client.ui.ITreeTableEditorManager;
import com.hexa.client.ui.treetable.TreeTable;
import com.hexa.client.ui.treetable.TreeTable.Row;

public class ColumnsSet<T>
{
	public interface IColumnMng<T>
	{
		// simple print
		public String getTitle();

		public void fillCell( int ordinal, Row row, T record );

		// from TreeTableEditorManager
		void getAsyncCellEditorWidget( int ordinal, Row row, T record, ITreeTableEditorManager callback );

		void onCellEditorValidation( int ordinal, Widget editor, Row row, T record );
	}

	private ArrayList<IColumnMng<T>> columns = new ArrayList<IColumnMng<T>>();

	public ColumnsSet()
	{
	}

	public void addColumn( IColumnMng<T> columnManager )
	{
		columns.add( columnManager );
	}

	public int getLastOrdinal()
	{
		return columns.size() - 1;
	}

	public void setHeaders( TreeTable table )
	{
		for( int i = 0; i < columns.size(); i++ )
			table.setHeader( i, columns.get( i ).getTitle() );
	}

	public void fillRow( Row row, T record )
	{
		for( int i = 0; i < columns.size(); i++ )
			columns.get( i ).fillCell( i, row, record );
	}

	// simple print
	public String getTitle( int column )
	{
		return columns.get( column ).getTitle();
	}

	public void fillCell( int column, Row row, T record )
	{
		columns.get( column ).fillCell( column, row, record );
	}

	// from TreeTableEditorManager
	public void getAsyncCellEditorWidget( int column, Row row, T record, ITreeTableEditorManager callback )
	{
		columns.get( column ).getAsyncCellEditorWidget( column, row, record, callback );
	}

	public void onCellEditorValidation( int column, Widget editor, Row row, T record )
	{
		columns.get( column ).onCellEditorValidation( column, editor, row, record );
	}
}
