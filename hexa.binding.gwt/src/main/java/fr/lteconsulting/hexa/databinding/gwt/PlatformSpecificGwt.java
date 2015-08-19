package fr.lteconsulting.hexa.databinding.gwt;

import java.util.HashMap;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;

import fr.lteconsulting.hexa.databinding.Converters;
import fr.lteconsulting.hexa.databinding.DataAdapterInfo;
import fr.lteconsulting.hexa.databinding.PlatformSpecific;
import fr.lteconsulting.hexa.databinding.gwt.propertyadapters.ValuePropertyAdapter;
import fr.lteconsulting.hexa.databinding.properties.DynamicPropertyBag;
import fr.lteconsulting.hexa.databinding.propertyadapters.CompositePropertyAdapter;
import fr.lteconsulting.hexa.databinding.propertyadapters.PropertyAdapter;
import fr.lteconsulting.hexa.databinding.tools.Property;

public final class PlatformSpecificGwt implements PlatformSpecific {
	private static final PlatformSpecificGwt INSTANCE;
	
	static {
		Logger.getLogger(PlatformSpecificGwt.class.getName()).info("PlatformSpecificGwt STARTED");
		
		INSTANCE = new PlatformSpecificGwt();
	}
	
	public static PlatformSpecificGwt get() {
		return INSTANCE;
	}
	
	private PlatformSpecificGwt() {}
	
	private static class DynamicPropertyBagAccessJre {
		private static HashMap<Integer, DynamicPropertyBag> propertyBags = new HashMap<>();

		static void setObjectDynamicPropertyBag(Object object, DynamicPropertyBag bag) {
			propertyBags.put(System.identityHashCode(object), bag);
		}

		static DynamicPropertyBag getObjectDynamicPropertyBag(Object object) {
			return propertyBags.get(System.identityHashCode(object));
		}
	}
	
	@Override
	public DynamicPropertyBag getObjectDynamicPropertyBag(Object object) {
		if(GWT.isScript())
			return getObjectDynamicPropertyBagImpl(object);
		else
			return DynamicPropertyBagAccessJre.getObjectDynamicPropertyBag(object);
	}
	
	private native DynamicPropertyBag getObjectDynamicPropertyBagImpl(Object object) /*-{
		return object.__hexa_dynamic_ppty_bag || null;
	}-*/;

	@Override
	public void setObjectDynamicPropertyBag(Object object, DynamicPropertyBag bag) {
		if(GWT.isScript())
			setObjectDynamicPropertyBagImpl(object, bag);
		else
			DynamicPropertyBagAccessJre.setObjectDynamicPropertyBag(object, bag);
	}
	
	private native void setObjectDynamicPropertyBagImpl(Object object, DynamicPropertyBag bag) /*-{
		object.__hexa_dynamic_ppty_bag = bag;
	}-*/;

	@Override
	public boolean isBindingToken(String token) {
		return token.equals(CompositePropertyAdapter.HASVALUE_TOKEN);
	}

	@Override
	public <T> T getBindingValue(Object object, String token) {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		T result = (T) ((HasValue) object).getValue();
		return result;
	}

	@Override
	public boolean setBindingValue(Object object, String name, Object value) {
		assert object instanceof HasValue : "Object should be implementing HasValue<?> !";

		@SuppressWarnings("unchecked")
		HasValue<Object> hasValue = ((HasValue<Object>) object);

		hasValue.setValue(value, true);
		return true;
	}

	@Override
	public PropertyAdapter createPropertyAdapter(Object object) {
		return new ValuePropertyAdapter((HasValue<?>) object);
	}

	// Metadata
	
	private static class MetatdataJre {
		private static final HashMap<Integer, Object> metadatas = new HashMap<>();

		static void setObjectMetadata(Object object, Object metadata) {
			metadatas.put(System.identityHashCode(object), metadata);
		}

		static <T> T getObjectMetadata(Object object) {
			@SuppressWarnings("unchecked")
			T result = (T) metadatas.get(System.identityHashCode(object));
			return result;
		}
	}

	@Override
	public void setObjectMetadata(Object object, Object metadata) {
		if(GWT.isScript())
			setObjectMetadataImpl(object, metadata);
		else
			MetatdataJre.setObjectMetadata(object, metadata);
	}
	
	private native void setObjectMetadataImpl(Object object, Object metadata) /*-{
		object.__hexa_metadata = metadata;
	}-*/;
	
	@Override
	public <T> T getObjectMetadata(Object object) {
		if(GWT.isScript())
			return getObjectMetadataImpl(object);
		else
			return MetatdataJre.getObjectMetadata(object);
	}
	
	private native <T> T getObjectMetadataImpl(Object object) /*-{
		return object.__hexa_metadata || null;
	}-*/;

	// DTOMapper

	@Override
	public boolean isSpecificDataAdapter(Object object) {
		return object instanceof HasValue;
	}

	@Override
	public void fillSpecificDataAdapter(Object widget, Object context, String property, Class<?> srcPptyType, DataAdapterInfo res) {
		// try to guess the HasValue type
		res.setDataType(null);
		if(widget instanceof TextBox)
			res.setDataType(String.class);

		String debugString = "";

		// try to find a converter if dataType does not match srcPptyType
		Class<?> dataType = res.getDataType();
		if(srcPptyType != null && dataType != null && dataType != srcPptyType && srcPptyType != Property.class) {
			// try to find a converter, if not : fail
			res.setConverter(Converters.findConverter(srcPptyType, dataType));
			if(res.getConverter() == null)
				debugString = "[ERROR: Cannot find converter from " + srcPptyType + " to " + dataType + "]";
			else
				debugString = "[" + srcPptyType.getSimpleName() + ">" + dataType.getSimpleName() + "] " + debugString;
		}

		debugString += "\"" + property + ".$HasValue\"";
		res.setDebugString(debugString);

		res.setAdapter(new CompositePropertyAdapter(context, property + ".$HasValue"));
	}
}