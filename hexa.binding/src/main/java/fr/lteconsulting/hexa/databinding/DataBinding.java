package fr.lteconsulting.hexa.databinding;

import java.util.logging.Logger;

import fr.lteconsulting.hexa.client.tools.Action2;
import fr.lteconsulting.hexa.databinding.propertyadapters.ObjectPropertyAdapter;
import fr.lteconsulting.hexa.databinding.propertyadapters.PropertyAdapter;

/**
 * Manages the binding between a source and a destination.
 * 
 * <p>
 * The data binding has several options like OneWay, TwoWay, ...<br/>
 * The data propagation can happen synchronously after a data changed, or it can
 * happen asynchronously through a deferred command.
 * 
 * @author Arnaud Tournier
 *
 */
public class DataBinding
{
	private static final Logger LOGGER = Logger.getLogger( DataBinding.class.getName() );

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

	/**
	 * Activates the data binding and propagates the source to the destination
	 */
	public DataBinding activate()
	{
		fActivated = true;

		log( "activation" );

		onSourceChanged.exec( null, null );

		return this;
	}

	/**
	 * Suspend the data binding. Can be reactivated with {@link activate}
	 */
	public DataBinding suspend()
	{
		fActivated = false;
		
		log( "suspended" );
		
		return this;
	}

	/**
	 * Terminates the Data Binding activation and cleans up all related
	 * resources. You should call this method when you want to free the binding,
	 * in order to lower memory usage.
	 */
	public void terminate()
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

	protected void log( String text )
	{
		if( logPrefix == null )
			return;
	
		LOGGER.info( "DATABINDING " + logPrefix + " : " + text );
	}

	private final Action2<PropertyAdapter, Object> onSourceChanged = new Action2<PropertyAdapter, Object>()
	{
		@Override
		public void exec( PropertyAdapter param, Object cookie )
		{
			// prevent us to wake up ourselves
			if( fSettingSource )
				return;

			if( logPrefix != null )
				log( "source changed, propagating to destination ..." );

			if( !fActivated )
				return;

			Object value = source.getValue();
			if( logPrefix != null )
				log(" - source value : " + value);

			if( converter != null )
			{
				if( logPrefix != null )
					log( "... converting value ..." );
				value = converter.convert( value );
				if( logPrefix != null )
					log(" - converted to : " + value);
			}

			fSettingDestination = true;
			destination.setValue( value );
			fSettingDestination = false;

			if( logPrefix != null )
				log( " - done propagating source" );
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
			
			if( !fActivated )
				return;
			
			log( "destination changed, propagating to source ..." );

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
