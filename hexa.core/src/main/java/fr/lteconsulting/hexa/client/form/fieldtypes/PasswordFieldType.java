package fr.lteconsulting.hexa.client.form.fieldtypes;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.form.marshalls.Marshalls;
import fr.lteconsulting.hexa.client.tools.MD5;

public class PasswordFieldType extends FieldTypeBase
{
	public Widget getWidget()
	{
		return new PasswordTextBox();
	}

	public void setValue( Widget w, JSONValue value )
	{
		((PasswordTextBox) w).setText( Marshalls.string.get( value ) );
	}

	public JSONValue getValue( Widget widget )
	{
		return Marshalls.string.get( MD5.md5( ((PasswordTextBox) widget).getText() ) );
	}

	@Override
	protected void installRealHandler( Widget widget )
	{
		((PasswordTextBox) widget).addChangeHandler( new com.google.gwt.event.dom.client.ChangeHandler()
		{
			public void onChange( ChangeEvent event )
			{
				signalChange( (PasswordTextBox) event.getSource() );
			}
		} );
	}
}
