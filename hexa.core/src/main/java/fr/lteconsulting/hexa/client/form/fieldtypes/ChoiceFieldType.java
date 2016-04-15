package fr.lteconsulting.hexa.client.form.fieldtypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.tools.HTMLStream;

public class ChoiceFieldType extends FieldTypeBase
{
	static class Pair
	{
		int id;
		String text;

		Pair( int id, String text )
		{
			this.id = id;
			this.text = text;
		}
	}

	ArrayList<Pair> items = new ArrayList<Pair>();
	boolean fSelectFirstByDefault;

	public ChoiceFieldType()
	{
		this( true );
	}

	public ChoiceFieldType( boolean fSelectFirstByDefault )
	{
		this.fSelectFirstByDefault = fSelectFirstByDefault;
	}

	public void addItem( int id, String text )
	{
		items.add( new Pair( id, text ) );
	}

	@Override
	protected void installRealHandler( Widget widget )
	{
		((NestWidget) widget).installRealHandler();
	}

	class NestWidget extends Composite
	{
		String groupName = "RBGRP_" + DOM.createUniqueId();
		HTMLStream stream = new HTMLStream();
		HashMap<Integer, RadioButton> radios = new HashMap<Integer, RadioButton>();

		NestWidget()
		{
			boolean fFirst = true;
			for( Pair item : items )
			{
				RadioButton radio = new RadioButton( groupName, item.text );
				stream.addDown( radio );

				radios.put( item.id, radio );

				if( fFirst && fSelectFirstByDefault )
				{
					fFirst = false;
					radio.setValue( true );
				}
			}

			initWidget( stream );
		}

		void installRealHandler()
		{
			ValueChangeHandler<Boolean> changeHandler = new ValueChangeHandler<Boolean>()
			{
				@Override
				public void onValueChange( ValueChangeEvent<Boolean> event )
				{
					if( event.getValue() )
						signalChange( NestWidget.this );
				}
			};

			for( RadioButton radio : radios.values() )
				radio.addValueChangeHandler( changeHandler );
		}

		void setSelected( int id )
		{
			RadioButton radio = radios.get( id );
			if( radio == null )
				return;
			radio.setValue( true );
		}

		int getSelected()
		{
			for( Entry<Integer, RadioButton> e : radios.entrySet() )
			{
				if( e.getValue().getValue() )
					return e.getKey();
			}

			return -1;
		}
	}

	@Override
	public Widget getWidget()
	{
		return new NestWidget();
	}

	@Override
	public void setValue( Widget widget, JSONValue value )
	{
		((NestWidget) widget).setSelected( (int) value.isNumber().doubleValue() );
	}

	@Override
	public JSONValue getValue( Widget widget )
	{
		int selected = ((NestWidget) widget).getSelected();
		if( selected == -1 )
			return null;
		return new JSONNumber( selected );
	}
}
