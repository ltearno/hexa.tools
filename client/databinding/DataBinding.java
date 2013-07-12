package com.hexa.client.databinding;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.HasValue;
import com.hexa.client.databinding.propertyadapters.ObjectPropertyAdapter;
import com.hexa.client.databinding.propertyadapters.PropertyAdapter;
import com.hexa.client.databinding.propertyadapters.WidgetPropertyAdapter;
import com.hexa.client.tools.Action2;

public class DataBinding
{
	private boolean fActivated;

	private PropertyAdapter source;
	private Object sourceHandler;

	private PropertyAdapter destination;
	private Object destinationHandler;

	private Converter converter;

	public DataBinding( Object source, String sourceProperty, Object destination, String destinationProperty, Mode bindingMode )
	{
		this( new ObjectPropertyAdapter( source, sourceProperty ), new ObjectPropertyAdapter( destination, destinationProperty ), bindingMode, null );
	}

	public DataBinding( Object source, String sourceProperty, HasValue<?> destination, Mode bindingMode )
	{
		this( new ObjectPropertyAdapter( source, sourceProperty ), new WidgetPropertyAdapter( destination ), bindingMode, null );
	}

	public DataBinding( PropertyAdapter source, PropertyAdapter destination, Mode bindingMode, Converter converter )
	{
		this.source = source;
		this.destination = destination;
		this.converter = converter;

		switch( bindingMode )
		{
		case OneWay:
			sourceHandler = source.registerPropertyChanged( onSourceChanged, null );
			break;
		case OneWayToSource:
			destinationHandler = destination.registerPropertyChanged( onDestinationChanged, null );
			break;
		case TwoWay:
			sourceHandler = source.registerPropertyChanged( onSourceChanged, null );
			destinationHandler = destination.registerPropertyChanged( onDestinationChanged, null );
			break;
		}
	}

	public void activate()
	{
		fActivated = true;

		onSourceChanged.exec( null, null );
	}

	public void deferActivate()
	{
		Scheduler.get().scheduleDeferred( new ScheduledCommand()
		{
			@Override
			public void execute()
			{
				activate();
			}
		} );
	}

	public void term()
	{
		fActivated = false;
		converter = null;

		source.removePropertyChangedHandler( sourceHandler );
		source = null;
		sourceHandler = null;

		destination.removePropertyChangedHandler( destinationHandler );
		destination = null;
		destinationHandler = null;

	}

	private final Action2<PropertyAdapter, Object> onSourceChanged = new Action2<PropertyAdapter, Object>()
	{
		@Override
		public void exec( PropertyAdapter param, Object cookie )
		{
			if( !fActivated )
				return;

			Object value = source.getValue();

			if( converter != null )
				value = converter.convert( value );

			destination.setValue( value );
		}
	};

	private final Action2<PropertyAdapter, Object> onDestinationChanged = new Action2<PropertyAdapter, Object>()
	{
		@Override
		public void exec( PropertyAdapter param, Object cookie )
		{
			if( !fActivated )
				return;

			Object value = destination.getValue();

			if( converter != null )
				value = converter.convertBack( value );

			source.setValue( value );
		}
	};
}
