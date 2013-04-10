package com.hexa.client.ui.miracle;

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

	public void print( ArrayList<T> data )
	{
		table.clear( true );

		CellInFlexTablePrinter cp = new CellInFlexTablePrinter( table, 0, 0 );

		// each cell
		for( int j = 0; j < data.size(); j++ )
		{
			T d = data.get( j );

			cp.row = j;

			// each column
			for( int i = 0; i < cols.size(); i++ )
			{
				cp.col = i;

				boolean fStillInUse = cols.get( i ).print( d, cp );

				if( fStillInUse )
					cp = new CellInFlexTablePrinter( table, j, 0 );
			}
		}
	}
}
