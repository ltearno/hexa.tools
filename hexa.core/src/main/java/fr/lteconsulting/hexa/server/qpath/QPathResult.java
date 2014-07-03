package fr.lteconsulting.hexa.server.qpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class QPathResult implements Iterable<QPathResult.QPathResultRow>
{
	public interface QPathResultRow
	{
		<T> T get( String field );
	}

	private int colCount;
	HashMap<String, Integer> cols;
	String columnsNames[];

	private int rowCount;
	private Object[][] rows;

	public QPathResult( DBResults dbRes )
	{
		colCount = dbRes.getColumnCount();
		rowCount = dbRes.getRowCount();

		// reference fields
		columnsNames = new String[colCount];
		cols = new HashMap<String, Integer>();
		for( int c = 0; c < colCount; c++ )
		{
			String colName = dbRes.getColumnName( c );
			cols.put( colName, c );
			columnsNames[c] = colName;
		}

		// copy
		if( rowCount > 0 && colCount > 0 )
		{
			rows = new Object[rowCount][];
			int row = 0;
			while( dbRes.next() )
			{
				Object preparedRow[] = new Object[colCount];

				for( int c = 0; c < colCount; c++ )
					preparedRow[c] = dbRes.getObject( c );

				rows[row] = preparedRow;
				row++;
			}
		}
		else
		{
			rowCount = 0;
			colCount = 0;
		}
	}

	public int getNbRows()
	{
		return rowCount;
	}

	public int getNbCols()
	{
		return colCount;
	}

	public String[] getColumnNames()
	{
		return columnsNames;
	}

	@SuppressWarnings( "unchecked" )
	public <T> T getValue( int row, String field )
	{
		if( row >= rows.length )
			return null;
		Integer fieldIdx = cols.get( field );
		if( fieldIdx == null )
			return null;

		return (T) rows[row][fieldIdx];
	}

	@SuppressWarnings( "unchecked" )
	public <T> ArrayList<T> getList( String field )
	{
		ArrayList<T> res = new ArrayList<T>();

		Integer fieldIdx = cols.get( field );
		if( fieldIdx == null )
			return null;

		int rowCount = rows.length;
		for( int r = 0; r < rowCount; r++ )
			res.add( (T) rows[r][fieldIdx] );

		return res;
	}

	@Override
	public Iterator<QPathResultRow> iterator()
	{
		return new RowIterator();
	}

	private class RowIterator implements Iterator<QPathResultRow>, QPathResultRow
	{
		int row = -1;

		/*
		 * Implementation of Iterator<QPathResultRow>
		 * 
		 * @see java.util.Iterator#hasNext()
		 */

		@Override
		public boolean hasNext()
		{
			return row < (rowCount - 1);
		}

		@Override
		public QPathResultRow next()
		{
			assert hasNext() : "next() can only be called if there is a next row to get !";
			row++;

			return this;
		}

		@Override
		public void remove()
		{
			assert false : "Cannot modify a QPathResult set !";
		}

		/*
		 * Implementation of QPathResultRow
		 * 
		 * @see
		 * fr.lteconsulting.hexa.server.qpath.QPathResult.QPathResultRow#get(java.lang.String
		 * )
		 */

		@Override
		public <T> T get( String field )
		{
			return getValue( row, field );
		}

	}

	/*
	 * public <I, T> T getValueIndexed( String indexField, I indexedValue,
	 * String field ) { return null; }
	 */
}
