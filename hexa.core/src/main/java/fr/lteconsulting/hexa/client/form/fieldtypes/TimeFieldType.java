package fr.lteconsulting.hexa.client.form.fieldtypes;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.common.HexaTime;
import fr.lteconsulting.hexa.client.form.marshalls.Marshalls;
import fr.lteconsulting.hexa.client.ui.widget.ListBoxEx;

public class TimeFieldType extends FieldTypeBase
{
	private static NumberFormat format = NumberFormat.getFormat( "00" );
	private static TimeFieldType INST = null;

	public static TimeFieldType get()
	{
		if( INST == null )
			INST = new TimeFieldType();
		return INST;
	}

	@Override
	public Widget getWidget()
	{
		return new NestedWidget();
	}

	@Override
	protected void installRealHandler( Widget widget )
	{
		((NestedWidget) widget).installRealHandler();
	}

	@Override
	public void setValue( Widget widget, JSONValue value )
	{
		((NestedWidget) widget).setTime( Marshalls.time.get( value ) );
	}

	@Override
	public JSONValue getValue( Widget widget )
	{
		return Marshalls.time.get( ((NestedWidget) widget).getTime() );
	}

	class NestedWidget extends Composite
	{
		ListBoxEx hours = new ListBoxEx();
		ListBoxEx minutes = new ListBoxEx();

		public NestedWidget()
		{
			HorizontalPanel panel = new HorizontalPanel();

			for( int i = 9; i <= 20; i++ )
				hours.addItem( format.format( i ), i );
			for( int i = 0; i < 60; i += 15 )
				minutes.addItem( format.format( i ), i );

			panel.add( hours );
			panel.add( new Label( ":" ) );
			panel.add( minutes );

			initWidget( panel );
		}

		public void setTime( HexaTime time )
		{
			hours.setSelected( time.getHours() );
			minutes.setSelected( time.getMinutes() - time.getMinutes() % 15 );
		}

		public HexaTime getTime()
		{
			return new HexaTime( hours.getSelected(), minutes.getSelected(), 0 );
		}

		void installRealHandler()
		{
			hours.setCallback( onChangeCb, null );
			minutes.setCallback( onChangeCb, null );
		}

		ListBoxEx.Callback onChangeCb = new ListBoxEx.Callback()
		{
			public void onListBoxExChange( ListBoxEx listBoxEx, Object cookie )
			{
				signalChange( NestedWidget.this );
			}
		};
	}
}
