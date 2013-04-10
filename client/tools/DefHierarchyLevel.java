package com.hexa.client.tools;

import com.hexa.client.tools.HierarchySet.IHierarchyAccumulator;
import com.hexa.client.tools.HierarchySet.IHierarchyLevel;

public abstract class DefHierarchyLevel<T> implements IHierarchyLevel<T> {
	String name;
	
	public DefHierarchyLevel( String name ) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IHierarchyAccumulator<T> getNewAccumulator() {
		return null;
	}

}
