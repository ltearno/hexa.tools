package com.hexa.client.databinding;

import com.google.gwt.user.client.ui.HasValue;
import com.hexa.client.tools.Action1;

public class DataBinding
{
	private boolean fActivated;

	private final DataAdapter source;
	private final DataAdapter destination;
	private final Mode bindingMode;

	public interface DataAdapter
	{
		public void registerPropertyChanged( Action1<DataAdapter> callback );

		public Object getValue();

		public void setValue( Object object );
	}

	public DataBinding( Object source, String sourceProperty, Object destination, String destinationProperty, Mode bindingMode )
	{
		this( new ObjectAdapter( source, sourceProperty ), new ObjectAdapter( destination, destinationProperty ), bindingMode );
	}

	public DataBinding( Object source, String sourceProperty, HasValue<?> destination, Mode bindingMode )
	{
		this( new ObjectAdapter( source, sourceProperty ), new WidgetAdapter( destination ), bindingMode );
	}

	public DataBinding( DataAdapter source, DataAdapter destination, Mode bindingMode )
	{
		this.source = source;
		this.destination = destination;
		this.bindingMode = bindingMode;

		switch( bindingMode )
		{
		case OneWay:
			source.registerPropertyChanged( onSourceChanged );
			break;
		case OneWayToSource:
			destination.registerPropertyChanged( onDestinationChanged );
			break;
		case TwoWay:
			source.registerPropertyChanged( onSourceChanged );
			destination.registerPropertyChanged( onDestinationChanged );
			break;
		}
	}

	public void activate()
	{
		fActivated = true;

		onSourceChanged.exec( null );
	}

	private final Action1<DataAdapter> onSourceChanged = new Action1<DataBinding.DataAdapter>()
	{
		@Override
		public void exec( DataAdapter param )
		{
			if( !fActivated )
				return;

			Object value = source.getValue();

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

			source.setValue( value );
		}
	};
}
