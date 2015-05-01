package fr.lteconsulting.hexa.client.sql;

import java.util.Date;
import java.util.HashMap;

import fr.lteconsulting.hexa.classinfo.Field;

public class SQLiteTypeManagerManager
{
	public static abstract class SQLiteTypeManager
	{
		abstract public String createFieldSql( String fieldName, boolean fPrimaryKey, boolean fAutoIncrement );

		public String autoUpdateTimestampCreateTriggerSql( String tableName, String fieldName )
		{
			throw new IllegalStateException( "AutoUpdateTimestamp trigger not supported for this type !" );
		}

		public Boolean localRecordStateCreateTriggerSql( SQLite db, String tableName, String fieldName )
		{
			return true;
		}

		public final void setFieldValueFromString( Field field, Object object, String value )
		{
			field.setValue( object, getValueFromString( value ) );
		}

		abstract public boolean appendUpdateValueSql( StringBuilder sb, Field field, Object record );

		abstract public String getStringForValue( Object value );
		abstract public Object getValueFromString( String value );
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
	public String createFieldSql( String fieldName, boolean fPrimaryKey, boolean fAutoIncrement )
	{
		String res = "INTEGER";
		if( fPrimaryKey )
			res += " PRIMARY KEY";
		if( fAutoIncrement )
			res += " AUTOINCREMENT";

		return res;
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
				+ " FOR EACH ROW " + "WHEN NEW." + fieldName + " <> 1 " + "BEGIN " + "UPDATE " + tableName + " SET " + fieldName + " = 2 WHERE id=NEW.id; END";

		db.execute( triggerSql );

		return true;
	}

	@Override
	public boolean appendUpdateValueSql( StringBuilder sb, Field field, Object record )
	{
		Object value = field.getValue( record );

		sb.append( getStringForValue( value ) );

		return true;
	}

	@Override
	public String getStringForValue( Object value )
	{
		if( value == null )
			return null;

		return "" + value;
	}

	@Override
	public Object getValueFromString( String value )
	{
		return Integer.parseInt( value );
	}
}

class SQLiteTypeManager_String extends SQLiteTypeManagerManager.SQLiteTypeManager
{
	@Override
	public String createFieldSql( String fieldName, boolean fPrimaryKey, boolean fAutoIncrement )
	{
		assert fPrimaryKey == false;
		assert fAutoIncrement == false;

		return "VARCHAR(100)";
	}

	@Override
	public final boolean appendUpdateValueSql( StringBuilder sb, Field field, Object record )
	{
		Object value = field.getValue( record );

		sb.append( "'" + getStringForValue( value ) + "'" );

		return true;
	}

	@Override
	public String getStringForValue( Object value )
	{
		return (String) value;
	}

	@Override
	public Object getValueFromString( String value )
	{
		return value;
	}
}

class SQLiteTypeManager_Date extends SQLiteTypeManagerManager.SQLiteTypeManager
{
	@Override
	public String createFieldSql( String fieldName, boolean fPrimaryKey, boolean fAutoIncrement )
	{
		assert fPrimaryKey == false;
		assert fAutoIncrement == false;

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
	public final boolean appendUpdateValueSql( StringBuilder sb, Field field, Object record )
	{
		Object value = field.getValue( record );
		if( value == null )
			sb.append( "NULL" );
		else
			sb.append( "'" + getStringForValue( value ) + "'" );

		return true;
	}

	@Override
	public String getStringForValue( Object value )
	{
		return (value != null ? SQLite.dateTimeFormat.format( (Date) value ) : "");
	}

	@Override
	public Object getValueFromString( String value )
	{
		return parseDate( value );
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

class SQLiteTypeManager_Timestamp extends SQLiteTypeManager_Date
{
	@Override
	public String createFieldSql( String fieldName, boolean fPrimaryKey, boolean fAutoIncrement )
	{
		assert fPrimaryKey == false;
		assert fAutoIncrement == false;

		return "DATETIME DEFAULT CURRENT_TIMESTAMP";
	}
}