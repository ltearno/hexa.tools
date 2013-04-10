package com.hexa.client.tools;

import java.util.ArrayList;

import com.hexa.client.ui.ITreeTableEditorManager;
import com.hexa.client.ui.TreeTable;
import com.google.gwt.user.client.ui.Widget;

public class ColumnsSet<T>
{
	public interface IColumnMng<T>
	{
		// simple print
		public String getTitle();

		public void fillCell( int ordinal, TreeTable table, Object item, T record );

		// from TreeTableEditorManager
		void getAsyncCellEditorWidget( int ordinal, Object item, T record, ITreeTableEditorManager callback );

		void onCellEditorValidation( int ordinal, Widget editor, final TreeTable table, final Object item, T record );
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

	public void fillRow( TreeTable table, Object item, T record )
	{
		for( int i = 0; i < columns.size(); i++ )
			columns.get( i ).fillCell( i, table, item, record );
	}

	// simple print
	public String getTitle( int column )
	{
		return columns.get( column ).getTitle();
	}

	public void fillCell( int column, TreeTable table, Object item, T record )
	{
		columns.get( column ).fillCell( column, table, item, record );
	}

	// from TreeTableEditorManager
	public void getAsyncCellEditorWidget( int column, Object item, T record, ITreeTableEditorManager callback )
	{
		columns.get( column ).getAsyncCellEditorWidget( column, item, record, callback );
	}

	public void onCellEditorValidation( int column, Widget editor, final TreeTable table, final Object item, T record )
	{
		columns.get( column ).onCellEditorValidation( column, editor, table, item, record );
	}
}
