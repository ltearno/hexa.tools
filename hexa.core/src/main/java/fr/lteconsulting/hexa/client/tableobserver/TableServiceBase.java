package fr.lteconsulting.hexa.client.tableobserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import fr.lteconsulting.hexa.client.interfaces.IAsyncCallback;
import fr.lteconsulting.hexa.client.interfaces.IHasIntegerId;

public abstract class TableServiceBase<T extends IHasIntegerId>
{
	protected abstract void doDeleteRecord( int recordId, IAsyncCallback<Integer> callback );

	protected abstract void doUpdateField( int recordId, String fieldName, String wantedValue, IAsyncCallback<String> callback );

	protected abstract void doGetRecords( IAsyncCallback<Iterable<T>> callback );

	protected abstract void doGetRecord( int recordId, IAsyncCallback<T> callback );

	// to be overriden if needed
	protected boolean isClientInterestedByRecord( T record, Object clientParam )
	{
		return true;
	}

	protected TableServiceBase( String logName )
	{
		this.logName = logName;
	}

	String logName;

	HashMap<Integer, T> records = new HashMap<Integer, T>();
	boolean fWholeTableLoaded = false;
	boolean fFullLoadRequested = false;

	HashSet<IAsyncCallback<Integer>> waitingLoaded = null;
	HashSet<ClientInfo> waitingWholeTableLoaded = null;

	class ClientInfo
	{
		Object param;
		XTableListen<T> callback;
		ITableCommand<T> command;

		public ClientInfo( Object param, XTableListen<T> callback )
		{
			this.param = param;
			this.callback = callback;
			command = new ITableCommand<T>()
			{
				@Override
				public boolean isLoaded()
				{
					return fWholeTableLoaded;
				}

				@Override
				public void waitLoaded( IAsyncCallback<Integer> callback )
				{
					if( fWholeTableLoaded )
					{
						callback.onSuccess( 1 );
						return;
					}

					if( waitingLoaded == null )
						waitingLoaded = new HashSet<IAsyncCallback<Integer>>();
					waitingLoaded.add( callback );
				}

				@Override
				public void quit()
				{
					// GWT.log( "TableServiceBase "+logName+" client leaving..."
					// );
					clients.remove( ClientInfo.this );
				}

				@Override
				public void askRefreshTable()
				{
					if( fWholeTableLoaded )
					{
						ClientInfo.this.onWholeTable( records.values() );
					}
					else
					{
						if( fFullLoadRequested )
						{
							if( waitingWholeTableLoaded == null )
								waitingWholeTableLoaded = new HashSet<ClientInfo>();
							waitingWholeTableLoaded.add( ClientInfo.this );
						}
						else
						{
							fFullLoadRequested = true;
							doGetRecords( new IAsyncCallback<Iterable<T>>()
							{
								@Override
								public void onSuccess( Iterable<T> result )
								{
									records.clear();
									for( T e : result )
										records.put( e.getId(), e );

									// GWT.log(
									// "TableServiceBase "+logName+" WHOLE TABLE RECEIVED / "
									// + TableServiceBase.this.toString() );
									fWholeTableLoaded = true;
									fFullLoadRequested = false;

									ClientInfo.this.onWholeTable( records.values() );

									if( waitingWholeTableLoaded != null )
									{
										for( ClientInfo waitingClient : waitingWholeTableLoaded )
											waitingClient.onWholeTable( records.values() );
										waitingWholeTableLoaded.clear();
										waitingWholeTableLoaded = null;
									}
									if( waitingLoaded != null )
									{
										for( IAsyncCallback<Integer> cb : waitingLoaded )
											cb.onSuccess( 1 );
										waitingLoaded.clear();
										waitingLoaded = null;
									}
								}
							} );
						}
					}
				}

				@Override
				public T getRecord( int recordId )
				{
					assert (fWholeTableLoaded);
					return records.get( recordId );
				}

				@Override
				public Iterable<T> getRecords()
				{
					assert (fWholeTableLoaded);
					return records.values();
				}

				@Override
				public Iterable<T> getRecordsSorted( Comparator<T> comparator )
				{
					assert (fWholeTableLoaded);
					List<T> sortedList = new ArrayList<T>( records.values() );
					Collections.sort( sortedList, comparator );
					return sortedList;
				}

				@Override
				public boolean isEmpty()
				{
					assert (fWholeTableLoaded);
					if( ClientInfo.this.param != null )
					{
						Iterable<T> it = new FilteredIterable( records.values() );
						return !it.iterator().hasNext();
					}
					return records.isEmpty();
				}
			};
		}

