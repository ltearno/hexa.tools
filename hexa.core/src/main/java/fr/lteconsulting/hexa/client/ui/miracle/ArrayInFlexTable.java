package fr.lteconsulting.hexa.client.ui.miracle;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.FlexTable;

public class ArrayInFlexTable<T> implements Prints<ArrayList<T>>
{
	FlexTable table;
	ArrayList<PrintsOn<T>> cols = new ArrayList<PrintsOn<T>>();

	public ArrayInFlexTable( FlexTable table )
	{
		this.table = table;
	}

	public void addColumn( PrintsOn<T> column )
	{
		cols.add( column );
	}

	@Override
	public void print( ArrayList<T> data )
	{
		table.clear( true );

		// each row
		for( int j = 0; j < data.size(); j++ )
		{
			T d = data.get( j );

			// each column
			for( int i = 0; i < cols.size(); i++ )
				cols.get( i ).print( d, new CellInFlexTablePrinter( table, j, i ) );
		}
	}
}
