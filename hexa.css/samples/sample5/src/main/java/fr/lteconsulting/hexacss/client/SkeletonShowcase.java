package fr.lteconsulting.hexacss.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.css.bindings.HexaBootstrapCss;

/**
 * The main class of this sample.
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 *
 */
public class SkeletonShowcase extends Composite
{
	private static BootstrapShowcaseUiBinder uiBinder = GWT.create( BootstrapShowcaseUiBinder.class );

	interface BootstrapShowcaseUiBinder extends UiBinder<Widget, SkeletonShowcase>
	{
	}
	
	@UiField
	Button button1;
	
	@UiField
	Button button2;
	
	@UiField
	FlexTable table;
	
	@UiField
	TextBox textbox;
	
	@UiField
	TextArea textarea;
	
	@UiField
	CheckBox checkbox;
	
	@UiField
	SubmitButton submit;
	
	@UiField
	ListBox listbox;

	public SkeletonShowcase()
	{
		initWidget( uiBinder.createAndBindUi( this ) );
		
		// Because those can't be set through uibinder xml
		textbox.getElement().setId( "exampleEmailInput" );
		textbox.getElement().setPropertyString( "type", "email" );
		textbox.getElement().setPropertyString( "placeholder", "test@mail.com" );
		textarea.getElement().setId( "exampleMessage" );
		textarea.getElement().setPropertyString( "placeholder", "Hi Dave …" );
		listbox.getElement().setId( "exampleRecipientInput" );
		
		listbox.addItem( "Questions" );
		listbox.addItem( "Admirations" );
		listbox.addItem( "What else ?" );
		
//		// table fill
		for(int j=0;j<10;j++)
			for(int i=0;i<5;i++)
				table.setText( j, i, "Cell " + i + ", " + j );
//		
//		// some buttons
//		panel.add( new Label("Some GWT buttons decorated with Bootstrap css") );
//		HorizontalPanel p = new HorizontalPanel();
//		panel.add( p );
//		addButton( p, "Normal", HexaBootstrapCss.CSS.btnDanger() );
//		addButton( p, "Default", HexaBootstrapCss.CSS.btnDefault() );
//		addButton( p, "Info", HexaBootstrapCss.CSS.btnInfo() );
//		addButton( p, "Primary", HexaBootstrapCss.CSS.btnPrimary() );
//		addButton( p, "Success", HexaBootstrapCss.CSS.btnSuccess() );
//		addButton( p, "Warning", HexaBootstrapCss.CSS.btnWarning() );
//		
//		// some alerts
//		panel.add( new Label("Some Bottstrap alerts") );
//		addAlert( "Danger !", HexaBootstrapCss.CSS.alertDanger() );
//		addAlert( "Info !", HexaBootstrapCss.CSS.alertInfo() );
//		addAlert( "Success !", HexaBootstrapCss.CSS.alertSuccess() );
//		addAlert( "Warning !", HexaBootstrapCss.CSS.alertWarning() );
	}
	
	private void addButton( HorizontalPanel panel, String text, String style )
	{
		Button button = new Button( text );
		button.setStylePrimaryName( HexaBootstrapCss.CSS.btn() );
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
		alert.setStylePrimaryName( HexaBootstrapCss.CSS.alert() );
		alert.addStyleName( style );
//		panel.add( alert );
	}

}
