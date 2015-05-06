package fr.lteconsulting.hexacss.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class UiBinderSample extends Composite
{
	interface MyUiBinder extends UiBinder<Widget, UiBinderSample>
	{
	}

	private static MyUiBinder uiBinder = GWT.create( MyUiBinder.class );
	
	public UiBinderSample()
	{
		initWidget( uiBinder.createAndBindUi( this ) );
	}
}
