package com.hexa.client.tools;

import com.hexa.client.tools.HierarchySet.IHierarchyAccumulator;
import com.hexa.client.ui.TreeTable;

public class HLevelStd implements HierarchySet.IHierarchyLevel<String>
{
	String id;
	String name;

	public HLevelStd( String id, String name )
	{
		this.id = id;
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public String getIdentifier( String record )
	{
		return id;
	}

	public void fillRow( TreeTable table, Object item, String record )
	{
	}

	public IHierarchyAccumulator<String> getNewAccumulator()
	{
		return null;
	}

}