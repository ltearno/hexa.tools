package fr.lteconsulting.hexa.client.ui.search.uivalues;

import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Composite;

import fr.lteconsulting.hexa.client.common.HexaDateTime;
import fr.lteconsulting.hexa.client.ui.search.ValueUI;
import fr.lteconsulting.hexa.client.ui.search.ValueUIFactory;
import fr.lteconsulting.hexa.client.ui.widget.DateTimeSelector;

public class UIValueDateTime extends Composite implements ValueUI
{
	DateTimeSelector ds = new DateTimeSelector();

	private UIValueDateTime( boolean fReadOnly )
	{
		ds.setEnabled( !fReadOnly );

		initWidget( ds );
	}

	@Override
	public void setValue( JSONValue json )
	{
		ds.setDateTime( new HexaDateTime( json.isString().stringValue() ) );
	}

	@Override
	public JSONValue getValue()
	{
		return new JSONString( ds.getDateTime().getString() );
	}

	private static ValueUIFactory factory = new ValueUIFactory()
	{
		@Override
		public ValueUI create( boolean fReadOnly )
		{
			return new UIValueDateTime( fReadOnly );
		}
	};

	public static ValueUIFactory factory()
	{
		return factory;
	}
}