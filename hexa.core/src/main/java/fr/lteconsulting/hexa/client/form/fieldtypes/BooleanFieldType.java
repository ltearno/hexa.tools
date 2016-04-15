package fr.lteconsulting.hexa.client.form.fieldtypes;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.form.marshalls.Marshalls;

public class BooleanFieldType extends FieldTypeBase
{
	public Widget getWidget()
	{
		return new CheckBox();
	}

	public void setValue( Widget w, JSONValue value )
	{
		((CheckBox) w).setValue( Marshalls.bool.get( value ) );
	}

	public JSONValue getValue( Widget widget )
	{
		return Marshalls.bool.get( ((CheckBox) widget).getValue() );
	}

	@Override
	protected void installRealHandler( Widget widget )
	{
		((CheckBox) widget).addValueChangeHandler( new ValueChangeHandler<Boolean>()
		{
			public void onValueChange( ValueChangeEvent<Boolean> event )
			{
				signalChange( (CheckBox) event.getSource() );
			}
		} );
	}
}
