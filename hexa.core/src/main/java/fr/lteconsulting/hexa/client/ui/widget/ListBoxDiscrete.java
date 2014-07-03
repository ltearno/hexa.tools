package fr.lteconsulting.hexa.client.ui.widget;

import java.util.HashMap;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.UIObject;
import fr.lteconsulting.hexa.client.ui.ListTable;
import fr.lteconsulting.hexa.client.ui.dialog.MyPopupPanel;

public class ListBoxDiscrete<T> extends Composite implements ClickHandler
{
	ImageResource up;
	ImageResource down;

	Label label = new Label();
	ImageButton img;

	HashMap<T, String> texts = new HashMap<T, String>();

	ListTable<T> listTable = new ListTable<T>();

	MyPopupPanel popup = null;

	boolean fEnabled = true;

	public ListBoxDiscrete( ImageResource up, ImageResource down )
	{
		this.up = up;
		this.down = down;

		img = new ImageButton( down, "Select..." );

		HorizontalPanel panel = new HorizontalPanel();
		panel.add( label );
		panel.add( img );
		initWidget( panel );

		setStylePrimaryName( "SelectButton" );

		img.addClickHandler( this );

		label.addClickHandler( this );

		listTable.addChangeHandler( new ChangeHandler()
		{
			@Override
			public void onChange( ChangeEvent event )
			{
				if( popup != null )
					popup.hide();

				label.setText( texts.get( listTable.getSelected() ) );
			}
		} );
	}

	public void setEnabled( boolean fEnabled )
	{
		this.fEnabled = fEnabled;
	}

	public void addItem( String text, T object )
	{
		texts.put( object, text );
		listTable.addItem( text, object );
	}

	public void addChangeHandler( ChangeHandler handler )
	{
		listTable.addChangeHandler( handler );
	}

	public T getSelected()
	{
		return listTable.getSelected();
	}

	public void setSelected( T selected )
	{
		label.setText( texts.get( selected ) );
		listTable.setSelected( selected );
	}

	@Override
	public void onClick( ClickEvent event )
	{
		if( !fEnabled )
			return;

		if( popup == null )
		{
			popup = new MyPopupPanel( true );
			popup.setWidget( listTable );

			popup.addCloseHandler( new CloseHandler<PopupPanel>()
			{
				@Override
				public void onClose( CloseEvent<PopupPanel> event )
				{
					img.setResource( down );
				}
			} );
		}

		img.setResource( up );
		popup.showRelativeTo( (UIObject) event.getSource() );
	}
}