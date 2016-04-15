package fr.lteconsulting.hexa.client.other;

import fr.lteconsulting.hexa.client.application.archi.View;
import com.google.gwt.event.dom.client.HasClickHandlers;

public interface ButtonView extends View
{
	void setText( String text );

	HasClickHandlers getButton();
}
