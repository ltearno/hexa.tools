package com.hexa.client.databinding;

import com.hexa.client.databinding.DataBinding.DataAdapter;
import com.hexa.client.tools.Action1;

public abstract class WriteOnlyDataAdapter implements DataAdapter
{
	@Override
	public final Object registerPropertyChanged( Action1<DataAdapter> callback )
	{
		throw new RuntimeException("Not implemented in WriteOnlyDataAdapter");
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
