package fr.lteconsulting.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.databinding.Mode;
import fr.lteconsulting.hexa.databinding.OneWayConverter;
import fr.lteconsulting.hexa.databinding.gwt.Binder;

/**
 * A very minimalist form of scaffolding which is possible with Hexa Binding.
 * 
 * Here the edition form is really just a widget with two fields : name and color. Those can
 * be used automatically by the binding system when needed. The {@link Application} class
 * maps this form on the selected user's category.
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 *
 */
public class CategoryForm extends Composite
{
	@UiField
	TextBox name;
	
	@UiField
	TextBox color;

	private static CategoryFormUiBinder uiBinder = GWT.create( CategoryFormUiBinder.class );

	interface CategoryFormUiBinder extends UiBinder<Widget, CategoryForm>
	{
	}

	public CategoryForm()
	{
		initWidget( uiBinder.createAndBindUi( this ) );
		
		/**
		 * Bind the color field's value to the form border's color.
		 * 
		 * Since the color field's value is just a String, we use a converter
		 * to generate the correct value for the setBorder" method of 
		 * the getElement().getStyle() object.
		 */
		Binder.bind( color ).mode( Mode.OneWay ).withConverter( new OneWayConverter()
		{
			@Override
			public Object convert( Object value )
			{
				return "10px solid " + value;
			}
		} ).to( getElement().getStyle(), "border" );
	}
}
