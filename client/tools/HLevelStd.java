package com.hexa.client.tools;

import com.hexa.client.tools.HierarchySet.IHierarchyAccumulator;
import com.hexa.client.ui.treetable.Row;

public class HLevelStd implements HierarchySet.IHierarchyLevel<String>
{
	String id;
	String name;

	public HLevelStd( String id, String name )
	{
		this.id = id;
		this.name = name;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public String getIdentifier( String record )
	{
		return id;
	}

	@Override
	public void fillRow( Row row, String record )
	{
	}

	@Override
	public IHierarchyAccumulator<String> getNewAccumulator()
	{
		return null;
	}

}