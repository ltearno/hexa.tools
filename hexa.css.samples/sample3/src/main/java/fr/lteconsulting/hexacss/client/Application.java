package fr.lteconsulting.hexacss.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;

import fr.lteconsulting.hexa.client.css.ThemeManager;

public class Application implements EntryPoint
{
	@Override
	public void onModuleLoad()
	{
		ThemeManager.get().setTheme( "theme-1" );
		
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
		
		FlexTable table = new FlexTable();
		table.setStyleName( css.table() );
		table.setHTML( 0, 0, "<b>Title 1<b>" );
		table.setHTML( 0, 1, "<b>Title 2<b>" );
		table.setHTML( 1, 0, "<b>Lorem<b>" );
		table.setHTML( 1, 1, "<b>Ipsus<b>" );
		table.setHTML( 2, 0, "<b>Laprem<b>" );
		table.setHTML( 2, 1, "<b>Esbien<b>" );
		table.setHTML( 3, 0, "<b>Tolla<b>" );
		table.setHTML( 3, 1, "<b>Mabsi<b>" );
		
		Button button = new Button( "A button" );
		button.setStyleName( css.button() );
		
		UiBinderSample uiBinderSample = new UiBinderSample();
		
		RootPanel.get().add( listBox );
		RootPanel.get().add( label );
		RootPanel.get().add( table );
		RootPanel.get().add( uiBinderSample );
		RootPanel.get().add( button );
	}
}
