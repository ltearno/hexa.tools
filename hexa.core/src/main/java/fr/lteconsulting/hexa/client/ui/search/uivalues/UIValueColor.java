package fr.lteconsulting.hexa.client.ui.search.uivalues;

import java.util.ArrayList;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;

import fr.lteconsulting.hexa.client.common.Pair;
import fr.lteconsulting.hexa.client.ui.colorpicker.ColorPicker;
import fr.lteconsulting.hexa.client.ui.dialog.MyPopupPanel;
import fr.lteconsulting.hexa.client.ui.search.ICriteriaMng;
import fr.lteconsulting.hexa.client.ui.search.StandardCriteriaMng;
import fr.lteconsulting.hexa.client.ui.search.StandardNoOpCriteriaMng;
import fr.lteconsulting.hexa.client.ui.search.ValueUI;
import fr.lteconsulting.hexa.client.ui.search.ValueUIFactory;

public class UIValueColor extends Composite implements ValueUI
{
	HTML html = new HTML( "Choose a color" );

	private UIValueColor( boolean fReadOnly )
	{
		html.getElement().getStyle().setWidth( 100, Unit.PX );
		html.getElement().getStyle().setHeight( 20, Unit.PX );
		html.getElement().getStyle().setMargin( 3, Unit.PX );
		
		html.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				final MyPopupPanel popup = new MyPopupPanel( true );
				ColorPicker colorPicker = new ColorPicker();
				popup.setWidget( colorPicker );
				colorPicker.addSelectionHandler( new SelectionHandler<String>()
				{
					@Override
					public void onSelection( SelectionEvent<String> event )
					{
						setColor( event.getSelectedItem() );
						popup.hide();
					}
				} );
				
				popup.showRelativeTo( html );
			}
		} );
		
		initWidget( html );
	}

	@Override
	public void setValue( JSONValue json )
	{
		if( json == null )
			setColor( null );
		else
			setColor( json.isString().stringValue() );
	}
	
	private void setColor( String color )
	{
		if( color == null )
			html.getElement().getStyle().clearBackgroundColor();
		else
			html.getElement().getStyle().setBackgroundColor( color );
		
		html.getElement().setAttribute( "value", color );
		
		GWT.log( "setValue : " + color );
	}

	@Override
	public JSONValue getValue()
	{
		String color = html.getElement().getAttribute( "value" );
		if( color == null )
			color = "";
		
		GWT.log( "getValue : " + color );
		
		return new JSONString( color );
	}

	private static ValueUIFactory factory = new ValueUIFactory()
	{
		@Override
		public ValueUI create( boolean fReadOnly )
		{
			return new UIValueColor( fReadOnly );
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
				UIValueColor ui = new UIValueColor( fReadOnly );
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
