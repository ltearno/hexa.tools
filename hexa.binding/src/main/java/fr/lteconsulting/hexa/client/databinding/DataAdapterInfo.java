package fr.lteconsulting.hexa.client.databinding;

import fr.lteconsulting.hexa.client.databinding.propertyadapters.PropertyAdapter;

class DataAdapterInfo
{
	PropertyAdapter adapter;
	Converter converter;
	Class<?> dataType;

	String debugString;
}