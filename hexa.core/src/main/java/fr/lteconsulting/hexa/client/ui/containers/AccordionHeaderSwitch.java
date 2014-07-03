package fr.lteconsulting.hexa.client.ui.containers;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Composite;
import fr.lteconsulting.hexa.client.ui.widget.ImageButton;

public class AccordionHeaderSwitch extends Composite implements ClickHandler
{
	Accordion.Item item;

	ImageButton image;

	private static ImageResource _openRsrc = null;
	private static ImageResource _closeRsrc = null;

	public static void setImages( ImageResource open, ImageResource close )
	{
		_openRsrc = open;
		_closeRsrc = close;
	}

	public AccordionHeaderSwitch( Accordion.Item item )
	{
		this.item = item;

		image = new ImageButton( _openRsrc, "" );
		image.addClickHandler( this );

		updateImage();

		initWidget( image );
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
