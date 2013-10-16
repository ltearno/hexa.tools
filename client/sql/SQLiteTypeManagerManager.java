package com.hexa.client.sql;

import java.util.Date;
import java.util.HashMap;

import com.hexa.client.classinfo.Field;

public class SQLiteTypeManagerManager
{
	public static abstract class SQLiteTypeManager
	{
		abstract public String createFieldSql( String fieldName );

		public String autoUpdateTimestampCreateTriggerSql( String tableName, String fieldName )
		{
			throw new IllegalStateException( "AutoUpdateTimestamp trigger not supported for this type !" );
		}

		public Boolean localRecordStateCreateTriggerSql( SQLite db, String tableName, String fieldName )
		{
			return true;
		}

		abstract public boolean appendUpdateValueSql( StringBuilder sb, Field field, Object record );

		abstract public void setFieldValueFromString( Field field, Object object, String value );
	}

	private static HashMap<Class<?>, SQLiteTypeManager> typeManagers;

	public static SQLiteTypeManager get( Class<?> clazz )
	{
		if( typeManagers == null )
		{
			typeManagers = new HashMap<Class<?>, SQLiteTypeManager>();

			typeManagers.put( int.class, new SQLiteTypeManager_int() );
			typeManagers.put( String.class, new SQLiteTypeManager_String() );
			typeManagers.put( Date.class, new SQLiteTypeManager_Date() );
		}

		return typeManagers.get( clazz );
	}
}

class SQLiteTypeManager_int extends SQLiteTypeManagerManager.SQLiteTypeManager
{
	@Override
	public String createFieldSql( String fieldName )
	{
		if( fieldName.equals( "id" ) )
			return "INTEGER PRIMARY KEY";// AUTOINCREMENT";

		return "INTEGER";
	}

	/*
	 * Créee un trigger qui permet la maj automatique d'un champ de statut (INTEGER) dont la valeur signifiera : - 0 : enregistrement créé localement - 1 :
	 * enregistrement copie conforme du serveur - 2 : enregistrement modifié localement
	 *
	 * @see com.hexa.client.sql.SQLiteTypeManagerManager.SQLiteTypeManager #localRecordStateCreateTriggerSql(java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean localRecordStateCreateTriggerSql( SQLite db, String tableName, String fieldName )
	{
		String triggerSql = "CREATE TRIGGER IF NOT EXISTS " + tableName + "_" + fieldName + "_localstate_update BEFORE UPDATE ON " + tableName
		// + " FOR EACH ROW " + "WHEN NEW." + fieldName + " IS NULL OR NEW." + fieldName + " <> 1 " + "BEGIN " + "UPDATE " + tableName + " SET "
				+ " FOR EACH ROW " + "WHEN NEW." + fieldName + " <> 1 " + "BEGIN " + "UPDATE " + tableName + " SET " + fieldName + " = 2 WHERE id=NEW.id; END";

		db.execute( triggerSql );

		return true;
	}

	@Override
	public boolean appendUpdateValueSql( StringBuilder sb, Field field, Object record )
	{
		sb.append( field.getValue( record ) );

		return true;
	}

	@Override
	public void setFieldValueFromString( Field field, Object object, String value )
	{
		field.setValue( object, Integer.parseInt( value ) );
	}
}

class SQLiteTypeManager_String extends SQLiteTypeManagerManager.SQLiteTypeManager
{
	@Override
	public String createFieldSql( String fieldName )
	{
		return "VARCHAR(100)";
	}

	@Override
	public boolean appendUpdateValueSql( StringBuilder sb, Field field, Object record )
	{
		sb.append( "'" + field.<String> getValue( record ) + "'" );

		return true;
	}

	@Override
	public void setFieldValueFromString( Field field, Object object, String value )
	{
		field.setValue( object, value );
	}
}

class SQLiteTypeManager_Date extends SQLiteTypeManagerManager.SQLiteTypeManager
{
	@Override
	public String createFieldSql( String fieldName )
	{
		return "DATETIME";
	}

	@Override
	public String autoUpdateTimestampCreateTriggerSql( String tableName, String fieldName )
	{
		String triggerSql = "CREATE TRIGGER IF NOT EXISTS " + tableName + "_" + fieldName + "_timestamp_update AFTER UPDATE ON " + tableName
				+ " FOR EACH ROW BEGIN " + "UPDATE " + tableName + " SET " + fieldName + " = CURRENT_TIMESTAMP WHERE id=old.id; END";

		return triggerSql;
	}

	@Override
	public boolean appendUpdateValueSql( StringBuilder sb, Field field, Object record )
	{
		Date value = field.<Date> getValue( record );
		sb.append( "'" + (value != null ? SQLite.dateTimeFormat.format( value ) : "") + "'" );

		return true;
	}

	@Override
	public void setFieldValueFromString( Field field, Object object, String value )
	{
		field.setValue( object, parseDate( value ) );
	}

	private static Date parseDate( String string )
	{
		try
		{
			return SQLite.dateTimeFormat.parse( string );
		}
		catch( Exception e )
		{
			return null;
		}
	}
}