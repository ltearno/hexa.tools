package com.hexa.client.databinding;

import com.google.gwt.user.client.ui.HasValue;
import com.hexa.client.databinding.DataBinding.DataAdapter;

public class Bind
{
	private DataAdapter source;
	private Mode mode = Mode.TwoWay;
	private Converter converter;
	private boolean fDeferActivate;

	public static Bind Source( Object source, String propertyName )
	{
		return Source( new CompositeObjectAdapter( source, propertyName ) );
	}

	@SuppressWarnings( "rawtypes" )
	public static Bind Source( HasValue widget )
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

	@SuppressWarnings( "rawtypes" )
	public DataBinding To( HasValue widget )
	{
		return To( new WidgetAdapter( widget ) );
	}

	public DataBinding To( DataAdapter destination )
	{
		DataBinding binding = new DataBinding( source, destination, mode, converter );
		if( fDeferActivate )
			binding.deferActivate();
		else
			binding.activate();

		source = null;
		return binding;
	}

	public Bind WithConverter( Converter converter )
	{
		this.converter = converter;

		return this;
	}

	public Bind DeferActivate()
	{
		fDeferActivate = true;

		return this;
	}
}
