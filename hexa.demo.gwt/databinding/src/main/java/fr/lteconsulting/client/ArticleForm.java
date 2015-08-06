package fr.lteconsulting.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.ui.widget.ListBox;
import fr.lteconsulting.hexa.databinding.gwt.Binder;
import fr.lteconsulting.hexa.databinding.tools.Property;

/**
 * This very simple class is just what is needed to use a Widget
 * as an editing form, when using HexaBinding...
 * 
 * - There is an Article property which holds the edited article.
 * - The form's fields are automatically detected and two-way bound to the article fields.
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 *
 */
public class ArticleForm extends Composite
{
	Property<Article> article = new Property<Article>( this, "article", null );

	@UiField
	public TextBox name;

	@UiField
	public TextBox weight;

	@UiField
	public ListBox<Category> category;

	private static ArticleFormUiBinder uiBinder = GWT.create( ArticleFormUiBinder.class );

	interface ArticleFormUiBinder extends UiBinder<Widget, ArticleForm>
	{
	}

	public ArticleForm()
	{
		/**
		 * Automatically bind (two-way) the article's fields to our form fields (name, weight and category)
		 */
		Binder.bind( article ).mapTo( this );

		initWidget( uiBinder.createAndBindUi( this ) );

		/**
		 * Fill the combo with the possible categories, the category selection will happen through
		 * the data binding mechanism.
		 * 
		 * Here, one could have chosen to use dynamic categories !
		 */
		for( Category c : Repository.getCategories() )
			category.addItem( c.name.getValue(), c );
	}
}
