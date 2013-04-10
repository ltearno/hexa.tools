package com.hexa.client.ui.search;

import com.google.gwt.json.client.JSONValue;

public interface ICriteriaMng
{
	String getDisplayName();
	ICriteriaWidget createCriteriaWidget( JSONValue json );
}
