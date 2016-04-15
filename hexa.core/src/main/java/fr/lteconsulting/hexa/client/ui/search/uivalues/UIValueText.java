package fr.lteconsulting.hexa.client.ui.search.uivalues;

import java.util.ArrayList;

import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;

import fr.lteconsulting.hexa.client.common.Pair;
import fr.lteconsulting.hexa.client.ui.search.ICriteriaMng;
import fr.lteconsulting.hexa.client.ui.search.StandardCriteriaMng;
import fr.lteconsulting.hexa.client.ui.search.StandardNoOpCriteriaMng;
import fr.lteconsulting.hexa.client.ui.search.ValueUI;
import fr.lteconsulting.hexa.client.ui.search.ValueUIFactory;

public class UIValueText extends Composite implements ValueUI
{
	TextBox tb = new TextBox();

	private UIValueText( boolean fReadOnly )
	{
		tb.setEnabled( !fReadOnly );
		initWidget( tb );
	}

	@Override
	public void setValue( JSONValue json )
	{
		if( json == null )
			tb.setText( "" );
		else
			tb.setText( json.isString().stringValue() );
	}

	@Override
	public JSONValue getValue()
	{
		return new JSONString( tb.getText() );
	}

	private static ValueUIFactory factory = new ValueUIFactory()
	{
		@Override
		public ValueUI create( boolean fReadOnly )
		{
			return new UIValueText( fReadOnly );
		}
	};

	public static ICriteriaMng createCriteriaMng( String displayName, String fieldName )
	{
		return new StandardCriteriaMng( displayName, fieldName, getComparators(), factory );
	}

	public static ICriteriaMng createGUIDCriteriaMng( String displayName, String fieldName )
	{
		return new StandardNoOpCriteriaMng( displayName, fieldName )
		{
			@Override
			public ValueUI factory( boolean fReadOnly )
			{
				UIValueText ui = new UIValueText( fReadOnly );
				ui.tb.setVisibleLength( 40 );
				return ui;
			}
		};
	}

	private static ArrayList<Pair<String, String>> _comparators = null;

	private static ArrayList<Pair<String, String>> getComparators()
	{
		if( _comparators == null )
		{
			_comparators = new ArrayList<Pair<String, String>>();
			_comparators.add( new Pair<String, String>( "is like", "LIKE" ) );
			_comparators.add( new Pair<String, String>( "is not like", "NOT LIKE" ) );
		}

		return _comparators;
	}
}
