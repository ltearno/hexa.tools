package fr.lteconsulting.hexa.databinding.propertyadapters;

import fr.lteconsulting.hexa.client.tools.Action2;

/**
 * A PropertyAdapter implementation that is only able to set its target property value
 * 
 * This adapter can only be used with a data binding target and CANNOT be used as a data binding source
 * 
 * @author Arnaud
 *
 */
public abstract class WriteOnlyPropertyAdapter implements PropertyAdapter
{
	@Override
	public final Object registerPropertyChanged( Action2<PropertyAdapter, Object> callback, Object cookie )
	{
		return null;
	}

	@Override
	public final void removePropertyChangedHandler( Object handler )
	{
		if( handler != null )
			throw new RuntimeException( "Not implemented 'removePropertyChangedHandler' method in WriteOnlyDataAdapter" );
	}

	@Override
	public final Object getValue()
	{
		throw new RuntimeException( "Not implemented 'getValue' method in WriteOnlyDataAdapter" );
	}
}
