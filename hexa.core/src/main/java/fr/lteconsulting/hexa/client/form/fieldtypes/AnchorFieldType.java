package fr.lteconsulting.hexa.client.form.fieldtypes;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.form.FormManager;
import fr.lteconsulting.hexa.client.form.marshalls.CallbackIntegerMarshall;
import fr.lteconsulting.hexa.client.interfaces.IAsyncCallback;

public class AnchorFieldType implements FieldType
{
	public static FormManager.Marshall<IAsyncCallback<Integer>> marshall = new CallbackIntegerMarshall();

	String title = "";

	class Wrap extends Composite
	{
		Anchor view = null;

		JSONValue callback = null;

		Wrap()
		{
			view = new Anchor( title );

			view.addClickHandler( new ClickHandler()
			{
				public void onClick( ClickEvent event )
				{
					if( callback == null )
						return;

					marshall.get( callback ).onSuccess( 1 );
				}
			} );

			initWidget( view );
		}

		void setCallback( JSONValue callback )
		{
			this.callback = callback;
		}
	}

	public AnchorFieldType( String title )
	{
		this.title = title;
	}

	@Override
	public Widget getWidget()
	{
		return new Wrap();
	}

	@Override
	public void setValue( Widget widget, JSONValue value )
	{
		((Wrap) widget).setCallback( value );
	}

	@Override
	public JSONValue getValue( Widget widget )
	{
		return null;
	}

	@Override
	public FieldChangeHandlerManager getHandlerManager( Widget widget )
	{
		return null;
	}
}
