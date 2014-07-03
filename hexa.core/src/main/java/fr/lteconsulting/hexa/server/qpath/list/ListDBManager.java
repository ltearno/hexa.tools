package fr.lteconsulting.hexa.server.qpath.list;

import java.util.ArrayList;
import java.util.List;

import fr.lteconsulting.hexa.server.qpath.DatabaseHelper;
import fr.lteconsulting.hexa.server.qpath.DatabaseHelper.FieldsMap;
import fr.lteconsulting.hexa.server.qpath.QPath;
import fr.lteconsulting.hexa.server.qpath.QPathResult.QPathResultRow;
import fr.lteconsulting.hexa.shared.data.ListDTO;

public class ListDBManager<T extends ListDTO>
{
	private QPath qpath;// = Server.inst().qpath();
	private DatabaseHelper dbh;// = Server.inst().dbh();

	private Class<T> clazz;
	private String table;
	private String groupingField;

	public ListDBManager( String targetTable, String groupingField, Class<T> targetClass, QPath qpath, DatabaseHelper dbh )
	{
		this.table = targetTable;
		this.groupingField = groupingField;
		this.clazz = targetClass;

		this.qpath = qpath;
		this.dbh = dbh;
	}

	// query to select all the elements of the list
	private String baseQuery( int groupingFieldValue )
	{
		return table + " [" + groupingField + "=" + groupingFieldValue + "]";
	}

	public List<T> get( int groupingFieldValue )
	{
		Iterable<T> result = qpath.queryExDTO( clazz, baseQuery( groupingFieldValue ) );

		List<T> list = new ArrayList<T>();
		for( T item : result )
			list.add( item );

		// TODO : sort according before_id and after_id

		return list;
	}

	// adds at the end
	public T add( T item, int groupingFieldValue )
	{
		if( item == null )
		{
			System.out.println( "ERROR in ListDBManager.add, item is null" );
			return null;
		}

		// how many in that list ?
		// Long count = qpath.queryOne( "F[count(*) as 'count'] ? " + baseQuery(
		// groupingFieldValue ) ).get( "count" );
		QPathResultRow row = qpath.queryOne( "F[" + table + ".id as 'id'] ? " + baseQuery( groupingFieldValue ) );

		// last in the list
		T last;
		if( row != null ) // count > 0 )
			last = qpath.queryOneDTO( clazz, baseQuery( groupingFieldValue ) + " [after_id=-1]" );
		else
			last = null;

		// add the new item
		item.setBeforeId( last != null ? last.getId() : -1 );
		item.setAfterId( -1 );
		item = dbh.insert( table, clazz, item, FieldsMap.create().p( groupingField, groupingFieldValue ) );

		// update the last
		if( last != null )
			dbh.update( table, "id=" + last.getId(), FieldsMap.create().p( "after_id", item.getId() ) );

		return item;
	}
}