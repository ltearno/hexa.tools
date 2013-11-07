package com.hexa.client.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import com.hexa.client.ui.treetable.TreeTable;

public class HierarchySet<T>
{
	public interface IHierarchyLevel<T>
	{
		public String getName();

		public String getIdentifier( T record );

		public void fillRow( TreeTable table, Object item, T record );

		public IHierarchyAccumulator<T> getNewAccumulator();
	}

	ArrayList<IHierarchyLevel<T>> hierarchy = new ArrayList<IHierarchyLevel<T>>();

	public interface IHierarchyAccumulator<T>
	{
		public void add( T element );

		public void fillRow( TreeTable table, Object item );
	}

	HashMap<String, Object> items = new HashMap<String, Object>();
	HashMap<Object, IHierarchyAccumulator<T>> accumulators = new HashMap<Object, IHierarchyAccumulator<T>>();

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

	public Object getParentItem( TreeTable table, T element )
	{
		Object itemParent = null;
		String itemAddress = "";
		for( IHierarchyLevel<T> h : hierarchy )
		{
			itemAddress += "-" + h.getIdentifier( element );
			Object item = items.get( itemAddress );
			if( item == null )
			{
				item = table.addRow( itemParent );

				h.fillRow( table, item, element );

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

	public Object createItem( TreeTable table, T element )
	{
		Object itemParent = getParentItem( table, element );

		return table.addRow( itemParent );
	}

	public void displayAccumulators( TreeTable table )
	{
		Set<Entry<Object, IHierarchyAccumulator<T>>> set = accumulators.entrySet();
		for( Iterator<Entry<Object, IHierarchyAccumulator<T>>> i = set.iterator(); i.hasNext(); )
		{
			Entry<Object, IHierarchyAccumulator<T>> entry = i.next();

			if( entry.getValue() != null )
				entry.getValue().fillRow( table, entry.getKey() );
		}
	}
}
