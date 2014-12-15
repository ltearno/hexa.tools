package fr.lteconsulting.hexa.client.ui;

import com.google.gwt.core.client.GWT;

import fr.lteconsulting.hexa.client.css.HexaCss;

public interface Styles extends HexaCss
{
	public static final Styles CSS = GWT.create( Styles.class );

	String framedPanel();
	String commentPanel();
	String thinFrame();
	String pictureDecorator();
}
