package fr.lteconsulting.client;

import java.util.ArrayList;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.TextBox;

import fr.lteconsulting.hexa.classinfo.gwt.ClazzBundle;
import fr.lteconsulting.hexa.classinfo.gwt.ReflectedClasses;
import fr.lteconsulting.hexa.databinding.tools.Property;
import fr.lteconsulting.hexa.databinding.watchablecollection.WatchableCollection;

/**
 * Registers the classes that are needed by the introspection module
 * in order for the data binding to work.
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 *
 */
interface MyClassBundle extends ClazzBundle
{
	@ReflectedClasses( classes = {
			ArrayList.class, 
			WatchableCollection.class, 
			Article.class, 
			ArticleForm.class, 
			ArticleList.class, 
			Property.class, 
			Anchor.class, 
			TextBox.class, 
			JavaScriptObject.class, 
			Category.class, 
			CategoryForm.class } )
	void register();
}