package fr.lteconsulting.hexa.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

public class IFrame extends Widget
{
	IFrameElement iFrame;

	public IFrame( String url )
	{
		Element iframe = DOM.createIFrame();
		iFrame = IFrameElement.as( iframe );

		setElement( iframe );

		setSrc( url );
	}

	public IFrame()
	{
		Element iframe = DOM.createIFrame();
		iFrame = IFrameElement.as( iframe );

		setElement( iframe );
	}

	public void setContent( String html )
	{
		// JQuery.get().jqHtml( (Element) iFrame.cast(), html );
		iFrame.setInnerHTML( html );
	}

	public void setSrc( String url )
	{
		iFrame.setSrc( url );
	}
}
