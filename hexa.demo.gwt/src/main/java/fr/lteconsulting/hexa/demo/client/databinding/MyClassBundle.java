package fr.lteconsulting.hexa.demo.client.databinding;

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import fr.lteconsulting.hexa.classinfo.gwt.ClazzBundle;
import fr.lteconsulting.hexa.classinfo.gwt.ReflectedClasses;
import fr.lteconsulting.hexa.demo.client.databinding.data.Category;
import fr.lteconsulting.hexa.demo.client.databinding.data.Person;
import fr.lteconsulting.hexa.demo.client.databinding.ui.CategoryForm;
import fr.lteconsulting.hexa.demo.client.databinding.ui.PersonForm;
import fr.lteconsulting.hexa.demo.client.databinding.ui.PersonGrid;

/**
 * List of classes for which we need to have introspection at runtime
 * 
 * @author Arnaud Tournier
 *
 */
public interface MyClassBundle extends ClazzBundle
{
	@ReflectedClasses( classes = {
			Anchor.class,
			Button.class,
			Category.class,
			CategoryForm.class,
			DataBindingDemo.class,
			JavaScriptObject.class,
			Label.class,
			Person.class,
			PersonForm.class,
			PersonGrid.class,
			TextBox.class } )
	void register();
}
