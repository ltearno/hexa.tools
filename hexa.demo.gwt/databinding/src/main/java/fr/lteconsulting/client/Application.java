package fr.lteconsulting.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.css.bindings.BootstrapHexaCss;
import fr.lteconsulting.hexa.databinding.Mode;
import fr.lteconsulting.hexa.databinding.gwt.Binder;
import fr.lteconsulting.hexa.databinding.properties.Properties;

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
		registerClassBundle();

		/**
		 * Create the UI components
		 */
		Widget help = createHelpWidget();
		ArticleList articleList = new ArticleList();
		ArticleForm articleForm = new ArticleForm();
		CategoryForm categoryForm = new CategoryForm();

		/**
		 * Bind the selected article on the article form
		 */
		Binder.bind( Repository.getArticles(), "selected" ).mode( Mode.OneWay ).to( articleForm, "article" );

		/**
		 * Bind the selected article's category on the category form. All properties will be two-way bound.
		 */
		Binder.bind( Repository.getArticles(), "selected.category" ).mapTo( categoryForm );

		/**
		 * Select the first article
		 * 
		 * Note: The SetProperty methods will not find a "selected" property on
		 * the list and will therefore create a dynamic property associated with
		 * the list. This property, named "selected", can be subscribed to by
		 * other parts of the program (through the usual
		 * NotifyPropertyChangedEvent.registerPropertyChangedEvent method)
		 */
		Properties.setValue( Repository.getArticles(), "selected", Repository.getArticles().get( 0 ) );
		
		/**
		 * Initialize the UI
		 */
		Document.get().getBody().addClassName( BootstrapHexaCss.CSS.containerFluid() );
		panel.setStylePrimaryName( BootstrapHexaCss.CSS.row() );
		addWidget( panel, help );
		addWidget( panel, articleList );
		addWidget( panel, articleForm );
		addWidget( panel, categoryForm );
		RootPanel.get().add( panel );
	}

	/**
	 * Class introspection registration.
	 * The bundle contains the required classes for execution
	 */
	private void registerClassBundle()
	{
		MyClassBundle b = GWT.create( MyClassBundle.class );
		b.register();
	}

	private void addWidget( FlowPanel panel, Widget w )
	{
		w.getElement().getStyle().setMargin( 5, Unit.PX );
		w.addStyleName( BootstrapHexaCss.CSS.colMd4() );
		panel.add( w );
	}
	
	private Widget createHelpWidget()
	{
		SimplePanel help = new SimplePanel();
		help.setStylePrimaryName( BootstrapHexaCss.CSS.well() );
		help.add( new HTML( "<h3>Hello !</h3>This demo shows both <b>Hexa Binding</b> (a data binding tool) and <b>HexaCss</b> (a tool used here to embed Bootstrap).<br/>"
				+ "You can select an article in the next zone and the Article and Category zone will hopefully stay up-to-date.<br/>" + "<i>Tip:</i> You can change the color in the category form, it is bound to the form's border color !<br/><br/>"
				+ "No boiler plate code has been written for this demo, thanks to the Hexa binding system." ) );
		return help;
	}
}