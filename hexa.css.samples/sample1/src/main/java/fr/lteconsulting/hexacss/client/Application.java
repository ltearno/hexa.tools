package fr.lteconsulting.hexacss.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class Application implements EntryPoint
{
	@Override
	public void onModuleLoad()
	{
		MyCss css = GWT.create( MyCss.class );
		
		Label label = new Label("This is a label");
		label.addStyleName( css.panel() );
		
		RootPanel.get().add( label );
	}
}
