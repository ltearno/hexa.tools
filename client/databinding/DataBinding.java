package com.hexa.client.databinding;

import com.google.gwt.user.client.ui.HasValue;
import com.hexa.client.tools.Action1;

public class DataBinding
{
	private boolean fActivated;

	private DataAdapter source;
	private Object sourceHandler;
	
	private DataAdapter destination;
	private Object destinationHandler;
	
	private final Mode bindingMode;
	private Converter converter;

	public interface DataAdapter
	{
		// returns handle
		public Object registerPropertyChanged( Action1<DataAdapter> callback );
		
		public void removePropertyChangedHandler( Object handler );

		public Object getValue();

		public void setValue( Object object );
	}

	public DataBinding( Object source, String sourceProperty, Object destination, String destinationProperty, Mode bindingMode )
	{
		this( new ObjectAdapter( source, sourceProperty ), new ObjectAdapter( destination, destinationProperty ), bindingMode, null );
	}

	public DataBinding( Object source, String sourceProperty, HasValue<?> destination, Mode bindingMode )
	{
		this( new ObjectAdapter( source, sourceProperty ), new WidgetAdapter( destination ), bindingMode, null );
	}

	public DataBinding( DataAdapter source, DataAdapter destination, Mode bindingMode, Converter converter )
	{
		this.source = source;
		this.destination = destination;
		this.bindingMode = bindingMode;
		this.converter = converter;

		switch( bindingMode )
		{
		case OneWay:
			sourceHandler = source.registerPropertyChanged( onSourceChanged );
			break;
		case OneWayToSource:
			destinationHandler = destination.registerPropertyChanged( onDestinationChanged );
			break;
		case TwoWay:
			sourceHandler = source.registerPropertyChanged( onSourceChanged );
			destinationHandler = destination.registerPropertyChanged( onDestinationChanged );
			break;
		}
	}

	public void activate()
	{
		fActivated = true;

		onSourceChanged.exec( null );
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

	private final Action1<DataAdapter> onSourceChanged = new Action1<DataBinding.DataAdapter>()
	{
		@Override
		public void exec( DataAdapter param )
		{
			if( !fActivated )
				return;

			Object value = source.getValue();
			
			if( converter != null )
				value = converter.convert( value );

			destination.setValue( value );
		}
	};

	private final Action1<DataAdapter> onDestinationChanged = new Action1<DataBinding.DataAdapter>()
	{
		@Override
		public void exec( DataAdapter param )
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
