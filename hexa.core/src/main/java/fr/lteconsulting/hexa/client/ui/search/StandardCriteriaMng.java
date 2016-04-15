package fr.lteconsulting.hexa.client.ui.search;

import java.util.ArrayList;

import com.google.gwt.json.client.JSONValue;

import fr.lteconsulting.hexa.client.common.Pair;

public class StandardCriteriaMng implements ICriteriaMng
{
	String displayName;
	String fieldName;
	ArrayList<Pair<String, String>> comparators;
	ValueUIFactory factory;

	public StandardCriteriaMng( String displayName, String fieldName, ArrayList<Pair<String, String>> comparators, ValueUIFactory factory )
	{
		this.displayName = displayName;
		this.fieldName = fieldName;
		this.comparators = comparators;
		this.factory = factory;
	}

	@Override
	public String getDisplayName()
	{
		return displayName;
	}

	@Override
	public ICriteriaWidget createCriteriaWidget( JSONValue json, boolean fReadOnly )
	{
		FieldCompCriteriaWidget w = new FieldCompCriteriaWidget( fieldName, comparators, factory.create( fReadOnly ), fReadOnly );
		w.setValue( json );
		return w;
	}
}
