package com.hexa.client.ui;

import com.hexa.client.common.HexaTime;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class HourMinuteControl extends Composite
{

	private static HourMinuteControlUiBinder uiBinder = GWT.create( HourMinuteControlUiBinder.class );

	interface HourMinuteControlUiBinder extends UiBinder<Widget, HourMinuteControl>
	{
	}

	@UiField
	ListBox hour;
	@UiField
	ListBox minute;

	public HourMinuteControl()
	{
		initWidget( uiBinder.createAndBindUi( this ) );
		NumberFormat fmt = NumberFormat.getFormat( "00" );
		for( int h = 0; h < 25; h++ )
			hour.addItem( fmt.format( h ), String.valueOf( h ) );
		for( int m = 0; m < 60; m += 15 )
			minute.addItem( fmt.format( m ), String.valueOf( m ) );
	}

	public void setTime( HexaTime time )
	{
		int h = time.getHours();
		int m = time.getMinutes();

		hour.setSelectedIndex( h );
		minute.setSelectedIndex( m / 15 );
	}

	public HexaTime getTime()
	{
		return new HexaTime( Integer.parseInt( hour.getValue( hour.getSelectedIndex() ) ), Integer.parseInt( minute.getValue( minute.getSelectedIndex() ) ), 0 );
	}
}
