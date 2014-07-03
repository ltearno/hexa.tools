package fr.lteconsulting.hexa.client.tools;

import fr.lteconsulting.hexa.client.tools.HierarchySet.IHierarchyAccumulator;
import fr.lteconsulting.hexa.client.ui.treetable.Row;

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