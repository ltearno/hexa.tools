package fr.lteconsulting.hexa.client.interfaces;

import com.google.gwt.user.client.ui.Widget;

public interface ICriteriaFieldMng
{
	public interface Callback
	{
		void onValueChange( String newValue );
	}

	public Widget getValueWidget( final String value, final Callback callback );

	public String getDisplayName();

	public String getField();
}
