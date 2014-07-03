package fr.lteconsulting.hexa.client.ui.containers;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import fr.lteconsulting.hexa.client.ui.widget.ImageButton;

public class AccordionHeader extends Composite implements ClickHandler
{
	Accordion.Item item;

	ImageButton image;
	Label label = null;
	Widget widget = null;

	private static ImageResource _openRsrc = null;
	private static ImageResource _closeRsrc = null;

	public static void setImages( ImageResource open, ImageResource close )
	{
		_openRsrc = open;
		_closeRsrc = close;
	}

	public AccordionHeader( Accordion.Item item, String text )
	{
		this.item = item;

		label = new Label( "" );

		HorizontalPanel p = new HorizontalPanel();

		image = new ImageButton( _openRsrc, "" );
		image.addClickHandler( this );

		p.add( image );
		p.add( label );

		updateImage();

		setText( text );

		initWidget( p );
	}

	public AccordionHeader( Accordion.Item item, Widget widget )
	{
		this.item = item;

		this.widget = widget;

		HorizontalPanel p = new HorizontalPanel();
		// FlowPanel p = new FlowPanel();

		image = new ImageButton( _openRsrc, "" );
		image.addClickHandler( this );

		p.add( image );
		p.add( widget );

		updateImage();

		p.setWidth( "100%" );

		initWidget( p );
	}

	public void setText( String text )
	{
		if( label != null )
			label.setText( text );
	}

	@Override
	public void onClick( ClickEvent event )
	{
		item.setExpanded( !item.getExpanded() );

		updateImage();
	}

	private void updateImage()
	{
		image.setResource( item.getExpanded() ? _openRsrc : _closeRsrc );
		image.setTitle( item.getExpanded() ? "Reduire" : "Ouvrir" );
	}
}
