package fr.lteconsulting.hexa.client.sql;

import java.util.Iterator;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

public class SQLiteResult implements Iterable<SQLiteResult.Row>
{
	private final JSONObject root;

	public SQLiteResult( JavaScriptObject jso )
	{
		this.root = new JSONObject( jso );
	}

	public class Cell
	{
		public String column;
		public String value;
	}

	public class Row implements Iterable<Cell>
	{
		JSONArray row;

		Row( JSONArray row )
		{
			this.row = row;
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
