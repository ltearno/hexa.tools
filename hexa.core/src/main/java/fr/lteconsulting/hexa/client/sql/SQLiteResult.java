package fr.lteconsulting.hexa.client.sql;

import java.util.Iterator;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class SQLiteResult implements Iterable<SQLiteResult.Row>
{
	private final JSONObject root;

	public SQLiteResult( JavaScriptObject jso )
	{
		this.root = new JSONObject( jso );
	}
	
	public int size()
	{
		return root.size();
	}

	public Row getRow( int rowIdx )
	{
		JSONArray row = root.get( "" + rowIdx ).isArray();
		return new Row( row );
	}

	public static class Cell
	{
		public String column;
		public String value;
	}

	public static class Row implements Iterable<Cell>
	{
		JSONArray row;

		Row( JSONArray row )
		{
			this.row = row;
		}

		public Row()
		{
			row = new JSONArray();
		}

		public void addCell( String columnName, String value )
		{
			JSONObject cell = new JSONObject();
			cell.put( "column", new JSONString( columnName ) );
			cell.put( "value", new JSONString( value ) );
			row.set( row.size(), cell );
		}

		public String getColumnValue( String columnName )
		{
			for( int i=0; i<row.size(); i++ )
			{
				JSONObject cellJson = row.get( i ).isObject();
				if( cellJson.get( "column" ).isString().stringValue().equals( columnName ) )
					return cellJson.get( "value" ).isString().stringValue();
			}

			return null;
		}

		@Override
		public String toString()
		{
			return row.toString();
		}

		@Override
		public Iterator<Cell> iterator()
		{
			return new Iterator<SQLiteResult.Cell>()
			{
				int current = 0;

				@Override
				public void remove()
				{
					assert false;
				}

				@Override
				public Cell next()
				{
					JSONObject cellJson = row.get( current ).isObject();
					current++;

					Cell cell = new Cell();
					cell.column = cellJson.get( "column" ).isString().stringValue();
					cell.value = cellJson.get( "value" ).isString().stringValue();

					return cell;
				}

				@Override
				public boolean hasNext()
				{
					return current < row.size();
				}
			};
		}
	}

	@Override
	public Iterator<Row> iterator()
	{
		return new Iterator<SQLiteResult.Row>()
		{
			int current = 0;

			@Override
			public void remove()
			{
				assert false;
			}

			@Override
			public Row next()
			{
				JSONArray row = root.get( "" + current ).isArray();
				current++;
				return new Row( row );
			}

			@Override
			public boolean hasNext()
			{
				return current < root.size();
			}
		};
	}
}
