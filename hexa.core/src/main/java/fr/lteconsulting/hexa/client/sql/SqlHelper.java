package fr.lteconsulting.hexa.client.sql;

import static fr.lteconsulting.hexa.classinfo.ClassInfo.Clazz;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;

import fr.lteconsulting.hexa.classinfo.Clazz;
import fr.lteconsulting.hexa.classinfo.Field;
import fr.lteconsulting.hexa.client.sql.SQLiteTypeManagerManager.SQLiteTypeManager;
import fr.lteconsulting.hexa.client.sql.SqlParser.SqlParseInfo;
import fr.lteconsulting.hexa.client.tools.Action;

public class SqlHelper
{
	// should be feeded by the application logic
	public static Action requestPersistDatabase = new Action()
	{
		@Override
		public void exec()
		{
		}
	};

	public static <T> T find( SQLite db, Class<T> clazzz, int id )
	{
		Clazz<?> clazz = Clazz( clazzz );
		if( clazz == null )
			return null;

		//String request = "select {" + clazz.getClassName() + "}, recordState from " + clazz.getClassName() + " where id=" + id;
		String request = "select {" + clazz.getClassName() + "} from " + clazz.getClassName() + " where id=" + id;
		SqlParser parser = new SqlParser();
		SqlParseInfo pi = parser.parse( request );
		JavaScriptObject results = db.execute( parser.getSql( pi ) );
		SQLiteResult sqliteR = new SQLiteResult( results );
		T record = parser.parseResult( pi, sqliteR, clazzz );

		return record;
	}

	public static <T> List<T> query( SQLite db, String sql, Class<T> clazzz )
	{
		Clazz<?> clazz = Clazz( clazzz );
		if( clazz == null )
			return null;

		SqlParser parser = new SqlParser();
		SqlParseInfo pi = parser.parse( sql );
		String formattedSql = parser.getSql( pi );
		// GWT.log( "QUERY: " + formattedSql );
		JavaScriptObject results = db.execute( formattedSql );
		SQLiteResult sqliteR = new SQLiteResult( results );

		List<T> records = parser.parseResults( pi, sqliteR, clazzz );

		return records;
	}

	public static <T> T queryOne( SQLite db, String sql, Class<T> clazzz )
	{
		Clazz<?> clazz = Clazz( clazzz );
		if( clazz == null )
			return null;

		SqlParser parser = new SqlParser();
		SqlParseInfo pi = parser.parse( sql );
		String formattedSql = parser.getSql( pi );
		//GWT.log( "QUERY: " + formattedSql );
		JavaScriptObject results = db.execute( formattedSql );
		SQLiteResult sqliteR = new SQLiteResult( results );

		List<T> records = parser.parseResults( pi, sqliteR, clazzz );
		if( records.isEmpty() )
			return null;

		return records.get( 0 );
	}

	public static <T> boolean update( SQLite db, T record )
	{
		return update( db, record, false );
	}

	public static <T> boolean updateFromServer( SQLite db, T record )
	{
		return update( db, record, true );
	}

