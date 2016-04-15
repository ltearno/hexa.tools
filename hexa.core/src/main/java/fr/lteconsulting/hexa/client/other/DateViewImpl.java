package fr.lteconsulting.hexa.client.other;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

import fr.lteconsulting.hexa.client.common.HexaDate;
import fr.lteconsulting.hexa.client.ui.widget.ListBoxEx;

public class DateViewImpl extends Composite implements DateView
{
	ListBoxEx day = new ListBoxEx();
	ListBoxEx month = new ListBoxEx();
	ListBoxEx year = new ListBoxEx();

	public DateViewImpl()
	{
		HorizontalPanel panel = new HorizontalPanel();

		for( int i = 0; i < 31; i++ )
			day.addItem( String.valueOf( i + 1 ), i + 1 );

		for( int i = 0; i < HexaDate.MonthNames.length; i++ )
			month.addItem( HexaDate.MonthNames[i], i );

		int nowY = new HexaDate().getYear();
		int lower = 100;
		int higher = 3;
		for( int i = nowY + higher; i >= nowY - lower; i-- )
			year.addItem( String.valueOf( 1900 + i ), i );
		year.setSelected( nowY );

		panel.add( day );
		panel.add( month );
		panel.add( year );

		initWidget( panel );
	}

	@Override
	public void setData( HexaDate date )
	{
		day.setSelected( date.getDate() );
		month.setSelected( date.getMonth() );
		year.setSelected( date.getYear() );
	}

	@Override
	public HexaDate getDate()
	{
		return new HexaDate( year.getSelected(), month.getSelected(), day.getSelected() );
	}

	@Override
	public HasSelectionHandlers<HexaDate> getChangeHandlerMng()
	{
		return hasChangeHandlers;
	}

	ArrayList<SelectionHandler<HexaDate>> handlers = null;

	HasSelectionHandlers<HexaDate> hasChangeHandlers = new HasSelectionHandlers<HexaDate>()
	{
		@Override
		public void addSelectionhandler( SelectionHandler<HexaDate> handler )
		{
			if( handlers == null )
			{
				handlers = new ArrayList<SelectionHandler<HexaDate>>();

				day.setCallback( lbCb, day );
				month.setCallback( lbCb, month );
				year.setCallback( lbCb, year );
			}

			handlers.add( handler );
		}
	};

	ListBoxEx.Callback lbCb = new ListBoxEx.Callback()
	{
		public void onListBoxExChange( ListBoxEx listBoxEx, Object cookie )
		{
			if( handlers == null )
				return;

			HexaDate date = getDate();

			for( SelectionHandler<HexaDate> handler : handlers )
				handler.onSelected( date );
		}
	};
}
