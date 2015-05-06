package fr.lteconsulting.hexa.client.datatable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.shared.GWT;

import fr.lteconsulting.hexa.client.tools.Action1;
import fr.lteconsulting.hexa.databinding.properties.Properties;
import fr.lteconsulting.hexa.databinding.properties.PropertyChangedEvent;
import fr.lteconsulting.hexa.databinding.properties.PropertyChangedHandler;
import fr.lteconsulting.hexa.databinding.watchablecollection.Change;
import fr.lteconsulting.hexa.databinding.watchablecollection.WatchableCollection;

public class DynaObjectCollectionManager<T> extends ObjectCollectionManager<T>
{
	HashMap<T, Object> registrations = new HashMap<>();

	protected void onStoredRow( T record, Row row )
	{
	}

	protected void onForgetRow( T record )
	{
	}

	protected void onForgotAllRows()
	{
	}
	
	protected void onUpdatedRecord( T record )
	{
	}
	
	public DynaObjectCollectionManager( boolean withDeleteColumn )
	{
		super( withDeleteColumn );
	}

	@Override
	protected void storeRow( T record, Row row )
	{
		super.storeRow( record, row );

		Object registration = Properties.register( record, "*", propertyChangeHandler );
		registrations.put( record, registration );

		onStoredRow( record, row );
	}

	@Override
	protected void forgetRow( T record )
	{
		onForgetRow( record );

		Object registration = registrations.remove( record );
		Properties.removeHandler( registration );

		super.forgetRow( record );
	}

	@Override
	protected void forgetAllRows()
	{
		onForgotAllRows();

		for( Object registration : registrations.values() )
			Properties.removeHandler( registration );
		registrations.clear();

		super.forgetAllRows();
	}

	private PropertyChangedHandler propertyChangeHandler = new PropertyChangedHandler()
	{
		@Override
		public void onPropertyChanged( PropertyChangedEvent event )
		{
			@SuppressWarnings( "unchecked" )
			T record = (T) event.getSender();

			scheduleChange( record );
		}
	};

	private boolean scheduled = false;
	private HashSet<T> scheduledUpdates = new HashSet<>();

	private void scheduleChange( T record )
	{
		boolean isNew = scheduledUpdates.add( record );
		if( !isNew )
			return;

		if( scheduled )
			return;
		scheduled = true;
		Scheduler.get().scheduleDeferred( updateRecordsCommand );
	}

	private Scheduler.ScheduledCommand updateRecordsCommand = new Scheduler.ScheduledCommand()
	{
		@Override
		public void execute()
		{
			HashSet<T> updates = scheduledUpdates;
			scheduledUpdates = new HashSet<>();
			scheduled = false;
			
			for( T record : updates )
			{
				// do we really still listen to that element ? Or is it a late
				// scheduled event ?
				if( rows.containsKey( record ) )
				{
					GWT.log( "UPDATE ON ROW " + record );
					getDataPlug().updated( record );
					onUpdatedRecord( record );
				}
			}
		}
	};

	public void setDataSource( WatchableCollection<T> source )
	{
		source.removeCallback( sourceCallback );
		source.addCallbackAndSendAll( sourceCallback );
	}

	Action1<List<Change>> sourceCallback = new Action1<List<Change>>()
	{
		@Override
		public void exec( List<Change> param )
		{
			for( Change c : param )
			{
				T record = c.getItem();
				switch( c.getType() )
				{
					case ADD:
						dataPlug.updated( record );
						break;
					case REMOVE:
						dataPlug.deleted( record );
						break;
				}
			}
		}
	};
}
