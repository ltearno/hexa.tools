package fr.lteconsulting.angular2gwt.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

import fr.lteconsulting.angular2gwt.client.interop.ng.core.ElementRef;

public class WidgetAdapterPanel extends SimplePanel
{
	public WidgetAdapterPanel( ElementRef elementRef )
	{
		super( (Element) (Object) elementRef.nativeElement() );

		onAttach();
		RootPanel.detachOnWindowClose( this );
	}
	
	public void remove()
	{
		RootPanel.detachNow( this );
	}
}