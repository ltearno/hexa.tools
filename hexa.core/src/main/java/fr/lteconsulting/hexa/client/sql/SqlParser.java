package fr.lteconsulting.hexa.client.sql;

import static fr.lteconsulting.hexa.classinfo.ClassInfo.FindClazz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.lteconsulting.hexa.classinfo.ClassInfo;
import fr.lteconsulting.hexa.classinfo.Clazz;
import fr.lteconsulting.hexa.classinfo.Field;
import fr.lteconsulting.hexa.client.sql.SQLiteTypeManagerManager.SQLiteTypeManager;

public class SqlParser
{
	public class SqlParseInfo
	{
		public String sql;

		public String firstPart;
		public String paramPart;
		public String lastPart;

		public String className;
		public String tableAlias;

		public HashMap<String, Field> bindings = new HashMap<String, Field>();

		public String generatedSelect;
	}

	public SqlParseInfo parse( String request )
	{
		SqlParseInfo pi = new SqlParseInfo();

		pi.sql = request;

		findParameters( pi );
		parseParameters( pi );
		generateSqlSelect( pi );

		return pi;
	}

	public String getSql( SqlParseInfo pi )
	{
		if( pi == null || pi.firstPart == null || pi.generatedSelect == null || pi.lastPart == null )
			return null;

		return pi.firstPart + pi.generatedSelect + pi.lastPart;
	}

	@SuppressWarnings( "unchecked" )
	public <T> List<T> parseResults( SqlParseInfo pi, SQLiteResult results, Class<T> clazz )
	{
		Clazz<?> recordType = FindClazz( pi.className );
		if( recordType == null )
			return null;

		@SuppressWarnings( "rawtypes" )
		List list = new ArrayList();

		for( SQLiteResult.Row row : results )
		{
			Object record = recordType.NEW();

			for( SQLiteResult.Cell cell : row )
			{
				Field field = pi.bindings.get( cell.column );
				if( field == null )
					continue;

				SQLiteTypeManager mng = SQLiteTypeManagerManager.get( field.getType() );
				if( mng == null )
					continue;

				mng.setFieldValueFromString( field, record, cell.value );
			}

			list.add( record );
		}

		return list;
	}

	public <T> T parseResult( SqlParseInfo pi, SQLiteResult results, Class<T> clazz )
	{
		Clazz<T> recordType = ClassInfo.Clazz( clazz );
		if( recordType == null )
			return null;

		for( SQLiteResult.Row row : results )
		{
			T record = recordType.NEW();

			for( SQLiteResult.Cell cell : row )
			{
				Field field = pi.bindings.get( cell.column );
				if( field == null )
					continue;

				SQLiteTypeManager mng = SQLiteTypeManagerManager.get( field.getType() );
				if( mng == null )
					continue;

				mng.setFieldValueFromString( field, record, cell.value );
			}

			return record;
		}

		return null;
	}

	boolean findParameters( SqlParseInfo pi )
	{
		if( pi == null || pi.sql == null )
			return false;

		int in = pi.sql.indexOf( "{" );
		int out = pi.sql.indexOf( "}" );
		if( in < 0 || out < 0 || out <= in )
			return false;

		pi.firstPart = pi.sql.substring( 0, in );
		pi.paramPart = pi.sql.substring( in + 1, out );
		pi.lastPart = pi.sql.substring( out + 1 );

		return true;
	}

	boolean parseParameters( SqlParseInfo pi )
	{
		if( pi == null || pi.paramPart == null )
			return false;

		String[] parts = pi.paramPart.split( ":" );
		if( parts == null )
			return false;

		switch( parts.length )
		{
			case 1:
				pi.className = parts[0];
				pi.tableAlias = parts[0];
				break;

			case 2:
				pi.className = parts[0];
				pi.tableAlias = parts[1];
				break;

			default:
				return false;
		}

		return true;
	}

	boolean generateSqlSelect( SqlParseInfo pi )
	{
		if( pi == null || pi.tableAlias == null || pi.className == null )
			return false;

		StringBuilder sb = new StringBuilder();

		Clazz<?> clazz = ClassInfo.FindClazz( pi.className );
		if( clazz == null )
			return false;

		boolean fComa = false;
		for( Field field : clazz.getDeclaredFields() )
		{
			// no need to select fields that we don't know how to manage
			if( SQLiteTypeManagerManager.get( field.getType() ) == null )
				continue;

			if( fComa )
				sb.append( ", " );
			else
				fComa = true;

			String bindingName = generateBindingName();

			pi.bindings.put( bindingName, field );

			sb.append( pi.tableAlias + "." + field.getName() + " AS " + bindingName );
		}

		pi.generatedSelect = sb.toString();

		return true;
	}

	int bni = 1;

	private String generateBindingName()
	{
		return "bni" + (bni++);
	}
}
