package fr.lteconsulting.hexa.client.ui.widget;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import fr.lteconsulting.hexa.client.interfaces.AValueControl;
import fr.lteconsulting.hexa.client.ui.editable.EditableLabel;
import fr.lteconsulting.hexa.client.ui.editable.EditableWidget;
import fr.lteconsulting.hexa.client.ui.resources.image.ImageResources;

public class SpinControl extends AValueControl<Integer> implements ClickHandler
{
	int value = 0;

	HTMLPanel w;

	Image up;
	Image down;
	//Label valueLabel;
	EditableLabel<Void> valueLabel;

	@UiConstructor
	public SpinControl( ImageResource upRsrc, ImageResource downRsrc )
	{
		String imgsPlace = DOM.createUniqueId();
		String labelPlace = DOM.createUniqueId();

		up = new Image( upRsrc );
		down = new Image( downRsrc );
		valueLabel = new EditableLabel<Void>( ImageResources.INSTANCE.loading16x16(), new EditableLabel.Callback<Void>()
		{
			@Override
			public void onWantChange( String text, EditableWidget<Label, Void> widget, Void cookie )
			{
				try
				{
					value = Integer.parseInt( text );
					valueLabel.setText( String.valueOf( value ) );
					
					signalCallbacks();
				}
				catch( Exception e )
				{
				}
			}
		}, null );
		valueLabel.setText( String.valueOf( value ) );

		up.getElement().getStyle().setDisplay( Display.BLOCK );

		w = new HTMLPanel( "<table><tr>" + "<td id='" + imgsPlace + "'></td>" + "<td id='" + labelPlace + "'></td>" + "</tr></table>" );

		w.add( up, imgsPlace );
		w.add( down, imgsPlace );
		w.add( valueLabel, labelPlace );

		initWidget( w );

		up.addClickHandler( this );
		down.addClickHandler( this );
	}

	@Override
	public void onClick( ClickEvent event )
	{
		int inc = 0;
		if( event.getSource() == up )
			inc = 1;
		else if( event.getSource() == down )
			inc = -1;

		value += inc;

		valueLabel.setText( String.valueOf( value ) );

		signalCallbacks();
	}

	@Override
	public Integer getValue()
	{
		return value;
	}

	@Override
	public void setValue( Integer value )
	{
		this.value = value;

		valueLabel.setText( String.valueOf( value ) );
	}
}
