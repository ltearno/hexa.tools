package com.hexa.client.databinding.propertyadapters;

import com.hexa.client.tools.Action2;

public abstract class WriteOnlyPropertyAdapter implements PropertyAdapter
{
	@Override
	public final Object registerPropertyChanged( Action2<PropertyAdapter, Object> callback, Object cookie )
	{
		return null;
	}

	@Override
	public final void removePropertyChangedHandler( Object handler )
	{
		throw new RuntimeException("Not implemented in WriteOnlyDataAdapter");
	}

	@Override
	public final Object getValue()
	{
		throw new RuntimeException("Not implemented in WriteOnlyDataAdapter");
	}
}
