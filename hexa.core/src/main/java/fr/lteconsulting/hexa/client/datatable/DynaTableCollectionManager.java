package fr.lteconsulting.hexa.client.datatable;

import java.util.HashMap;
import java.util.HashSet;

import com.google.gwt.core.client.Scheduler;

import fr.lteconsulting.hexa.client.databinding.NotifyPropertyChangedEvent;
import fr.lteconsulting.hexa.client.interfaces.IHasIntegerId;

public class DynaTableCollectionManager<T extends IHasIntegerId> extends TableCollectionManager<T>
{
	HashMap<Integer, Object> registrations = new HashMap<>();
	
	public DynaTableCollectionManager( boolean withDeleteColumn )
	{
		super( withDeleteColumn );
	}
	
	protected void onStoredRow( T record, Row row )
	{
	}
	
	protected void onForgetRow( int recordId )
	{
	}
	
	protected void onForgotAllRows()
	{
	}
	
	@Override
	protected void storeRow( T record, Row row )
	{
		super.storeRow( record, row );
		
		Object registration = NotifyPropertyChangedEvent.registerPropertyChangedEvent( record, "*", propertyChangeHandler );
		registrations.put( record.getId(), registration );
		
		onStoredRow( record, row );
	}
	
	@Override
	protected void forgetRow( int recordId )
	{
		onForgetRow( recordId );
		
		Object registration = registrations.remove( recordId );
		NotifyPropertyChangedEvent.removePropertyChangedHandler( registration );
		
		super.forgetRow( recordId );
	}
	
	@Override
	protected void forgetAllRows()
	{
		onForgotAllRows();
		
		for( Object registration : registrations.values() )
			NotifyPropertyChangedEvent.removePropertyChangedHandler( registration );
		registrations.clear();
		
		super.forgetAllRows();
	}
	
	private NotifyPropertyChangedEvent.Handler propertyChangeHandler = new NotifyPropertyChangedEvent.Handler()
	{	
		@Override
		public void onNotifyPropertChanged( NotifyPropertyChangedEvent event )
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
		if( ! isNew )
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
			for( T record : scheduledUpdates )
			{
				// do we really still listen to that element ? Or is it a late scheduled event ?
				if( registrations.containsKey( record.getId() ) )
					getDataPlug().updated( record );
			}
			scheduledUpdates.clear();
			
			scheduled = false;
		}
	};
}
