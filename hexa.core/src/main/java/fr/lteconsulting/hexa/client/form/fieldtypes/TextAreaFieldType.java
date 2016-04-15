package fr.lteconsulting.hexa.client.form.fieldtypes;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.form.fieldtypes.FieldTypeBase;
import fr.lteconsulting.hexa.client.form.marshalls.Marshalls;

public class TextAreaFieldType extends FieldTypeBase
{
	private static final int stdFieldWidth = 300;

	public Widget getWidget()
	{
		TextArea ta = new TextArea();

		ta.setWidth( stdFieldWidth + "px" );
		ta.setHeight( "120px" );

		return ta;
	}

	public void setValue( Widget w, JSONValue value )
	{
		((TextArea) w).setText( Marshalls.string.get( value ) );
	}

	@Override
	public JSONValue getValue( Widget widget )
	{
		return Marshalls.string.get( ((TextArea) widget).getText() );
	}

	@Override
	protected void installRealHandler( Widget widget )
	{
		((TextArea) widget).addChangeHandler( new com.google.gwt.event.dom.client.ChangeHandler()
		{
			public void onChange( ChangeEvent event )
			{
				signalChange( (TextArea) event.getSource() );
			}
		} );
	}
}
