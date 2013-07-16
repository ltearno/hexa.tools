package com.hexa.client.databinding;

import com.google.gwt.user.client.ui.HasValue;
import com.hexa.client.databinding.propertyadapters.CompositePropertyAdapter;
import com.hexa.client.databinding.propertyadapters.PropertyAdapter;
import com.hexa.client.databinding.propertyadapters.WidgetPropertyAdapter;

public class Bind
{
	private PropertyAdapter source;
	private Mode mode = Mode.TwoWay;
	private Converter converter;
	private boolean fDeferActivate;
	private String logPrefix;

	public static Bind Source( Object source, String propertyName )
	{
		return Source( new CompositePropertyAdapter( source, propertyName ) );
	}

	@SuppressWarnings( "rawtypes" )
	public static Bind Source( HasValue widget )
	{
		return Source( new WidgetPropertyAdapter( widget ) );
	}

	public static Bind Source( PropertyAdapter source )
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
	
	public Bind Log( String prefix )
	{
		this.logPrefix = prefix;
		return this;
	}

	public DataBinding To( Object source, String propertyName )
	{
		return To( new CompositePropertyAdapter( source, propertyName ) );
	}

	@SuppressWarnings( "rawtypes" )
	public DataBinding To( HasValue widget )
	{
		return To( new WidgetPropertyAdapter( widget ) );
	}

	public DataBinding To( PropertyAdapter destination )
	{
		DataBinding binding = new DataBinding( source, destination, mode, converter, logPrefix );
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
