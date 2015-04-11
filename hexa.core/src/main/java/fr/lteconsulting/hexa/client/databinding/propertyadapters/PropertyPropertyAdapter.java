package fr.lteconsulting.hexa.client.databinding.propertyadapters;

import java.util.ArrayList;

import fr.lteconsulting.hexa.client.databinding.NotifyPropertyChangedEvent;
import fr.lteconsulting.hexa.client.databinding.NotifyPropertyChangedEvent.Handler;
import fr.lteconsulting.hexa.client.databinding.tools.Property;
import fr.lteconsulting.hexa.client.tools.Action2;

/**
 * {@link PropertyAdapter} for the {@link Property} class
 * 
 * @author Arnaud Tournier (c) LTE Consulting - 2015 http://www.lteconsulting.fr
 *
 */
public class PropertyPropertyAdapter implements PropertyAdapter
{
	private Property<?> property;
	private Object propertyRegistration;
	private String pathItem;
	private ObjectPropertyAdapter adapter;
	private Object adapterRegistration;
	private ArrayList<Client> clients = new ArrayList<>();

	private class Client
	{
		Action2<PropertyAdapter, Object> callback;
		Object cookie;

		public Client( Action2<PropertyAdapter, Object> callback, Object cookie )
		{
			this.callback = callback;
			this.cookie = cookie;
		}
	}

	public PropertyPropertyAdapter( Property<?> property, String pathItem )
	{
		this.property = property;
		this.pathItem = pathItem;
	}

	@Override
	public Object getValue()
	{
		ensureAdapter(false);

		if( adapter == null )
			return null;

		return adapter.getValue();
	}

	@Override
	public void setValue( Object object )
	{
		ensureAdapter(false);

		if( adapter != null )
			adapter.setValue( object );
	}

	@Override
	public Object registerPropertyChanged( Action2<PropertyAdapter, Object> callback, Object cookie )
	{
		Client client = new Client( callback, cookie );
		clients.add( client );

		ensureRegisterPropertyValueChange();
		ensureAdapter(true);

		return client;
	}

	@Override
	public void removePropertyChangedHandler( Object handlerRegistration )
	{
		if( handlerRegistration instanceof Client )
			clients.remove( (Client) handlerRegistration );
	}

	private void ensureAdapter( boolean registerValueChange )
	{
		if( adapter == null )
		{
			if( property == null )
				return;

			Object value = property.getValue();
			if( value != null )
			{
				adapter = new ObjectPropertyAdapter( property.getValue(), pathItem );
				if( registerValueChange && adapterRegistration == null )
					adapterRegistration = adapter.registerPropertyChanged( valueChanged, null );
			}
		}
	}

	private void removeAdapter()
	{
		if( adapter != null )
		{
			if( adapterRegistration != null )
				adapter.removePropertyChangedHandler( adapterRegistration );
			adapter = null;
			adapterRegistration = null;
		}
	}

	private void ensureRegisterPropertyValueChange()
	{
		if( propertyRegistration != null )
			return;

		propertyRegistration = NotifyPropertyChangedEvent.registerPropertyChangedEvent( property, "value", new Handler()
		{
			@Override
			public void onNotifyPropertChanged( NotifyPropertyChangedEvent event )
			{
				removeAdapter();
				ensureAdapter(true);
			}
		} );
	}

	private Action2<PropertyAdapter, Object> valueChanged = new Action2<PropertyAdapter, Object>()
	{
		@Override
		public void exec( PropertyAdapter p1, Object p2 )
		{
			for( Client c : clients )
				c.callback.exec( PropertyPropertyAdapter.this, c.cookie );
		}
	};
}