	@SuppressWarnings( "unchecked" )
	public static <T> boolean update( SQLite db, T record, boolean isFromServer )
	{
		if( record == null )
			return false;

		Clazz<?> clazz = Clazz( record.getClass() );
		if( clazz == null )
			return false;

		Field idField = clazz.getField( "id" );
		if( idField == null )
			throw new IllegalStateException( "No id field found for class " + clazz.getClassName() );

		int recordId = (Integer) idField.getValue( record );

		// UPDATE users SET name = 'toto', xxx = value WHERE expression;

		String tableName = clazz.getClassName();

		StringBuilder sb = new StringBuilder();

		sb.append( "UPDATE " );
		sb.append( tableName );
		sb.append( " SET " );

		boolean fComa = false;
		for( Field field : clazz.getFields() )
		{
			// we dont update id field
			if( field.getName().equals( "id" ) )
				continue;

			SQLiteTypeManager mng = SQLiteTypeManagerManager.get( field.getType() );
			if( mng == null )
				continue;

			if( fComa )
				sb.append( ", " );
			else
				fComa = true;

			sb.append( field.getName() + " = " );

			if( !mng.appendUpdateValueSql( sb, field, record ) )
				return false;
		}

		if( fComa )
			sb.append( ", " );
		if( isFromServer )
			sb.append( "recordState = 1" );
		else
			sb.append( "recordState = 2" );

		sb.append( " WHERE id=" + recordId );
		sb.append( ";" );

		String sql = sb.toString();
		db.execute( sql );

		requestPersistDatabase.exec();

		//GWT.log( "UPDATE: " + sql );

		// update the given object
		T newVersion = find( db, (Class<T>) record.getClass(), recordId );
		for( Field field : clazz.getFields() )
			field.copyValueTo( newVersion, record );

		return true;
	}

	public static <T> boolean insert( SQLite db, T record )
	{
		return insert( db, record, false );
	}

	public static <T> boolean insertFromServer( SQLite db, T record )
	{
		return insert( db, record, true );
	}

	public static <T> boolean insert( SQLite db, T record, boolean isFromServer )
	{
		Clazz<?> clazz = Clazz( record.getClass() );
		if( clazz == null )
			return false;

		String tableName = clazz.getClassName();

		StringBuilder sb = new StringBuilder();

		sb.append( "INSERT INTO " );
		sb.append( tableName );
		sb.append( "(" );

		StringBuilder sbValues = new StringBuilder();

		boolean fComa = false;
		for( Field field : clazz.getDeclaredFields() )
		{
			// we dont insert id field when they are not specified...
			if( field.getName().equals( "id" ) && ((Integer) field.getValue( record )) == 0 )
				continue;

			SQLiteTypeManager mng = SQLiteTypeManagerManager.get( field.getType() );
			if( mng == null )
				continue;

			if( fComa )
			{
				sb.append( ", " );
				sbValues.append( ", " );
			}
			else
			{
				fComa = true;
			}

			sb.append( field.getName() + " " );

			if( !mng.appendUpdateValueSql( sbValues, field, record ) )
				return false;
		}

		sb.append( ") VALUES (" );
		sb.append( sbValues.toString() );
		sb.append( ");" );

		String sql = sb.toString();

		//GWT.log( "INSERT: " + sql );

		db.execute( sql );

		requestPersistDatabase.exec();

		int lastId = db.getLastInsertedId();
		GWT.log( "LastInsertedId : " + lastId );
		if( lastId > 0 )
		{
			Field idField = clazz.getDeclaredField( "id" );
			if( idField != null )
				idField.setValue( record, lastId );
		}

		return true;
	}

	public static <T> boolean delete( SQLite db, Class<T> clazzz, int recordId )
	{
		return delete( db, clazzz, recordId, false );
	}

	public static <T> boolean deleteFromServer( SQLite db, Class<T> clazzz, int recordId )
	{
		return delete( db, clazzz, recordId, true );
	}

	public static <T> boolean delete( SQLite db, Class<T> clazzz, int recordId, boolean isFromServer )
	{
		// "DELETE FROM table WHERE id=kkk"

		Clazz<T> clazz = Clazz( clazzz );
		if( clazz == null )
			return false;

		String tableName = clazz.getClassName();

		StringBuilder sb = new StringBuilder();

		sb.append( "DELETE FROM " );
		sb.append( tableName );
		sb.append( " WHERE id=" );
		sb.append( recordId );

		String sql = sb.toString();

		db.execute( sql );

		requestPersistDatabase.exec();

		//GWT.log( "DELETE: " + sql );

		if( !isFromServer )
		{
			db.execute( "insert into DeletedRecord (recordId, tableName) VALUES (" + recordId + ", '" + tableName + "')" );
		}

		return true;
	}
}
