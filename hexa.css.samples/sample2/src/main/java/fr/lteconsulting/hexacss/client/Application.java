package fr.lteconsulting.hexacss.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;

import fr.lteconsulting.hexa.client.css.ThemeManager;

public class Application implements EntryPoint
{
	@Override
	public void onModuleLoad()
	{
		MyCss css = GWT.create( MyCss.class );
		
		final ListBox listBox = new ListBox();
		listBox.addItem( "theme-1" );
		listBox.addItem( "theme-2" );
		listBox.addItem( "theme-3" );
		listBox.addChangeHandler( new ChangeHandler()
		{
			@Override
			public void onChange( ChangeEvent event )
			{
				ThemeManager.get().setTheme( listBox.getSelectedValue() );
			}
		} );
		
		Label label = new Label("This is the application, you can change theme with the list box above");
		label.addStyleName( css.panel() );
		
		RootPanel.get().add( listBox );
		RootPanel.get().add( label );
	}
}
