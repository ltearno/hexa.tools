package fr.lteconsulting.hexa.client.databinding.propertyadapters;

import fr.lteconsulting.hexa.client.databinding.INotifyPropertyChanged;
import fr.lteconsulting.hexa.client.databinding.NotifyPropertyChangedEvent;
import fr.lteconsulting.hexa.client.databinding.NotifyPropertyChangedEvent.Handler;
import fr.lteconsulting.hexa.client.tools.Action2;

/**
 * A PropertyAdapter implementation which is able to work with an object's field or property
 * 
 * To access the object's property, first the adapter tries to find a getter/setter. Then, if no
 * access method is found, the adapter works with the object's field value directly.
 * 
 * @author Arnaud
 *
 */
public class ObjectPropertyAdapter implements PropertyAdapter, Handler
{
	private final Object source;
	private final String sourceProperty;

	private Action2<PropertyAdapter, Object> callback;
	private Object cookie;

	public ObjectPropertyAdapter( Object source, String sourceProperty )
	{
		this.source = source;
		this.sourceProperty = sourceProperty;
	}

	@Override
	public Object registerPropertyChanged( Action2<PropertyAdapter, Object> callback, Object cookie )
	{
		if( source == null )
			return null;
		
		this.callback = callback;
		this.cookie = cookie;
		
		// How do we register ?
		if( source instanceof INotifyPropertyChanged )
		{
			// Through object's interface implementation
			return ((INotifyPropertyChanged) source).registerPropertyChangedEvent( sourceProperty, this );
		}
		else
		{
			// Ask directly to the property system
			return NotifyPropertyChangedEvent.registerPropertyChangedEvent( source, sourceProperty, this );
		}
	}

	@Override
	public void removePropertyChangedHandler( Object registration )
	{
		if( source instanceof INotifyPropertyChanged )
		{
			// Through object's interface implementation
			((INotifyPropertyChanged) source).removePropertyChangedHandler( registration );
		}
		else
		{
			// Ask directly to the property system
			NotifyPropertyChangedEvent.removePropertyChangedHandler( registration );
		}
	}

	@Override
	public Object getValue()
	{
		return ObjectPropertiesUtils.GetProperty( source, sourceProperty, true );
	}

	@Override
	public void setValue( Object value )
	{
		ObjectPropertiesUtils.SetProperty( source, sourceProperty, value, true );
	}

	@Override
	public void onNotifyPropertChanged( NotifyPropertyChangedEvent event )
	{
		if( callback == null )
			return;

		callback.exec( this, cookie );
	}
}