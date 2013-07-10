package com.hexa.client.databinding;

import com.google.gwt.user.client.ui.Widget;
import com.hexa.client.databinding.DataBinding.DataAdapter;

public class Bind
{
	private DataAdapter source;
	private Mode mode = Mode.TwoWay;

	public static Bind Source( Object source, String propertyName )
	{
		return Source( new ObjectAdapter( source, propertyName ) );
	}

	public static Bind Source( Widget widget )
	{
		return Source( new WidgetAdapter( widget ) );
	}

	public static Bind Source( DataAdapter source )
	{
		Bind b = new Bind();
		b.source = source;
		return b;
	}

	public Bind Mode( Mode mode )
	{
		this.mode = mode;
		return this;
	}

	public void To( Object source, String propertyName )
	{
		To( new ObjectAdapter( source, propertyName ) );
	}

	public void To( Widget widget )
	{
		To( new WidgetAdapter( widget ) );
	}

	public void To( DataAdapter destination )
	{
		DataBinding binding = new DataBinding( source, destination, mode );
		binding.activate();
		source = null;
	}
}
