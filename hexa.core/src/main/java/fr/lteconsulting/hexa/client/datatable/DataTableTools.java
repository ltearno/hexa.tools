package fr.lteconsulting.hexa.client.datatable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.google.gwt.core.shared.GWT;

import fr.lteconsulting.hexa.client.interfaces.IHasIntegerId;
import fr.lteconsulting.hexa.client.tools.Func1;

/**
 * Tools for DataTable
 * 
 */
public class DataTableTools
{
	/**
	 * Inserts a flat list as a tree in a TableCollectionManager. It does
	 * insert data in the right order for the creation of the tree (parents
	 * must be inserted first)
	 * 
	 * @param list
	 * @param mng
	 * @param parentIdGetter
	 */
	public static <T extends IHasIntegerId> void insertFlatList( List<T> list, TableCollectionManager<T> mng, Func1<T, Integer> parentIdGetter )
	{
		HashSet<Integer> inserted = new HashSet<>();
		
		List<T> toInsert = new ArrayList<>( list );
		List<T> postPoned = new ArrayList<>();
		List<T> inOrder = new ArrayList<>();
		
		int nbInserted;
		
		while( ! toInsert.isEmpty() )
		{
			nbInserted = 0;
			while( ! toInsert.isEmpty() )
			{
				T a = toInsert.remove( 0 );
				Integer parentId = parentIdGetter.exec( a );
				
				if( parentId==null || parentId<=0 || inserted.contains( parentId ) || mng.getRowForRecordId( parentId )!=null )
				{
					inOrder.add( a );
					inserted.add( a.getId() );
					
					nbInserted++;
				}
				else
				{
					postPoned.add( a );
				}
			}
			toInsert = postPoned;
			postPoned = new ArrayList<>();
			
			if( nbInserted == 0 && ! toInsert.isEmpty() )
			{
				GWT.log("Cannot construct full tree !");
				throw new RuntimeException( "Cannot construct full tree !" );
			}
		}
		
		for( T t : inOrder )
			mng.getDataPlug().updated( t );
	}
}
