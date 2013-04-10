package com.hexa.client.ui;

import java.util.ArrayList;

import com.hexa.client.common.HexaDate;
import com.hexa.client.interfaces.IValueChangeHandler;
import com.google.gwt.user.client.ui.Composite;

public class DateSelectorBak extends Composite implements JQDatepicker.Callback
{
	JQDatepicker datePicker = new JQDatepicker();
	boolean fCallbackSet = false; // denotes if a call to
									// JQDatepicker.setCallback has already been
									// made

	ArrayList<IValueChangeHandler<HexaDate>> handlers = new ArrayList<IValueChangeHandler<HexaDate>>();

	public DateSelectorBak()
	{
		initWidget( datePicker );

		datePicker.setFormat( HexaDate.getDisplayFormat().getJQDatepickerFormat() );
	}

	public void clear()
	{
		datePicker.setValueString( "" );
	}

	public HexaDate getDate()
	{
		return HexaDate.getDisplayFormat().getHexaDateFromDisplayString( datePicker.getValueAsString() );
	}

	public void setDate( HexaDate hexaDate )
	{
		if( hexaDate == null )
		{
			datePicker.setValueString( "" );
			return;
		}

		datePicker.setValueString( hexaDate.getDisplayString() );
	}

	public void setDate( HexaDate date, boolean fFireEvent )
	{
		setDate( date );

		if( fFireEvent )
			fire( date );
	}

	public void addValueChangeHandler( IValueChangeHandler<HexaDate> handler )
	{
		if( !fCallbackSet )
		{
			datePicker.setCallback( this );
			fCallbackSet = true;
		}

		handlers.add( handler );
	}

	@Override
	public void onDateSelected( String text )
	{
		HexaDate date = getDate();// new HexaDate( text );

		fire( date );
	}

	private void fire( HexaDate date )
	{
		for( IValueChangeHandler<HexaDate> handler : handlers )
			handler.onValueChange( date );
	}
}