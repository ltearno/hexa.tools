package fr.lteconsulting.hexacss.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

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
	}
}
