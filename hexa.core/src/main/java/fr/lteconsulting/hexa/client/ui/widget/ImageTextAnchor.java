package fr.lteconsulting.hexa.client.ui.widget;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.TextDecoration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.DOM;

public class ImageTextAnchor extends ImageTextButton
{
	@UiConstructor
	public ImageTextAnchor( ImageResource resource, String title )
	{
		super(resource, title);
	}
	
	@Override
	protected Element createButtonElement()
	{
		Element anchor = DOM.createAnchor();
		anchor.getStyle().setTextDecoration( TextDecoration.NONE );
		return anchor;
	}
	
	public void setHref(String href)
	{
		button.<AnchorElement>cast().setHref( href );
	}
	
	public void setDownloadName(String name)
	{
		button.setAttribute( "download", name );
	}
}
