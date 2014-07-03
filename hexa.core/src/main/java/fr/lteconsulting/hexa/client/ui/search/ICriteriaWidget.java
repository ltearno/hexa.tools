package fr.lteconsulting.hexa.client.ui.search;

import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Widget;

public interface ICriteriaWidget
{
	JSONValue getValue();

	void setValue( JSONValue json );

	Widget asWidget();
}
