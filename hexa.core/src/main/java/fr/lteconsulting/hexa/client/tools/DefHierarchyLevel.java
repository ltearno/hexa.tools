package fr.lteconsulting.hexa.client.tools;

import fr.lteconsulting.hexa.client.tools.HierarchySet.IHierarchyAccumulator;
import fr.lteconsulting.hexa.client.tools.HierarchySet.IHierarchyLevel;

public abstract class DefHierarchyLevel<T> implements IHierarchyLevel<T>
{
	String name;

	public DefHierarchyLevel( String name )
	{
		this.name = name;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public IHierarchyAccumulator<T> getNewAccumulator()
	{
		return null;
	}

}
