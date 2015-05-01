package fr.lteconsulting.hexa.client.sql;

import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;

import fr.lteconsulting.hexa.classinfo.ClassInfo;
import fr.lteconsulting.hexa.classinfo.Clazz;
import fr.lteconsulting.hexa.client.sql.SqlParser.SqlParseInfo;
import fr.lteconsulting.hexa.client.tools.Action;
import fr.lteconsulting.hexa.shared.dto.PagedResults;

public abstract class BaseSQLiteDAO<T>
{
	protected Clazz<T> clazz;
	protected SQLite db;
	protected Action requestSaveAction;

	protected abstract T recordFactory();

	public BaseSQLiteDAO( Class<T> clazzz, SQLite db )
	{
		this( clazzz, db, null );
	}
	
	public BaseSQLiteDAO( Class<T> clazzz, SQLite db, Action requestSaveAction )
	{
		clazz = ClassInfo.Clazz( clazzz );
		if( clazz == null )
			throw new IllegalArgumentException( "Classe non prise en charge par ClassInfo : " + clazzz.getName() );

		this.db = db;
		this.requestSaveAction = requestSaveAction;
	}

	public List<T> getRecords()
	{
		String request = "select {" + clazz.getClassName() + "} from " + clazz.getClassName() + " order by id";
		SqlParser parser = new SqlParser();
		SqlParseInfo pi = parser.parse( request );
		JavaScriptObject results = db.execute( parser.getSql( pi ) );
		SQLiteResult sqliteR = new SQLiteResult( results );
		List<T> records = parser.parseResults( pi, sqliteR, clazz.getReflectedClass() );

		return records;
	}

	public PagedResults<T> getRecordsPaged( int offset, int pageSize )
	{
		String request = "select {" + clazz.getClassName() + "} from " + clazz.getClassName() + " order by id limit " + offset + "," + pageSize;

		SqlParser parser = new SqlParser();
		SqlParseInfo pi = parser.parse( request );
		JavaScriptObject results = db.execute( parser.getSql( pi ) );
		SQLiteResult sqliteR = new SQLiteResult( results );
		List<T> customers = parser.parseResults( pi, sqliteR, clazz.getReflectedClass() );

		results = db.execute( "select count(*) from " + clazz.getClassName() );
		sqliteR = new SQLiteResult( results );
		int count = 0;
		try
		{
			for( SQLiteResult.Row row : sqliteR )
				for( SQLiteResult.Cell cell : row )
				{
					count = Integer.parseInt( cell.value );
					break;
				}
		}
		catch( Exception e )
		{

		}

		PagedResults<T> res = new PagedResults<T>();
		res.results = customers;
		res.offset = offset;
		res.totalNumberResults = count;

		return res;
	}

	public T getRecord( int id )
	{
		T record = SqlHelper.find( db, clazz.getReflectedClass(), id );

		return record;
	}

	public T updateRecord( T record )
	{
		SqlHelper.update( db, record );

		if( requestSaveAction != null )
			requestSaveAction.exec();

		return record;
	}

	public boolean deleteRecord( int id )
	{
		SqlHelper.delete( db, clazz.getReflectedClass(), id );

		if( requestSaveAction != null )
			requestSaveAction.exec();

		return true;
	}

	public T createRecord()
	{
		T record = recordFactory();

		SqlHelper.insert( db, record );

		if( requestSaveAction != null )
			requestSaveAction.exec();

		return record;
	}
}
