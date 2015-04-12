package fr.lteconsulting.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.css.bindings.HexaBootstrapCss;
import fr.lteconsulting.hexa.client.ui.widget.ListBox;

public class ArticleForm extends Composite
{
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

	public ArticleForm(List<Category> categories)
	{
		initWidget( uiBinder.createAndBindUi( this ) );
		setStylePrimaryName( HexaBootstrapCss.CSS.well() );
		
		for(Category c : categories)
			category.addItem( c.name, c );
	}
}
