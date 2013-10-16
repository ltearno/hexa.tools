package com.hexa.client.ui;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.hexa.client.common.HexaDate;
import com.hexa.client.interfaces.IValueChangeHandler;

public class DateSelector extends Composite implements JQDatepicker.Callback
{
	boolean enabled = true;

	TextBox textBox = new TextBox();

	boolean fCallbackSet = false; // denotes if a call to
									// JQDatepicker.setCallback has already been
									// made

	ArrayList<IValueChangeHandler<HexaDate>> handlers = new ArrayList<IValueChangeHandler<HexaDate>>();

	public DateSelector()
	{
		initWidget( textBox );

		textBox.addFocusHandler( new FocusHandler()
		{
			@Override
			public void onFocus( FocusEvent event )
			{
				if( enabled )
					showPopup();
			}
		} );

		textBox.addKeyUpHandler( new KeyUpHandler()
		{
			@Override
			public void onKeyUp( KeyUpEvent event )
			{
				HexaDate hexaDate = HexaDate.getDisplayFormat().getHexaDateFromDisplayString( textBox.getText() );
				if( hexaDate != null )
					datePicker.setValueString( hexaDate.getDisplayString() );
			}
		} );

		/*
		 * textBox.addClickHandler( new ClickHandler() { public void onClick(ClickEvent event) { if( popup!=null && popup.isShowing() ) hidePopup(); else
		 * showPopup(); } });
		 */
	}

	MyPopupPanel popup = null;
	JQDatepicker datePicker = null;

	private void showPopup()
	{
		if( datePicker == null )
		{
			datePicker = new JQDatepicker( true );
			datePicker.setFormat( HexaDate.getDisplayFormat().getJQDatepickerFormat() );
			datePicker.setCallback( DateSelector.this );
		}

		if( popup == null )
		{
			popup = new MyPopupPanel( true, true );
			popup.setWidget( datePicker );
		}

		popup.showRelativeTo( textBox );

		HexaDate hexaDate = HexaDate.getDisplayFormat().getHexaDateFromDisplayString( textBox.getText() );
		if( hexaDate != null )
			datePicker.setValueString( hexaDate.getDisplayString() );
	}

	private void hidePopup()
	{
		if( popup != null )
			popup.hide();
	}

	public void clear()
	{
		setDate( null );
	}

	public HexaDate getDate()
	{
		// return HexaDate.getDisplayFormat().getHexaDateFromDisplayString(
		// datePicker.getValueAsString() );
		return HexaDate.getDisplayFormat().getHexaDateFromDisplayString( textBox.getText() );
	}

	public void setDate( HexaDate hexaDate )
	{
		if( hexaDate == null )
		{
			textBox.setText( "" );
			return;
		}

		textBox.setText( hexaDate.getDisplayString() );
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

	@Override
	public void onDateSelected( String text )
	{
		HexaDate date = HexaDate.getDisplayFormat().getHexaDateFromDisplayString( text );// getDate();//new
																							// HexaDate(
																							// text
																							// );

		textBox.setText( date.getDisplayString() );

		hidePopup();
		// setDate( date, true );
	}

	private void fire( HexaDate date )
	{
		for( IValueChangeHandler<HexaDate> handler : handlers )
			handler.onValueChange( date );
	}

	public void setEnabled( boolean enabled )
	{
		this.enabled = enabled;
		textBox.setEnabled( enabled );
	}
}
