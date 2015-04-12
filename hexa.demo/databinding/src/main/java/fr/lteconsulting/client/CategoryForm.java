package fr.lteconsulting.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.css.bindings.HexaBootstrapCss;
import fr.lteconsulting.hexa.client.databinding.Binder;
import fr.lteconsulting.hexa.client.databinding.Mode;
import fr.lteconsulting.hexa.client.databinding.OneWayConverter;

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
		setStylePrimaryName( HexaBootstrapCss.CSS.well() );
		
		Binder.Bind( color ).Mode( Mode.OneWay ).WithConverter( new OneWayConverter()
		{
			@Override
			public Object convert( Object value )
			{
				return "10px solid " + value;
			}
		} ).To( getElement().getStyle(), "border" );
	}
}
