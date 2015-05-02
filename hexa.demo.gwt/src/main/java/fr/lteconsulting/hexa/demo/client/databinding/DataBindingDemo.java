package fr.lteconsulting.hexa.demo.client.databinding;

import static fr.lteconsulting.hexa.client.databinding.Binder.Bind;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.lteconsulting.hexa.client.databinding.Mode;
import fr.lteconsulting.hexa.client.databinding.propertyadapters.WriteOnlyPropertyAdapter;
import fr.lteconsulting.hexa.client.ui.UiBuilder;
import fr.lteconsulting.hexa.demo.client.databinding.data.Category;
import fr.lteconsulting.hexa.demo.client.databinding.data.Person;
import fr.lteconsulting.hexa.demo.client.databinding.ui.CategoryForm;
import fr.lteconsulting.hexa.demo.client.databinding.ui.PersonForm;
import fr.lteconsulting.hexa.demo.client.databinding.ui.PersonGrid;

/**
 * A basic DataBinding use case.
 * 
 * We have a list of Person, each one belonging to a Category.
 * 
 * The data binding is used to bind widgets together :
 * 
 * - the selected person dto to the person edition widget (PersonForm)
 * - the selected person's category to the category edition widget (CategoryForm)
 * - the selected person's name to the window's title.
 * 
 * Data binding is also used inside the different form widgets.
 * 
 * @author Arnaud Tournier
 *
 */
public class DataBindingDemo
{
	static
	{
		/** Register classes for which we need introspection at runtime */
		MyClassBundle myClassBundle = GWT.create( MyClassBundle.class );
		myClassBundle.register();
	}
	
	/** Hard-coded Categories */
	Category enfants = new Category( 1, "Enfant", "#eebbee" );
	Category adultes = new Category( 2, "Adulte", "#bbeeee" );

	/** Hard-coded Persons */
	List<Person> famille = Arrays.asList(
			new Person( 1, "Tournier", "Arnaud", 31, Arrays.asList( "musique", "informatique" ), adultes ),
			new Person( 2, "Cotigny", "Valérie", 35, Arrays.asList( "poker", "dessin" ), adultes ),
			new Person( 4, "Tournier", "Soan", 3, Arrays.asList( "eau" ), enfants ),
			new Person( 6, "Tournier", "Isaia", 0, Arrays.asList( "sol", "sein" ), enfants ) );
	
	/** Widget for persons list, with selected person highlited */
	PersonGrid personListWidget = new PersonGrid();
	
	/** Widget displaying a person's details */
	PersonForm personneForm = new PersonForm();
	
	/** Widget displaying a category's detail */
	CategoryForm categoryForm = new CategoryForm();

	/** Button to circle selected person */
	Button nextButton = new Button( "Next" );
	
	/** Runs the demo */
	public void run( AcceptsOneWidget container )
	{
		/** Add our widgets in the container */
		container.setWidget( UiBuilder.addIn( new VerticalPanel(),
				personListWidget,
				personneForm,
				categoryForm,
				nextButton ) );
		
		/*
		 * Bind things together
		 */
		
		// selectedPerson to person form
		Bind( personListWidget, "selectedPersonne" ).Mode( Mode.OneWay ).Log("PERSONNEFORM").To( personneForm, "personne" );
		
		// maps the selected person's category to the category form
		Bind( personListWidget, "selectedPersonne.category" ).Mode( Mode.OneWay ).MapTo( categoryForm );
		
		// selected person's description to Window's title
		Bind( personListWidget, "selectedPersonne.description" ).Mode( Mode.OneWay ).To( new WriteOnlyPropertyAdapter()
		{
			@Override
			public void setValue( Object object )
			{
				Window.setTitle( (String) object );
			}
		} );
		
		/*
		 * Initialize person list widget
		 */		
		personListWidget.setPersonList( famille );
		personListWidget.setSelectedPersonne( famille.get( 0 ) );
		
		nextButton.addClickHandler( nextButtonClickHandler );
	}
	
	ClickHandler nextButtonClickHandler = new ClickHandler()
	{
		@Override
		public void onClick( ClickEvent arg0 )
		{
			// index of the next person to select
			int index = ( famille.indexOf( personListWidget.getSelectedPersonne() ) + 1 ) % famille.size();
			
			// select it !
			personListWidget.setSelectedPersonne( famille.get(index) );
		}
	};
}