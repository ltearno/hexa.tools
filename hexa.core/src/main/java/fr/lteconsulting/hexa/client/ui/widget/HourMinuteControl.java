package fr.lteconsulting.hexa.client.ui.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import fr.lteconsulting.hexa.client.common.HexaTime;
import fr.lteconsulting.hexa.client.common.text.NumberFormat;

public class HourMinuteControl extends Composite
{
	public interface Callback
	{
		void onValueChanged( HexaTime newValue );
	}

	private static HourMinuteControlUiBinder uiBinder = GWT.create( HourMinuteControlUiBinder.class );

	interface HourMinuteControlUiBinder extends UiBinder<Widget, HourMinuteControl>
	{
	}

	Callback callback;
	HandlerRegistration hourHandlerRegistration;
	HandlerRegistration minuteHandlerRegistration;

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

	public void setCallback( Callback callback )
	{
		this.callback = callback;

		// unsubscribe previous registration, if any
		if( hourHandlerRegistration != null )
			hourHandlerRegistration.removeHandler();
		// subscribe to changes in the 'hour' control
		hourHandlerRegistration = hour.addChangeHandler( changeHandler );

		// unsubscribe previous registration, if any
		if( minuteHandlerRegistration != null )
			minuteHandlerRegistration.removeHandler();
		// subscribe to changes in the 'hour' control
		minuteHandlerRegistration = minute.addChangeHandler( changeHandler );
	}

	private ChangeHandler changeHandler = new ChangeHandler()
	{
		@Override
		public void onChange( ChangeEvent event )
		{
			if( callback != null )
				callback.onValueChanged( getTime() );
		}
	};
}
