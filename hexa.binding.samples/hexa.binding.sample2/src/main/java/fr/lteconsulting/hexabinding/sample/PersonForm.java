package fr.lteconsulting.hexabinding.sample;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * The form widget that we will use to edit the person objects.
 * 
 * Note that it is not bounded to the {@link Person} class. Only the
 * fields are named with the same name as the Person's fields.
 * That means that this form can be reused with other classes having the 
 * same fields.
 * 
 * @author Arnaud
 *
 */
public class PersonForm extends Composite
{
	private static PersonFormUiBinder uiBinder = GWT.create( PersonFormUiBinder.class );

	interface PersonFormUiBinder extends UiBinder<Widget, PersonForm>
	{
	}

	@UiField
	TextBox firstName;

	@UiField
	TextBox lastName;

	@UiField
	TextBox preferredColor;

	public PersonForm()
	{
		initWidget( uiBinder.createAndBindUi( this ) );
		
		getElement().getStyle().setBorderWidth( 10, Unit.PX );
		getElement().getStyle().setBorderStyle( BorderStyle.SOLID );
	}
}
