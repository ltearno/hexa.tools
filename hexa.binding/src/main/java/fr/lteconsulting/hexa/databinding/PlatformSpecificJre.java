package fr.lteconsulting.hexa.databinding;

import java.util.HashMap;

import fr.lteconsulting.hexa.databinding.properties.DynamicPropertyBag;
import fr.lteconsulting.hexa.databinding.propertyadapters.PropertyAdapter;

class PlatformSpecificJre implements PlatformSpecific
{
	private static final PlatformSpecificJre INSTANCE = new PlatformSpecificJre();

	public static PlatformSpecificJre get()
	{
		return INSTANCE;
	}

	private PlatformSpecificJre()
	{
	}
	
	private static HashMap<Integer, DynamicPropertyBag> propertyBags = new HashMap<>();

	public void setObjectDynamicPropertyBag( Object object, DynamicPropertyBag bag )
	{
		propertyBags.put( System.identityHashCode( object ), bag );
	}

	public DynamicPropertyBag getObjectDynamicPropertyBag( Object object )
	{
		return propertyBags.get( System.identityHashCode( object ) );
	}

	@Override
	public boolean isBindingToken( String token )
	{
		return false;
	}

	@Override
	public <T> T getBindingValue( Object object, String token )
	{
		return null;
	}

	@Override
	public boolean setBindingValue( Object object, String name, Object value )
	{
		return false;
	}

	@Override
	public PropertyAdapter createPropertyAdapter( Object object )
	{
		return null;
	}

	// Metadata

	private static HashMap<Integer, Object> metadatas = new HashMap<>();

	@Override
	public void setObjectMetadata( Object object, Object metadata )
	{
		metadatas.put( System.identityHashCode( object ), metadata );
	}

	@Override
	public <T> T getObjectMetadata( Object object )
	{
		@SuppressWarnings( "unchecked" )
		T result = (T) metadatas.get( System.identityHashCode( object ) );
		return result;
	}

	// DTOMapper

	@Override
	public boolean isSpecificDataAdapter( Object object )
	{
		return false;
	}

	@Override
	public void fillSpecificDataAdapter( Object widget, Object context, String property, Class<?> srcPptyType, DataAdapterInfo res )
	{
		throw new IllegalStateException();
	}
}