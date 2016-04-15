package fr.lteconsulting.hexa.client.form.fieldtypes;

import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Widget;

public interface FieldType
{
	public interface FieldChangeHandler
	{
		void onFieldChange( Object cookie );
	}

	public interface FieldChangeHandlerManager
	{
		Object addChangeHandler( FieldChangeHandler handler, Object cookie );

		void removeChangeHandler( Object registration );
	}

	// factory to create a type instance
	Widget getWidget();

	// methods to work with a type instance
	void setValue( Widget widget, JSONValue value );

	JSONValue getValue( Widget widget );

	FieldChangeHandlerManager getHandlerManager( Widget widget );
}
