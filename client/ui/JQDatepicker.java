package com.hexa.client.ui;

import com.hexa.client.tools.JQuery;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

public class JQDatepicker extends Widget
{
	public interface Callback
	{
		public void onDateSelected( String text );
	}
	
	Element input;
	
	public JQDatepicker()
	{
		input = DOM.createInputText();
		JQuery.jqDatepicker( input );
		setElement( input );
	}
	
	public void setFormat( String format )
	{
		jqDatepickerOption( input, "dateFormat", format );
	}
	
	private static native void jqDatepickerOption( Element e, String option, String value ) /*-{
		$wnd.$( e ).datepicker( "option", option, value );
	}-*/;
	
	private static native void jqDatepickerSetEventHandler( Element e, JQDatepicker.Callback datePicker ) /*-{
		$wnd.$( e ).datepicker( "option", "onSelect", function(dateText, inst) { datePicker.@com.hexa.client.ui.JQDatepicker.Callback::onDateSelected(Ljava/lang/String;)(dateText); } );
	}-*/;
	
	public void setCallback( Callback callback )
	{
		jqDatepickerSetEventHandler( input, callback );
	}
	
	public void setValueString( String date )
	{
		input.setInnerText( date );
		input.setPropertyString( "value", date );
	}
	
	public String getValueAsString()
	{
		return input.getPropertyString( "value" );
	}
}
