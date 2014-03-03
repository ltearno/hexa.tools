package com.hexa.client.tools;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Widget;
import com.hexa.client.ui.treetable.TreeTable;
import com.hexa.client.ui.treetable.TreeTable.Row;

public class ColumnsSet<T>
{
	// an editor has a widget and
	// can instruct container to show a cancel button around
	public interface IEditor
	{
		Widget getWidget();

		boolean isShowCancel();
	}

	public interface IColumnMng<T>
	{
		public String getTitle();

		public void fillCell( int ordinal, Row row, T record );

		IEditor editCell( T record );

		void onCellEditorValidation( int ordinal, Widget editor, Row row, T record );
	}

	public static class Editor implements IEditor
	{
		private final Widget widget;
		private final boolean isShowCancel;

		public Editor( Widget widget, boolean isShowCancel )
		{
			this.widget = widget;
			this.isShowCancel = isShowCancel;
		}

		@Override
		public Widget getWidget()
		{
			return widget;
		}

		@Override
		public boolean isShowCancel()
		{
			return isShowCancel;
		}
	}

	private final ArrayList<IColumnMng<T>> columns = new ArrayList<IColumnMng<T>>();

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

	public IEditor editCell( int column, T record )
	{
		return columns.get( column ).editCell( record );
	}

	public void onCellEditorValidation( int column, Widget editor, Row row, T record )
	{
		columns.get( column ).onCellEditorValidation( column, editor, row, record );
	}
}
