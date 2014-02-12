package com.hexa.client.ui.widget;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.hexa.client.common.HexaDate;
import com.hexa.client.interfaces.IValueChangeHandler;
import com.hexa.client.ui.dialog.MyPopupPanel;

public class DateSelector extends Composite implements JQDatepicker.Callback
{
	boolean enabled = true;
	TextBox textBox = new TextBox();
	MyPopupPanel popup = null;
	JQDatepicker datePicker = null;

	ArrayList<IValueChangeHandler<HexaDate>> handlers = new ArrayList<IValueChangeHandler<HexaDate>>();

	public DateSelector()
	{
		initWidget( textBox );

		textBox.addFocusHandler( new FocusHandler()
		{
			@Override
			public void onFocus( FocusEvent event )
			{
				if( ! enabled )
					return;
				
				showPopup();
			}
		} );
	}

	public void clear()
	{
		setDate( null );
	}

	public HexaDate getDate()
	{
		String tb = textBox.getText();
		HexaDate res = HexaDate.getDisplayFormat().getHexaDateFromDisplayString( tb );
		return res;
	}

	public void setDate( HexaDate hexaDate )
	{
		if( hexaDate == null )
		{
			textBox.setText( "" );
			return;
		}
		
		String display = hexaDate.getDisplayString();
		
		textBox.setText( display );
		if( datePicker != null )
			datePicker.setValueString( hexaDate.getString() );
	}

	public void setDate( HexaDate date, boolean fFireEvent )
	{
		setDate( date );

		if( fFireEvent )
			fire( date );
	}

	public void addValueChangeHandler( IValueChangeHandler<HexaDate> handler )
	{
		handlers.add( handler );
	}

	public void setEnabled( boolean enabled )
	{
		this.enabled = enabled;
		textBox.setEnabled( enabled );
	}

	private void showPopup()
	{
		if( datePicker == null )
		{
			datePicker = new JQDatepicker( true );
			datePicker.setCallback( this );
		}

		if( popup == null )
		{
			popup = new MyPopupPanel( true, true );
			popup.setWidget( datePicker );
		}

		popup.showRelativeTo( textBox );

		HexaDate hexaDate = getDate();
		if( hexaDate != null )
			datePicker.setValueString( hexaDate.getString() );
	}

	private void hidePopup()
	{
		if( popup != null )
			popup.hide();
	}

	@Override
	public void onDateSelected( String text )
	{
		hidePopup();
		
		HexaDate date = new HexaDate( text );
		setDate( date, true );
	}

	private void fire( HexaDate date )
	{
		for( IValueChangeHandler<HexaDate> handler : handlers )
			handler.onValueChange( date );
	}
}
