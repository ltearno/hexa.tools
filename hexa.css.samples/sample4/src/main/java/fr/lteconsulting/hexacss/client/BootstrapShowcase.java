package fr.lteconsulting.hexacss.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.css.ThemeManager;
import fr.lteconsulting.hexa.client.css.bindings.BootstrapHexaCss;

/**
 * The main class of this sample.
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 *
 */
public class BootstrapShowcase extends ResizeComposite
{
	ThemeManager themes = new ThemeManager();
	
	@UiField
	ListBox theme;
	
	@UiField
	FlexTable table;
	
	@UiField
	VerticalPanel panel;

	private static BootstrapShowcaseUiBinder uiBinder = GWT.create( BootstrapShowcaseUiBinder.class );

	interface BootstrapShowcaseUiBinder extends UiBinder<Widget, BootstrapShowcase>
	{
	}

	public BootstrapShowcase()
	{
		// UiBinder initialisation
		initWidget( uiBinder.createAndBindUi( this ) );
		
		// themes available
		theme.addItem( "bootstrap.min" );
		theme.addItem( "bootstrap.cerulean.min" );
		theme.addItem( "bootstrap.readable.min" );
		theme.addItem( "bootstrap.superhero.min" );
		theme.addItem( "bootstrap.united.min" );
		theme.addItem( "bootstrap.slate.min" );
		theme.addItem( "bootstrap.spacelab.min" );
		theme.addChangeHandler( new ChangeHandler()
		{
			@Override
			public void onChange( ChangeEvent event )
			{
				themes.setTheme( theme.getSelectedValue() );
			}
		} );
		
		themes.setTheme( "bootstrap.superhero.min" );
		theme.setSelectedIndex( 3 );
		
		// table fill
		for(int j=0;j<10;j++)
			for(int i=0;i<10;i++)
				table.setText( j, i, "Cell " + i + ", " + j );
		
		// some buttons
		panel.add( new Label("Some GWT buttons decorated with Bootstrap css") );
		HorizontalPanel p = new HorizontalPanel();
		panel.add( p );
		addButton( p, "Normal", BootstrapHexaCss.CSS.btnDanger() );
		addButton( p, "Default", BootstrapHexaCss.CSS.btnDefault() );
		addButton( p, "Info", BootstrapHexaCss.CSS.btnInfo() );
		addButton( p, "Primary", BootstrapHexaCss.CSS.btnPrimary() );
		addButton( p, "Success", BootstrapHexaCss.CSS.btnSuccess() );
		addButton( p, "Warning", BootstrapHexaCss.CSS.btnWarning() );
		
		// some alerts
		panel.add( new Label("Some Bottstrap alerts") );
		addAlert( "Danger !", BootstrapHexaCss.CSS.alertDanger() );
		addAlert( "Info !", BootstrapHexaCss.CSS.alertInfo() );
		addAlert( "Success !", BootstrapHexaCss.CSS.alertSuccess() );
		addAlert( "Warning !", BootstrapHexaCss.CSS.alertWarning() );
	}
	
	private void addButton( HorizontalPanel panel, String text, String style )
	{
		Button button = new Button( text );
		button.setStylePrimaryName( BootstrapHexaCss.CSS.btn() );
		button.addStyleName( style );
		panel.add( button );
		
		button.addClickHandler( new ClickHandler()
		{	
			@Override
			public void onClick( ClickEvent event )
			{
				Window.alert( "I am a normal GWT Button !" );
			}
		} );
	}
	
	private void addAlert( String text, String style )
	{
		Label alert = new Label( text );
		alert.setStylePrimaryName( BootstrapHexaCss.CSS.alert() );
		alert.addStyleName( style );
		panel.add( alert );
	}

}
