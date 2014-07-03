package fr.lteconsulting.hexa.client.ui.widget;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;
import fr.lteconsulting.hexa.client.tools.JQuery;

public class JQDatepicker extends Widget
{
	public interface Callback
	{
		public void onDateSelected( String text );
	}

	boolean fInline;
	Element input;

	public JQDatepicker()
	{
		this( false );
	}

	public JQDatepicker( boolean fInline )
	{
		this.fInline = fInline;

		if( fInline )
			input = DOM.createDiv();
		else
			input = DOM.createInputText();

		JQuery.get().jqDatepicker( input );
		jqDatepickerOption( input, "dateFormat", "yy-mm-dd" );
		setElement( input );
	}

	private static native void jqDatepickerOption( Element e, String option, String value )
	/*-{
		$wnd.$( e ).datepicker( "option", option, value );
	}-*/;

	private static native void jqDatepickerSetEventHandler( Element e, JQDatepicker.Callback datePicker )
	/*-{
		$wnd.$( e ).datepicker( "option", "onSelect", function(dateText, inst) { datePicker.@fr.lteconsulting.hexa.client.ui.widget.JQDatepicker.Callback::onDateSelected(Ljava/lang/String;)(dateText); } );
		$wnd.$( e ).datepicker( "option", "onClose", function(dateText, inst) { datePicker.@fr.lteconsulting.hexa.client.ui.widget.JQDatepicker.Callback::onDateSelected(Ljava/lang/String;)(dateText); } );
	}-*/;

	private static native void jqDatepickerRefresh( Element e ) /*-{
																$wnd.$( e ).datepicker( "refresh" );
																}-*/;

	public void setCallback( Callback callback )
	{
		jqDatepickerSetEventHandler( input, callback );
	}
	
	public void setValueString( String date )
	{
		if( date == null || date.isEmpty() )
			return;
		
		setValueString( input, date );
	}

	private native final void setValueString( Element element, String date )
	/*-{
		$wnd.$( element ).datepicker( "setDate", date );
	}-*/;
//	{
//		if( ! fInline )
//			input.setInnerText( date );
//
//		// jqDatepickerRefresh( input );
//		input.setPropertyString( "value", date );
//	}

	public String getValueAsString()
	{
		return input.getPropertyString( "value" );
	}
}
