package com.hexa.client.ui.Ribbon;

public interface RibbonView
{
	void selectButton( Object obj );

	void setButtonText( Object obj, String text );

	public void setTabText( String id, String text );
}