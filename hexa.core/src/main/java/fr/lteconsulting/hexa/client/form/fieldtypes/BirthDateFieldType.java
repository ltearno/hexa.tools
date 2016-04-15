package fr.lteconsulting.hexa.client.form.fieldtypes;

import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.common.HexaDate;
import fr.lteconsulting.hexa.client.form.marshalls.Marshalls;
import fr.lteconsulting.hexa.client.other.DateViewImpl;
import fr.lteconsulting.hexa.client.other.SelectionHandler;

public class BirthDateFieldType extends FieldTypeBase
{
	class DateWidget extends Composite
	{
		DateViewImpl impl = new DateViewImpl();

		DateWidget()
		{
			initWidget( impl.asWidget() );
		}

		void installRealHandler()
		{
			impl.getChangeHandlerMng().addSelectionhandler( new SelectionHandler<HexaDate>()
			{
				public void onSelected( HexaDate selected )
				{
					signalChange( DateWidget.this );
				}
			} );
		}
	}

	public Widget getWidget()
	{

		return new DateWidget();
	}

	@Override
	public void setValue( Widget widget, JSONValue value )
	{
		((DateWidget) widget).impl.setData( Marshalls.date.get( value ) );
	}

	@Override
	public JSONValue getValue( Widget widget )
	{
		return Marshalls.date.get( ((DateWidget) widget).impl.getDate() );
	}

	@Override
	protected void installRealHandler( Widget widget )
	{
		((DateWidget) widget).installRealHandler();
	}
}
