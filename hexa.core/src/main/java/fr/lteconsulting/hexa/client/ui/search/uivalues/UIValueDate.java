package fr.lteconsulting.hexa.client.ui.search.uivalues;

import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Composite;

import fr.lteconsulting.hexa.client.common.HexaDate;
import fr.lteconsulting.hexa.client.ui.search.ValueUI;
import fr.lteconsulting.hexa.client.ui.search.ValueUIFactory;
import fr.lteconsulting.hexa.client.ui.widget.DateSelector;

public class UIValueDate extends Composite implements ValueUI
{
	DateSelector ds = new DateSelector();

	private UIValueDate( boolean fReadOnly )
	{
		ds.setEnabled( !fReadOnly );

		initWidget( ds );
	}

	@Override
	public void setValue( JSONValue json )
	{
		ds.setDate( new HexaDate( json.isString().stringValue() ) );
	}

	@Override
	public JSONValue getValue()
	{
		return new JSONString( ds.getDate().getString() );
	}

	private static ValueUIFactory factory = new ValueUIFactory()
	{
		@Override
		public ValueUI create( boolean fReadOnly )
		{
			return new UIValueDate( fReadOnly );
		}
	};

	public static ValueUIFactory factory()
	{
		return factory;
	}
}