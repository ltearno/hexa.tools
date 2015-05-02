package fr.lteconsulting.hexa.client.databinding;

import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;

import fr.lteconsulting.hexa.client.databinding.propertyadapters.CompositePropertyAdapter;
import fr.lteconsulting.hexa.client.databinding.propertyadapters.PropertyAdapter;
import fr.lteconsulting.hexa.client.databinding.propertyadapters.gwt.WidgetPropertyAdapter;
import fr.lteconsulting.hexa.client.databinding.tools.Property;

class PlatformSpecificGwt implements PlatformSpecific
{
	public native DynamicPropertyBag getObjectDynamicPropertyBag( Object object )
	/*-{
		return object.__hexa_dynamic_ppty_bag || null;
	}-*/;

	public native void setObjectDynamicPropertyBag( Object object, DynamicPropertyBag bag )
	/*-{
		object.__hexa_dynamic_ppty_bag = bag;
	}-*/;

	@Override
	public boolean isBindingToken( String token )
	{
		return token.equals( CompositePropertyAdapter.HASVALUE_TOKEN );
	}

	@Override
	public <T> T getBindingValue( Object object, String token )
	{
		@SuppressWarnings( { "rawtypes", "unchecked" } )
		T result = (T) ((HasValue) object).getValue();
		return result;
	}

	@Override
	public boolean setBindingValue( Object object, String name, Object value )
	{
		assert object instanceof HasValue : "Object should be implementing HasValue<?> !";

		@SuppressWarnings( "unchecked" )
		HasValue<Object> hasValue = ((HasValue<Object>) object);

		hasValue.setValue( value, true );

		return true;
	}

	@Override
	public PropertyAdapter createPropertyAdapter( Object object )
	{
		return new WidgetPropertyAdapter( (HasValue<?>) object );
	}

	// Metadata

	@Override
	public native void setObjectMetadata( Object object, Object metadata )
	/*-{
		object.__hexa_metadata = metadata;
	}-*/;

	@Override
	public native <T> T getObjectMetadata( Object object )
	/*-{
		return object.__hexa_metadata || null;
	}-*/;

	// DTOMapper

	@Override
	public boolean isSpecificDataAdapter( Object object )
	{
		return object instanceof HasValue;
	}

	@Override
	public void fillSpecificDataAdapter( Object widget, Object context, String property, Class<?> srcPptyType, DataAdapterInfo res )
	{
		// try to guess the HasValue type
		res.dataType = null;
		if( widget instanceof TextBox )
			res.dataType = String.class;

		// try to find a converter if dataType does not match srcPptyType
		if( srcPptyType != null && res.dataType != null && res.dataType != srcPptyType && srcPptyType != Property.class )
		{
			// try to find a converter, if not : fail
			res.converter = Converters.findConverter( srcPptyType, res.dataType );
			if( res.converter == null )
				res.debugString = "[ERROR:CANNOT FIND CONVERTER FROM " + srcPptyType + " TO " + res.dataType + "]";
			else
				res.debugString = "[" + srcPptyType.getSimpleName() + ">" + res.dataType.getSimpleName() + "] " + res.debugString;
		}

		res.debugString += "\"" + property + ".$HasValue\"";

		res.adapter = new CompositePropertyAdapter( context, property + ".$HasValue" );
	}
}