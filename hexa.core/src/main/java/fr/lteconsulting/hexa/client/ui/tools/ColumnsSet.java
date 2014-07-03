package fr.lteconsulting.hexa.client.ui.tools;

import java.util.ArrayList;

import fr.lteconsulting.hexa.client.ui.treetable.Row;
import fr.lteconsulting.hexa.client.ui.treetable.TreeTable;

public class ColumnsSet<T>
{
	private final ArrayList<IColumn<T>> columns = new ArrayList<IColumn<T>>();

	public void addColumn( IColumn<T> columnManager )
	{
		columns.add( columnManager );
	}

	public int getNbColumns()
	{
		return columns.size();
	}

	public void setHeaders( TreeTable table )
	{
		for( int i = 0; i < columns.size(); i++ )
			table.setHeader( i, columns.get( i ).getTitle() );
	}

	public void fillRow( Row row, T record )
	{
		for( int i = 0; i < columns.size(); i++ )
			columns.get( i ).fillCell( row.getCell( i ), record );
	}

	// simple print
	public String getTitle( int column )
	{
		return columns.get( column ).getTitle();
	}

	public void fillCell( int column, Row row, T record )
	{
		columns.get( column ).fillCell( row.getCell( column ), record );
	}

	public IEditor editCell( int column, T record )
	{
		return columns.get( column ).editCell( record );
	}
}
