package fr.lteconsulting.hexa.client.sql;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.google.gwt.core.shared.GWT;

import fr.lteconsulting.hexa.classinfo.ClassInfo;
import fr.lteconsulting.hexa.classinfo.Clazz;
import fr.lteconsulting.hexa.classinfo.Field;
import fr.lteconsulting.hexa.client.sql.SQLiteTypeManagerManager.SQLiteTypeManager;
import fr.lteconsulting.hexa.client.tools.Func2;

public class SqlHelper2
{
	public static class CreateTable
	{
		SQLite db;
		Clazz<?> clazz;
		String tableName;
		List<String> autoUpdateTimestampTriggerFields = new ArrayList<String>();
		List<String> localRecordStateTriggerFields = new ArrayList<String>();
		boolean fWithLocalRecordDeletedCreateTriggerSql;

		class FieldInfo
		{
			String name;
			SQLiteTypeManager typeManager;
		}

		HashMap<String, FieldInfo> fields = new HashMap<String, FieldInfo>();

		private void registerField( String fieldName, SQLiteTypeManager typeManager )
		{
			if( fields.containsKey( fieldName ) )
				return;

			FieldInfo fi = new FieldInfo();
			fi.name = fieldName;
			fi.typeManager = typeManager;

			fields.put( fieldName, fi );
		}

		private CreateTable( SQLite db )
		{
			this.db = db;
		}

		public static CreateTable WithDb( SQLite db )
		{
			return new CreateTable( db );
		}

		public CreateTable FromClass( Class<?> clazz )
		{
			this.clazz = ClassInfo.Clazz( clazz );
			if( this.clazz == null )
				return null;

			this.tableName = this.clazz.getClassName();

			for( Field field : this.clazz.getDeclaredFields() )
			{
				SQLiteTypeManager mng = SQLiteTypeManagerManager.get( field.getType() );
				if( mng == null )
					continue;

				registerField( field.getName(), mng );
			}

			return this;
		}

		public CreateTable WithName( String tableName )
		{
			this.tableName = tableName;

			return this;
		}

		public CreateTable AutoUpdateTimestampTrigger( String fieldName )
		{
			registerField( fieldName, SQLiteTypeManagerManager.get( Date.class ) );

			autoUpdateTimestampTriggerFields.add( fieldName );

			return this;
		}

		public CreateTable LocalRecordStateTrigger( String fieldName )
		{
			registerField( fieldName, SQLiteTypeManagerManager.get( int.class ) );

			localRecordStateTriggerFields.add( fieldName );

			return this;
		}

		public CreateTable WithTriggerDelete()
		{
			fWithLocalRecordDeletedCreateTriggerSql = true;

			return this;
		}

		// "CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100));"
		public boolean Go()
		{
			if( clazz == null || tableName == null )
				return false;

			StringBuilder sb = new StringBuilder();

			sb.append( "CREATE TABLE " );
			sb.append( tableName );
			sb.append( "(" );

			boolean fComa = false;

			for( Entry<String, FieldInfo> e : fields.entrySet() )
			{
				FieldInfo fi = e.getValue();
				SQLiteTypeManager mng = fi.typeManager;
				String fieldName = fi.name;

				if( fComa )
					sb.append( ", " );
				else
					fComa = true;

				sb.append( fieldName );
				sb.append( " " );
				String createFieldSql = mng.createFieldSql( fieldName, false, false );
				if( createFieldSql == null )
					return false;
				sb.append( createFieldSql );
				sb.append( " " );
			}

			sb.append( ");" );

			String sql = sb.toString();

			db.execute( sql );

			// post creation optional sql.
			// for trigger creation for example

			actionsForFields( autoUpdateTimestampTriggerFields, new Func2<SQLite, FieldInfo, Boolean>()
			{
				@Override
				public Boolean exec( SQLite db, FieldInfo fi )
				{
					String sql = fi.typeManager.autoUpdateTimestampCreateTriggerSql( tableName, fi.name );

					db.execute( sql );

					return true;
				}
			} );

			actionsForFields( localRecordStateTriggerFields, new Func2<SQLite, FieldInfo, Boolean>()
			{
				@Override
				public Boolean exec( SQLite db, FieldInfo fi )
				{
					return fi.typeManager.localRecordStateCreateTriggerSql( db, tableName, fi.name );
				}
			} );

			if( fWithLocalRecordDeletedCreateTriggerSql )
				localRecordDeletedCreateTriggerSql();

			return true;
		}

		private boolean actionsForFields( List<String> fieldNames, Func2<SQLite, FieldInfo, Boolean> fieldAction )
		{
			for( String fieldName : fieldNames )
			{
				FieldInfo fi = fields.get( fieldName );
				if( fi == null )
					return false;

				Boolean res = fieldAction.exec( db, fi );
				if( !res )
					GWT.log( "ERROR : actions for field !" );
			}

			return true;
		}

		public Boolean localRecordDeletedCreateTriggerSql()
		{
			 String triggerSql = "CREATE TRIGGER IF NOT EXISTS "
				 + tableName
				 + "_deleted AFTER DELETE ON "
				 + tableName
				 + " FOR EACH ROW "
				 + "BEGIN "
				 + "INSERT INTO DeletedRecord (recordId, tableName) VALUES (OLD.id, '"
				 + tableName + "'); END";

			 db.execute(triggerSql);

			return true;
		}
	}
}
