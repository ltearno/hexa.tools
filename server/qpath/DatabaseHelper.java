package com.hexa.server.qpath;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import com.hexa.client.common.HexaDate;
import com.hexa.client.common.HexaDateTime;
import com.hexa.client.common.HexaTime;
import com.hexa.client.common.text.DateTimeFormat;
import com.hexa.shared.data.IdDTO;

public class DatabaseHelper
{
	private final Database db;

	private static final DateTimeFormat dateFormatter = DateTimeFormat.getFormat( "yyyy/MM/dd HH:mm:ss" );

	HashMap<String, ArrayList<String>> cacheFields;

	public DatabaseHelper( Database db )
	{
		this.db = db;
	}

	public boolean hasField( String table, String field )
	{
		DBResults res = db.sql( "SELECT * FROM " + table + " WHERE 1=0" );
		if( res == null )
			return false;

		int idx = res.getColumnIndex( field );

		return idx >= 0;
	}

	public ArrayList<String> getTableFields( String table )
	{
		if( cacheFields == null )
			cacheFields = new HashMap<String, ArrayList<String>>();

		ArrayList<String> list = cacheFields.get( table );

		if( list != null )
			return list;

		list = new ArrayList<String>();
		cacheFields.put( table, list );

		DBResults res = db.sql( "SELECT * FROM " + table + " WHERE 1=0" );
		if( res == null )
			return list;

		int n = res.getColumnCount();
		for( int i = 0; i < n; i++ )
			list.add( res.getColumnName( i ) );

		return list;
	}

	public ArrayList<String> getTables()
	{
		ArrayList<String> list = new ArrayList<String>();

		try
		{
			DBResults res = new DBResults( db.getDatabaseMetaData().getTables( db.getCurrentDatabase(), null, null, new String[] { "TABLE" } ), null );
			int idx = res.getColumnIndex( "TABLE_NAME" );
			while( res.next() )
				list.add( res.getString( idx ) );
		}
		catch( SQLException e )
		{
			e.printStackTrace();
		}

		return list;
	}

	// TODO : this one works only on MySQL server
	public boolean hasTrigger( String triggerName )
	{
		DBResults res = db.sql( "SELECT * FROM information_schema.TRIGGERS WHERE TRIGGER_NAME='$triggerName' AND TRIGGER_SCHEMA='" + db.getCurrentDatabase()
				+ "'" );
		if( res.getRowCount() == 0 )
			return false;
		return true;
	}

	public static class FieldsMap
	{
		HashMap<String, Object> map = new HashMap<String, Object>();

		public static FieldsMap create()
		{
			return new FieldsMap();
		}

		public FieldsMap p( String name, Object value )
		{
			if( value != null && value.getClass().isEnum() )
			{
				map.put( name, value.toString() );

				return this;
			}

			map.put( name, value );

			return this;
		}
	}

	public <T extends IdDTO> T insert( String table, Class<T> clazz, T item )
	{
		return insert( table, clazz, item, null );
	}

	private String getStringForObject( Object o )
	{
		if( o == null )
			return "NULL";

		if( o.getClass().isEnum() )
			return "'" + o.toString() + "'";

		if( o instanceof Date )
			return "'" + dateFormatter.format( (Date) o ) + "'";

		if( o instanceof HexaDateTime )
			return "'" + ((HexaDateTime) o).getString() + "'";

		if( o instanceof HexaDate )
			return "'" + ((HexaDate) o).getString() + "'";

		if( o instanceof HexaTime )
			return "'" + ((HexaTime) o).getString() + "'";

		return "'" + o.toString() + "'";
	}

	// TODO : return the T item. for this have to prepare for the clazz some data, to be optimized a bit
	public <T extends IdDTO> T insert( String table, Class<T> clazz, T item, FieldsMap toAppendFieldsMap )
	{
		try
		{
			FieldsMap fields = FieldsMap.create();

			Field[] classFields = clazz.getFields();
			for( int i = 0; i < classFields.length; i++ )
			{
				Field classField = classFields[i];
				if( classField.getName().equals( "id" ) )
					continue;

				fields.p( JavaDBNames.javaToDBName( classFields[i].getName() ), classField.get( item ) );
			}

			if( toAppendFieldsMap != null )
			{
				for( Entry<String, Object> e : toAppendFieldsMap.map.entrySet() )
				{
					fields.map.put( e.getKey(), e.getValue() );

					// TODO sets the item's field also
				}
			}

			int insertedId = insert( table, fields );
			item.setId( insertedId );

			return item;
		}
		catch( IllegalAccessException e )
		{
			return null;
		}
	}

	public int insert( String table, FieldsMap fields )
	{
		return insert( table, fields.map );
	}

	public int insert( String table, HashMap<String, ?> fields )
	{
		String sql;
		if( fields == null )
		{
			sql = "INSERT INTO " + table + " () VALUES ()";
		}
		else
		{
			StringBuilder fieldsSb = new StringBuilder();
			StringBuilder valuesSb = new StringBuilder();

			boolean fFirst = true;
			for( Entry<String, ?> entry : fields.entrySet() )
			{
				if( fFirst )
				{
					fFirst = false;
				}
				else
				{
					fieldsSb.append( ", " );
					valuesSb.append( ", " );
				}

				String fieldName = entry.getKey();
				Object fieldValue = entry.getValue();

				fieldsSb.append( "`" + fieldName + "`" );
				valuesSb.append( getStringForObject( fieldValue ) );
			}

			sql = "INSERT INTO " + table + " (" + fieldsSb.toString() + ") VALUES (" + valuesSb.toString() + ")";
		}

		return db.sqlInsert( sql );
	}

	public int delete( String table, String condition )
	{
		return db.sqlDelete( "DELETE FROM " + table + " WHERE " + condition );
	}

	public int update( String table, String condition, FieldsMap fields )
	{
		return update( table, condition, fields.map );
	}

	public int update( String table, String condition, HashMap<String, ?> data )
	{
		if( data == null || data.size() == 0 )
			return 0;

		StringBuilder updateSb = new StringBuilder();

		boolean fFirst = true;
		for( Entry<String, ?> entry : data.entrySet() )
		{
			if( fFirst )
				fFirst = false;
			else
				updateSb.append( ", " );

			String fieldName = entry.getKey();
			Object fieldValue = entry.getValue();

			updateSb.append( "`" + fieldName + "`=" );
			if( fieldValue == null || (fieldValue instanceof String && ((String) fieldValue).equalsIgnoreCase( "null" )) )
				updateSb.append( "NULL" );
			else
				updateSb.append( "'" + fieldValue.toString() + "'" );
		}

		String sql = "UPDATE " + table + " SET " + updateSb.toString() + " WHERE " + condition;

		return db.sqlUpdate( sql );
	}
}
