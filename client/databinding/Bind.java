package com.hexa.client.databinding;

import com.google.gwt.user.client.ui.Widget;
import com.hexa.client.databinding.DataBinding.DataAdapter;

public class Bind
{
	private DataAdapter source;
	private Mode mode = Mode.TwoWay;
	private Converter converter;

	public static Bind Source( Object source, String propertyName )
	{
		return Source( new CompositeObjectAdapter( source, propertyName ) );
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

	public DataBinding To( Object source, String propertyName )
	{
		return To( new CompositeObjectAdapter( source, propertyName ) );
	}

	public DataBinding To( Widget widget )
	{
		return To( new WidgetAdapter( widget ) );
	}

	public DataBinding To( DataAdapter destination )
	{
		DataBinding binding = new DataBinding( source, destination, mode, converter );
		binding.activate();
		source = null;
		return binding;
	}

	public Bind WithConverter( Converter converter )
	{
		this.converter = converter;
		
		return this;
	}
}
