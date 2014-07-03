package fr.lteconsulting.hexa.client.ui.widget;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import fr.lteconsulting.hexa.client.common.HexaTime;
import fr.lteconsulting.hexa.client.interfaces.IValueChangeHandler;
import fr.lteconsulting.hexa.client.tools.HTMLStream;

public class TimeSelector extends Composite implements ChangeHandler
{
	TextBox hour = new TextBox();
	TextBox minute = new TextBox();

	boolean fCallbackSet = false;

	ArrayList<IValueChangeHandler<HexaTime>> handlers = new ArrayList<IValueChangeHandler<HexaTime>>();

	public TimeSelector()
	{
		hour.setVisibleLength( 2 );
		minute.setVisibleLength( 2 );

		// for text init
		clear();

		HTMLStream stream = new HTMLStream();
		stream.addInline( hour ).text( " : " ).addInline( minute );

		initWidget( stream );
	}

	public void clear()
	{
		hour.setText( "00" );
		minute.setText( "00" );
	}

	public HexaTime getTime()
	{
		try
		{
			return new HexaTime( Integer.parseInt( hour.getText() ), Integer.parseInt( minute.getText() ), 0 );
		}
		catch( Exception e )
		{
			return new HexaTime();
		}
	}

	public void setTime( HexaTime hexaTime )
	{
		if( hexaTime == null )
		{
			clear();
			return;
		}

		hour.setText( String.valueOf( hexaTime.getHours() ) );
		minute.setText( String.valueOf( hexaTime.getMinutes() ) );
	}

	public void setTime( HexaTime time, boolean fFireEvent )
	{
		setTime( time );

		if( fFireEvent )
			fire( time );
	}

	public void addValueChangeHandler( IValueChangeHandler<HexaTime> handler )
	{
		if( !fCallbackSet )
		{
			hour.addChangeHandler( this );
			minute.addChangeHandler( this );
			fCallbackSet = true;
		}

		handlers.add( handler );
	}

	private void fire( HexaTime time )
	{
		for( IValueChangeHandler<HexaTime> handler : handlers )
			handler.onValueChange( time );
	}

	@Override
	public void onChange( ChangeEvent event )
	{
		HexaTime time = getTime();

		fire( time );
	}

	public void setEnabled( boolean enabled )
	{
		hour.setEnabled( enabled );
		minute.setEnabled( enabled );
	}
}
