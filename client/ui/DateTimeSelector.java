package com.hexa.client.ui;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Composite;
import com.hexa.client.common.HexaDate;
import com.hexa.client.common.HexaDateTime;
import com.hexa.client.common.HexaTime;
import com.hexa.client.interfaces.IValueChangeHandler;
import com.hexa.client.tools.HTMLStream;

public class DateTimeSelector extends Composite
{
	DateSelector date = new DateSelector();
	TimeSelector time = new TimeSelector();
	
	boolean fCallbackSet = false;
	
	ArrayList<IValueChangeHandler<HexaDateTime>> handlers = new ArrayList<IValueChangeHandler<HexaDateTime>>();
	
	public DateTimeSelector()
	{
		HTMLStream stream = new HTMLStream();
		stream.addInline( date ).text( " at " ).addInline( time );
		
		initWidget( stream );
	}
	
	public void clear()
	{
		date.clear();
		time.clear();
	}
	
	public HexaDateTime getDateTime()
	{
		return new HexaDateTime( date.getDate(), time.getTime() );
	}
	
	public void setDateTime( HexaDateTime hexaDateTime )
	{
		if( hexaDateTime == null )
		{
			clear();
			return;
		}
		
		date.setDate( hexaDateTime.getHexaDate() );
		time.setTime( hexaDateTime.getHexaTime() );
	}
	
	public void setDateTime( HexaDateTime dateTime, boolean fFireEvent )
	{
		setDateTime( dateTime );
		
		if( fFireEvent )
			fire( dateTime );
	}
	
	public void addValueChangeHandler( IValueChangeHandler<HexaDateTime> handler )
	{
		if( ! fCallbackSet )
		{
			date.addValueChangeHandler( new IValueChangeHandler<HexaDate>() {
				public void onValueChange(HexaDate value)
				{
					changed();
				}
			} );
			time.addValueChangeHandler( new IValueChangeHandler<HexaTime>() {
				public void onValueChange(HexaTime value)
				{
					changed();
				}
			} );
			fCallbackSet = true;
		}
		
		handlers.add( handler );
	}
	
	private void fire( HexaDateTime dateTime )
	{
		for( IValueChangeHandler<HexaDateTime> handler : handlers )
			handler.onValueChange( dateTime );
	}

	void changed()
	{
		HexaDateTime time = getDateTime();
		
		fire( time );
	}
}
