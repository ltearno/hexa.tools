package fr.lteconsulting.hexa.client.ui.search.uivalues;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;

import fr.lteconsulting.hexa.client.ui.search.ValueUI;
import fr.lteconsulting.hexa.client.ui.search.ValueUIFactory;

public class UIValueInteger extends Composite implements ValueUI
{
	TextBox tb = new TextBox();

	private UIValueInteger( boolean fReadOnly )
	{
		tb.setEnabled( !fReadOnly );

		initWidget( tb );
	}

	@Override
	public void setValue( JSONValue json )
	{
		tb.setText( String.valueOf( ((int) json.isNumber().doubleValue()) ) );
	}

	@Override
	public JSONValue getValue()
	{
		try
		{
			return new JSONNumber( Integer.parseInt( tb.getText() ) );
		}
		catch( Exception e )
		{
			return new JSONNumber( 0 );
		}
	}

	private static ValueUIFactory factory = new ValueUIFactory()
	{
		@Override
		public ValueUI create( boolean fReadOnly )
		{
			return new UIValueInteger( fReadOnly );
		}
	};

	public static ValueUIFactory factory()
	{
		return factory;
	}
}
