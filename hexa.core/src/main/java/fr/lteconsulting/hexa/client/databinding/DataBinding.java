package fr.lteconsulting.hexa.client.databinding;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.HasValue;
import fr.lteconsulting.hexa.client.databinding.propertyadapters.ObjectPropertyAdapter;
import fr.lteconsulting.hexa.client.databinding.propertyadapters.PropertyAdapter;
import fr.lteconsulting.hexa.client.databinding.propertyadapters.WidgetPropertyAdapter;
import fr.lteconsulting.hexa.client.tools.Action2;

/**
 * Manages the binding between a source and a destination.
 * 
 * <p>The data binding has several options like OneWay,
 * TwoWay, ...<br/>
 * The data propagation can happen synchronously after a data changed,
 * or it can happen asynchronously through a deferred command.
 * 
 * @author Arnaud Tournier
 *
 */
public class DataBinding
{
	private boolean fActivated;

	private PropertyAdapter source;
	private Object sourceHandler;
	private boolean fSettingSource;

	private PropertyAdapter destination;
	private Object destinationHandler;
	private boolean fSettingDestination;

	private Converter converter;

	private final String logPrefix;

	public DataBinding( Object source, String sourceProperty, Object destination, String destinationProperty, Mode bindingMode )
	{
		this( new ObjectPropertyAdapter( source, sourceProperty ), new ObjectPropertyAdapter( destination, destinationProperty ), bindingMode, null, null );
	}

	public DataBinding( Object source, String sourceProperty, HasValue<?> destination, Mode bindingMode )
	{
		this( new ObjectPropertyAdapter( source, sourceProperty ), new WidgetPropertyAdapter( destination ), bindingMode, null, null );
	}

	public DataBinding( PropertyAdapter source, PropertyAdapter destination, Mode bindingMode, Converter converter, String logPrefix )
	{
		this.source = source;
		this.destination = destination;
		this.converter = converter;
		this.logPrefix = logPrefix;

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

	private void log( String text )
	{
		if( logPrefix == null )
			return;

		GWT.log( "DATABINDING " + logPrefix + " : " + text );
	}

	public DataBinding activate()
	{
		fActivated = true;

		log( "activation" );

		onSourceChanged.exec( null, null );
		
		return this;
	}

	public void deferActivate()
	{
		log( "deferred activation..." );

		Scheduler.get().scheduleDeferred( new ScheduledCommand()
		{
			@Override
			public void execute()
			{
				activate();
			}
		} );
	}

	/**
	 * Terminates the Data Binding activation and cleans up all related resources.
	 * You should call this method when you want to free the binding, in order to
	 * lower memory usage.
	 */
	public void term()
	{
		log( "term" );

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
			// prevent us to wake up ourselves
			if( fSettingSource )
				return;

			log( "source changed, propagating to destination ..." );

			if( !fActivated )
				return;

			Object value = source.getValue();

			if( converter != null )
			{
				log( "... converting value ..." );
				value = converter.convert( value );
			}

			fSettingDestination = true;
			destination.setValue( value );
			fSettingDestination = false;

			log( "done setting source to " + value );
		}
	};

	private final Action2<PropertyAdapter, Object> onDestinationChanged = new Action2<PropertyAdapter, Object>()
	{
		@Override
		public void exec( PropertyAdapter param, Object cookie )
		{
			// prevent us to wake up ourselves
			if( fSettingDestination )
				return;

			log( "destination changed, propagating to source ..." );

			if( !fActivated )
				return;

			Object value = destination.getValue();

			if( converter != null )
			{
				log( "... converting value ..." );
				value = converter.convertBack( value );
			}

			fSettingSource = true;
			source.setValue( value );
			fSettingSource = false;

			log( "done setting destination to " + value );
		}
	};
}
