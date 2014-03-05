package com.hexa.client.tools;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Widget;
import com.hexa.client.ui.miracle.Size;
import com.hexa.client.ui.treetable.TreeTable;
import com.hexa.client.ui.treetable.TreeTable.Row;

public class ColumnsSet<T>
{
	public interface IEditorHost
	{
		// allows the editors to ask what is the best pixel size to adopt 
		// (so that in a table for example, the layout keeps being beautiful)
		Size getPreferredSize();
		
		// editor can say it has finished its job
		void finishedEdition();
	}

	// an editor has a widget and
	// can instruct container to show a cancel button around
	public interface IEditor
	{
		void setHost( IEditorHost editorHost );
		Widget getWidget();
	}

	public interface IColumnMng<T>
	{
		public String getTitle();

		public void fillCell( int ordinal, Row row, T record );

		// begins a editing session.
		// callee should return an IEditor implementation
		// it is its role to update the data if needed.
		// when finished, callee should call the IEditorHost.finishedEdition();
		// then the host will redraw the cell
		IEditor editCell( T record );
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
}
