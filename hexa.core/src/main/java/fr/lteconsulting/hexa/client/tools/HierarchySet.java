package fr.lteconsulting.hexa.client.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import fr.lteconsulting.hexa.client.ui.treetable.Row;
import fr.lteconsulting.hexa.client.ui.treetable.TreeTable;

public class HierarchySet<T>
{
	public interface IHierarchyLevel<T>
	{
		public String getName();

		public String getIdentifier( T record );

		public void fillRow( Row row, T record );

		public IHierarchyAccumulator<T> getNewAccumulator();
	}

	ArrayList<IHierarchyLevel<T>> hierarchy = new ArrayList<IHierarchyLevel<T>>();

	public interface IHierarchyAccumulator<T>
	{
		public void add( T element );

		public void fillRow( Row row );
	}

	HashMap<String, Row> items = new HashMap<String, Row>();
	HashMap<Row, IHierarchyAccumulator<T>> accumulators = new HashMap<Row, IHierarchyAccumulator<T>>();

	public void resetDisplay()
	{
		items.clear();
		accumulators.clear();
	}

	public void clearHierarchy()
	{
		hierarchy.clear();
	}

	public void add( IHierarchyLevel<T> item )
	{
		hierarchy.add( item );
	}

	public Row getParentItem( TreeTable table, T element )
	{
		Row itemParent = null;
		String itemAddress = "";
		for( IHierarchyLevel<T> h : hierarchy )
		{
			itemAddress += "-" + h.getIdentifier( element );
			Row item = items.get( itemAddress );
			if( item == null )
			{
				item = table.addRow( itemParent );

				h.fillRow( item, element );

				// TODO : FIx the bug that prevents to do that...
				// table.setExpanded( item, false );

				items.put( itemAddress, item );
				accumulators.put( item, h.getNewAccumulator() );
			}

			IHierarchyAccumulator<T> accu = accumulators.get( item );
			if( accu != null )
				accu.add( element );

			itemParent = item;
		}

		return itemParent;
	}

	public Row createItem( TreeTable table, T element )
	{
		Row itemParent = getParentItem( table, element );

		return table.addRow( itemParent );
	}

	public void displayAccumulators( TreeTable table )
	{
		Set<Entry<Row, IHierarchyAccumulator<T>>> set = accumulators.entrySet();
		for( Iterator<Entry<Row, IHierarchyAccumulator<T>>> i = set.iterator(); i.hasNext(); )
		{
			Entry<Row, IHierarchyAccumulator<T>> entry = i.next();

			if( entry.getValue() != null )
				entry.getValue().fillRow( entry.getKey() );
		}
	}
}
