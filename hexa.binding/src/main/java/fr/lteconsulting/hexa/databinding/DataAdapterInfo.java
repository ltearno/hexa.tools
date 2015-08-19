package fr.lteconsulting.hexa.databinding;

import fr.lteconsulting.hexa.databinding.propertyadapters.PropertyAdapter;

public final class DataAdapterInfo
{
	PropertyAdapter adapter;
	Converter converter;
	Class<?> dataType;
	String debugString;

	public DataAdapterInfo() {}

	public DataAdapterInfo(PropertyAdapter adapter, Converter converter, Class<?> dataType, String debugString) {
		this.adapter = adapter;
		this.converter = converter;
		this.dataType = dataType;
		this.debugString = debugString;
	}

	public final PropertyAdapter getAdapter() {
		return adapter;
	}

	public final void setAdapter(PropertyAdapter adapter) {
		this.adapter = adapter;
	}

	public final Converter getConverter() {
		return converter;
	}

	public final void setConverter(Converter converter) {
		this.converter = converter;
	}

	public final Class<?> getDataType() {
		return dataType;
	}

	public final void setDataType(Class<?> dataType) {
		this.dataType = dataType;
	}

	public final String getDebugString() {
		return debugString;
	}

	public final void setDebugString(String debugString) {
		this.debugString = debugString;
	}
}