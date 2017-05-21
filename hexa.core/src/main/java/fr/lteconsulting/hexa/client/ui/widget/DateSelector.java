package fr.lteconsulting.hexa.client.ui.widget;

import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import fr.lteconsulting.hexa.client.common.HexaDate;
import fr.lteconsulting.hexa.client.interfaces.IValueChangeHandler;
import fr.lteconsulting.hexa.client.ui.dialog.MyPopupPanel;

import java.util.ArrayList;

public class DateSelector extends Composite
{
	boolean enabled = true;
	TextBox textBox = new TextBox();
	MyPopupPanel popup = null;
	DatePicker datePicker = null;

	ArrayList<IValueChangeHandler<HexaDate>> handlers = new ArrayList<IValueChangeHandler<HexaDate>>();

	public DateSelector()
	{
		initWidget( textBox );

		textBox.addFocusHandler( new FocusHandler()
		{
			@Override
			public void onFocus( FocusEvent event )
			{
				if( !enabled )
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
			datePicker.setCurrentMonth( hexaDate );
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
			datePicker = new DatePicker();
			datePicker.setAvailablePeriod( "a" );
			datePicker.addValueChangeHandler( new ValueChangeHandler<HexaDate>()
			{
				@Override public void onValueChange( ValueChangeEvent<HexaDate> valueChangeEvent )
				{
					hidePopup();

					setDate( valueChangeEvent.getValue(), true );
				}
			} );
		}

		if( popup == null )
		{
			popup = new MyPopupPanel( true, true );
			popup.setWidget( datePicker );
		}

		datePicker.setCurrentMonth( getDate() );

		popup.showRelativeTo( textBox );
	}

	private void hidePopup()
	{
		if( popup != null )
			popup.hide();
	}

	private void fire( HexaDate date )
	{
		for( IValueChangeHandler<HexaDate> handler : handlers )
			handler.onValueChange( date );
	}
}
