package fr.lteconsulting.hexa.client.ui.dialog;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.HasCloseHandlers;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.ui.dialog.DialogBoxBuilder.DialogBox;
import fr.lteconsulting.hexa.client.ui.resources.image.ImageResources;

public class DialogBoxForLayoutWidget implements DialogBox, HasCloseHandlers<DialogBox>
{
	HandlerManager handlerMng = new HandlerManager( this );
	
	boolean isAutoHide;
	
	boolean isDisplayed = false;
	LayoutPanel dock;
	
	Image closeWidget;
	
	public DialogBoxForLayoutWidget( String title, IsWidget content )
	{
		int m = 50;
		ImageResource closeImage = ImageResources.INSTANCE.close();
		int titleSize = Math.max( closeImage.getHeight(), closeImage.getWidth() ) + 5;
		
		dock = new LayoutPanel();
		
		Glass glass = new Glass();
		dock.add( glass );
		
		DivWidget backGround = new DivWidget();
		backGround.addStyleName( ResizablePanel.CSS.bkgnd() );
		dock.add( backGround );
		dock.setWidgetLeftRight( backGround, m, Unit.PX, m, Unit.PX );
		dock.setWidgetTopBottom( backGround, m, Unit.PX, m, Unit.PX );
		
		DivWidget titleWidget = new DivWidget();
		titleWidget.addStyleName( ResizablePanel.CSS.title() );
		titleWidget.getElement().setInnerText( title );
		dock.add( titleWidget );
		dock.setWidgetLeftRight( titleWidget, m, Unit.PX, m + titleSize, Unit.PX );
		dock.setWidgetTopHeight( titleWidget, m, Unit.PX, titleSize, Unit.PX );
		
		closeWidget = new Image( closeImage );
		dock.add( closeWidget );
		dock.setWidgetRightWidth( closeWidget, m, Unit.PX, titleSize, Unit.PX );
		dock.setWidgetTopHeight( closeWidget, m, Unit.PX, titleSize, Unit.PX );
		
		SimpleLayoutPanel contentPanel = new SimpleLayoutPanel();
		contentPanel.setWidget( content.asWidget() );
		contentPanel.addStyleName( ResizablePanel.CSS.content() );
		dock.add( contentPanel );
		dock.setWidgetLeftRight( contentPanel, m, Unit.PX, m, Unit.PX );
		dock.setWidgetTopBottom( contentPanel, m + titleSize, Unit.PX, m, Unit.PX );
		
		closeWidget.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				if( isAutoHide )
					hide();
			}
		} );
	}
	
	@Override
	public void show()
	{
		show( true );
	}

	@Override
	public void show( boolean isAutoHide )
	{
		this.isAutoHide = isAutoHide;
		
		if( isDisplayed )
			return;
		isDisplayed = true;
		
		RootLayoutPanel.get().add( dock );
	}
	
	@Override
	public void hide()
	{
		if( ! isDisplayed )
			return;
		isDisplayed = false;
		
		RootLayoutPanel.get().remove( dock );
		
		CloseEvent.fire( this, null );
	}

	@Override
	public HandlerRegistration addCloseHandler( CloseHandler<DialogBox> handler )
	{
		return handlerMng.addHandler( CloseEvent.getType(), handler );
	}

	@Override
	public void fireEvent( GwtEvent<?> event )
	{
		handlerMng.fireEvent( event );
	}
}

class DivWidget extends Widget
{
	public DivWidget()
	{
		setElement( Document.get().createDivElement() );
	}
}
