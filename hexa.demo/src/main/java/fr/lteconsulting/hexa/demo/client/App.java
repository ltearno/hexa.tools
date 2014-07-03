package fr.lteconsulting.hexa.demo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import fr.lteconsulting.hexa.client.ui.UiBuilder;

public class App implements EntryPoint
{
	@Override
	public void onModuleLoad()
	{
		RootPanel.get().add( UiBuilder.vert( new Label("Cac"), new Label("Cac") ) );
	}
}