		void onWholeTable( Iterable<T> records )
		{
			if( callback == null )
				return;
			if( param != null )
				callback.wholeTable( new FilteredIterable( records ) );
			else
				callback.wholeTable( records );
		}

		void onUpdatedRecord( T record )
		{
			if( callback == null )
				return;
			if( param != null && !isClientInterestedByRecord( record, param ) )
				return;
			callback.updated( record );
		}

		void onUpdatedRecordField( T record, String fieldName )
		{
			if( callback == null )
				return;
			if( param != null && !isClientInterestedByRecord( record, param ) )
				return;
			callback.updatedField( fieldName, record );
		}

		void onDeletedRecord( int recordId, T oldRecord )
		{
			if( callback == null )
				return;
			if( param != null && !isClientInterestedByRecord( oldRecord, param ) )
				return;
			callback.deleted( recordId, oldRecord );
		}

		class FilteredIterable implements Iterable<T>
		{
			Iterable<T> raw;

			public FilteredIterable( Iterable<T> raw )
			{
				this.raw = raw;
			}

			@Override
			public Iterator<T> iterator()
			{
				class FilteredIterator implements Iterator<T>
				{
					Iterator<T> rawIt = raw.iterator();
					T nextElem = null;

					FilteredIterator()
					{
						prepareNextElem();
					}

					@Override
					public boolean hasNext()
					{
						return nextElem != null;
					}

					@Override
					public T next()
					{
						T curElem = nextElem;

						prepareNextElem();

						return curElem;
					}

					void prepareNextElem()
					{
						nextElem = null;
						while( rawIt.hasNext() )
						{
							T test = rawIt.next();
							if( isClientInterestedByRecord( test, param ) )
							{
								nextElem = test;
								return;
							}
						}
					}

					@Override
					public void remove()
					{
						assert (false) : "remove is not allowed ...";
					}
				}

				return new FilteredIterator();
			}

		}
	}

	HashSet<ClientInfo> clients = new HashSet<ClientInfo>();

	public ITableCommand<T> listen( XTableListen<T> callback )
	{
		return listenInternal( null, callback );
	}

	protected ITableCommand<T> listenInternal( Object clientParam, XTableListen<T> callback )
	{
		ClientInfo client = new ClientInfo( clientParam, callback );
		clients.add( client );
		return client.command;
	}

	protected IAsyncCallback<T> getAddInternalCallback( final IAsyncCallback<T> callback )
	{
		return new IAsyncCallback<T>()
		{
			@Override
			public void onSuccess( T result )
			{
				if( result == null )
				{
					callback.onSuccess( null );
					return;
				}
				records.put( result.getId(), result );
				for( ClientInfo client : clients )
					client.onUpdatedRecord( result );
				if( callback != null )
					callback.onSuccess( result );
			}
		};
	}

	public void delete( final int recordId )
	{
		doDeleteRecord( recordId, new IAsyncCallback<Integer>()
		{
			@Override
			public void onSuccess( Integer result )
			{
				if( result < 0 )
					return;
				T oldRecord = records.remove( recordId );
				for( ClientInfo client : clients )
					client.onDeletedRecord( recordId, oldRecord );
			}
		} );
	}

	public void updateField( int recordId, final String fieldName, String newValue )
	{
		doUpdateField( recordId, fieldName, newValue, new IAsyncCallback<String>()
		{
			@Override
			public void onSuccess( String result )
			{
			}
		} );

		doGetRecord( recordId, new IAsyncCallback<T>()
		{
			@Override
			public void onSuccess( T result )
			{
				records.put( result.getId(), result );

				for( ClientInfo client : clients )
					client.onUpdatedRecordField( result, fieldName );
			}
		} );
	}

	public void getRecord( int recordId, IAsyncCallback<T> callback )
	{
		if( fWholeTableLoaded )
			callback.onSuccess( records.get( recordId ) );
		else
			doGetRecord( recordId, callback );
	}

	public void getRecords( IAsyncCallback<Iterable<T>> callback )
	{
		if( fWholeTableLoaded )
			callback.onSuccess( records.values() );
		else
			doGetRecords( callback );
	}
}
