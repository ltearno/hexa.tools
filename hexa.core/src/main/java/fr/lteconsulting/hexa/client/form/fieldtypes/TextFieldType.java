package fr.lteconsulting.hexa.client.form.fieldtypes;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.form.marshalls.Marshalls;

public class TextFieldType extends FieldTypeBase
{
	private static final int stdFieldWidth = 300;

	public Widget getWidget()
	{
		TextBox tb = new TextBox();

		tb.setMaxLength( 120 );
		tb.setWidth( stdFieldWidth + "px" );

		return tb;
	}

	public void setValue( Widget w, JSONValue value )
	{
		((TextBox) w).setText( Marshalls.string.get( value ) );

		signalChange( (TextBox) w );
	}

	public JSONValue getValue( Widget widget )
	{
		return Marshalls.string.get( ((TextBox) widget).getText() );
	}

	@Override
	protected void installRealHandler( Widget widget )
	{
		((TextBox) widget).addChangeHandler( new com.google.gwt.event.dom.client.ChangeHandler()
		{
			public void onChange( ChangeEvent event )
			{
				signalChange( (TextBox) event.getSource() );
			}
		} );
	}
}
