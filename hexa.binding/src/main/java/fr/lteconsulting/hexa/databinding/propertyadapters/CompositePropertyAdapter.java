package fr.lteconsulting.hexa.databinding.propertyadapters;

import java.util.ArrayList;

import fr.lteconsulting.hexa.client.tools.Action2;
import fr.lteconsulting.hexa.databinding.PlatformSpecificProvider;

public class CompositePropertyAdapter implements PropertyAdapter
{
	public final static String HASVALUE_TOKEN = "$HasValue";
	public final static String DTOMAP_TOKEN = "$DTOMap";

	Object context;
	String[] path;

	PropertyAdapter[] adapters;
	Object[] adapterHandlerRegistrations;

	private ArrayList<ClientInfo> clients;

	public CompositePropertyAdapter( Object context, String path )
	{
		this.context = context;
		this.path = path.split( "\\." );

		adapters = new PropertyAdapter[this.path.length];
		adapterHandlerRegistrations = new Object[this.path.length];
	}

	private Action2<PropertyAdapter, Object> onPropertyChanged = new Action2<PropertyAdapter, Object>()
	{
		@Override
		public void exec( PropertyAdapter p1, Object p2 )
		{
			int adapterNo = (Integer) p2;

			// unregister all adapters with a position > adapterNo
			for( int p = adapterNo + 1; p < path.length; p++ )
			{
				if( adapters[p] == null || adapterHandlerRegistrations[p] == null )
					continue;

				adapters[p].removePropertyChangedHandler( adapterHandlerRegistrations[p] );
				adapters[p] = null;
				adapterHandlerRegistrations[p] = null;
			}

			// signal callbacks that a change occured
			if( clients != null )
			{
				for( ClientInfo client : clients )
					client.callback.exec( CompositePropertyAdapter.this, client.cookie );
			}
		}
	};

	@Override
	public Object getValue()
	{
		tryCreateAdapters();

		if( adapters[path.length - 1] != null )
		{
			return adapters[path.length - 1].getValue();
		}

		return null;
	}

	@Override
	public void setValue( Object object )
	{
		tryCreateAdapters();

		if( adapters[path.length - 1] != null )
			adapters[path.length - 1].setValue( object );
	}

	class ClientInfo
	{
		Action2<PropertyAdapter, Object> callback;
		Object cookie;
	}

	@Override
	public Object registerPropertyChanged( Action2<PropertyAdapter, Object> callback, Object cookie )
	{
		tryCreateAdapters();

		if( clients == null )
			clients = new ArrayList<ClientInfo>();

		ClientInfo client = new ClientInfo();
		client.callback = callback;
		client.cookie = cookie;

		// what if any sub path contains a property that's not subscribable ?

		clients.add( client );

		return client;
	}

	@Override
	public void removePropertyChangedHandler( Object handlerRegistration )
	{
		ClientInfo client = (ClientInfo) handlerRegistration;
		client.callback = null;
		client.cookie = null;

		clients.remove( client );
		if( clients.isEmpty() )
			clients = null;
		
		// remove adapters
		for( int i=0; i<adapters.length; i++ )
		{
			if( adapters[i] == null )
				continue;
			adapters[i].removePropertyChangedHandler( adapterHandlerRegistrations[i] );
		}
	}

	// create adapaters from the root context object to the end of the path, if
	// possible...
	private void tryCreateAdapters()
	{
		Object object = context;
	
		for( int p = 0; p < path.length; p++ )
		{
			if( object == null )
				return;
	
			// if no adapter has yet been created for this pathItem
			if( adapters[p] == null )
			{
				String pathItem = path[p];
	
				// try to find an adapter, otherwise create one or return null
				// to create an adapter, we need a context and a path item
				// context is the 'object' value (ie the value of the previous
				// pathItem or the root context)
				// path item is path[p]
				if(pathItem.charAt(0)=='$')
				{
					if( PlatformSpecificProvider.get().isBindingToken( pathItem ) )
						adapters[p] = PlatformSpecificProvider.get().createPropertyAdapter(object);
					else if( CompositePropertyAdapter.DTOMAP_TOKEN.equals( pathItem ) )
						adapters[p] = new DTOMapperPropertyAdapter( object );
				}
				else
				{
					adapters[p] = new ObjectPropertyAdapter( object, pathItem );
				}
	
				// we should subscribe to the value changes so that we can
				// subscribe to
				// new values when anything on the path changes
				adapterHandlerRegistrations[p] = adapters[p].registerPropertyChanged( onPropertyChanged, p );
			}
	
			if( p < path.length - 1 )
				object = adapters[p].getValue();
		}
	}
}
