package fr.lteconsulting.hexa.client.ui.search;

import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.IsWidget;

public interface SearchMng
{
	public interface SearchInstance extends IsWidget
	{
		JSONValue getValue();

		void setValue( JSONValue json );
	}

	SearchInstance createSearchWidget( boolean fReadOnly );
}
