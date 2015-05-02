package fr.lteconsulting.hexa.demo.client.databinding.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import fr.lteconsulting.hexa.client.databinding.Binder;
import fr.lteconsulting.hexa.client.databinding.Mode;

public class CategoryForm extends Composite
{
	TextBox id = new TextBox();
	TextBox name = new TextBox();
	TextBox color = new TextBox();

	public CategoryForm()
	{
		VerticalPanel vp = new VerticalPanel();
		initWidget( vp );

		vp.add( id );
		vp.add( name );
		vp.add( color );

		Binder.Bind( color ).Mode( Mode.OneWay ).DeferActivate().To( getElement().getStyle(), "backgroundColor" );
	}
}
