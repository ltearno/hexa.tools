package fr.lteconsulting.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.classinfo.ClazzBundle;
import fr.lteconsulting.hexa.client.classinfo.ReflectedClasses;
import fr.lteconsulting.hexa.client.css.bindings.HexaBootstrapCss;
import fr.lteconsulting.hexa.client.databinding.Binder;
import fr.lteconsulting.hexa.client.databinding.Mode;
import fr.lteconsulting.hexa.client.databinding.propertyadapters.ObjectPropertiesUtils;
import fr.lteconsulting.hexa.client.databinding.tools.Property;
import fr.lteconsulting.hexa.client.ui.UiBuilder;
import fr.lteconsulting.hexa.client.ui.widget.ListBox;

/**
 * The GWT EntryPoint class to the application
 * 
 * @author Arnaud Tournier
 * 
 */
public class Application implements EntryPoint
{
	FlowPanel panel = new FlowPanel();
	
	@Override
	public void onModuleLoad()
	{
		//GWT.debugger();
		
		Document.get().getBody().addClassName( "container-fluid" );
		panel.setStylePrimaryName( HexaBootstrapCss.CSS.row() );

		MyBundle b = GWT.create( MyBundle.class );
		b.register();

		List<Category> categories = Arrays.asList( new Category( "Food", "#652" ), new Category( "Travel", "#256" ), new Category( "Sport", "#265" ) );

		List<Article> articles = new ArrayList<>();
		for( int i = 0; i < 7; i++ )
			articles.add( new Article( randomName(), ((int)(500*Math.random())) + " grams", categories.get( (int) (Math.random() * categories.size()) ) ) );

		ListBox<Article> listBox = new ListBox<>();
		listBox.setStylePrimaryName( HexaBootstrapCss.CSS.formControl() );
		for( Article a : articles )
			listBox.addItem( a.getName(), a );

		ArticleList list = new ArticleList( articles );
		ArticleForm form = new ArticleForm( categories );
		CategoryForm categoryForm = new CategoryForm();
		
		Panel selectPanel = new Panel( "Select an article" );
		selectPanel.add( UiBuilder.vert( listBox, list ) );
		
		SimplePanel help = new SimplePanel();
		help.add( new HTML( "<h3>Hello !</h3>This demo shows both <b>Hexa Binding</b> (a data binding tool) and <b>HexaCss</b> (a tool used here to embed Bootstrap).<br/>You can select an article in the next zone and the Article and Category zone will hopefully stay up-to-date.<br/><i>Tip:</i> You can change the color in the category form, it is bound to the form's border color !<br/><br/>No boiler plate code has been written for this demo, thanks to the Hexa binding system." ) );
		help.setStylePrimaryName( HexaBootstrapCss.CSS.well() );

		addWidget( help );
		addWidget( selectPanel );
		addWidget( form );
		addWidget( categoryForm );
		RootPanel.get().add( panel );
		
		Binder.Bind( articles, "selected" ).To( listBox );
		Binder.Bind( articles, "selected" ).To( list, "selectedArticle" );
		Binder.Bind( articles, "selected" ).Mode( Mode.OneWay ).To( form, "$DTOMap" );
		Binder.Bind( articles, "selected.category" ).Mode( Mode.OneWay ).To( categoryForm, "$DTOMap" );
		
		// To select the current item, you can either do
		// listBox.setValue( articles.get(0) );
		// Or with the SetProperty method which uses dynamic properties if needed
		ObjectPropertiesUtils.SetProperty( articles, "selected", articles.get(((int)(Math.random()*articles.size()))) );
		
		listBox.getElement().focus();
	}
	
	private void addWidget(Widget w)
	{
		w.getElement().getStyle().setMargin( 5, Unit.PX );
		w.addStyleName( HexaBootstrapCss.CSS.colMd4() );
		panel.add( w );
	}

	private final static String[] names = { "Bike", "Lean", "Starter", "Kit", "Wheel", "Dumb" };

	private static String randomName()
	{
		return names[(int) (names.length * Math.random())] + " " + names[(int) (names.length * Math.random())];
	}

}

interface MyBundle extends ClazzBundle
{
	@ReflectedClasses( classes = { ArrayList.class, Article.class, ArticleForm.class, ArticleList.class, Property.class, Anchor.class, TextBox.class, JavaScriptObject.class, Category.class, CategoryForm.class } )
	void register();
}