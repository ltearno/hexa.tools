package fr.lteconsulting.hexa.demo.client.databinding.ui;

import static fr.lteconsulting.hexa.client.databinding.Binder.Bind;

import java.util.List;

import com.github.gwtbootstrap.client.ui.ListBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import fr.lteconsulting.hexa.client.databinding.Converters;
import fr.lteconsulting.hexa.client.databinding.Mode;
import fr.lteconsulting.hexa.client.databinding.NotifyPropertyChangedEvent;
import fr.lteconsulting.hexa.client.databinding.OneWayConverter;
import fr.lteconsulting.hexa.client.databinding.propertyadapters.WriteOnlyPropertyAdapter;
import fr.lteconsulting.hexa.demo.client.databinding.data.Person;

/**
 * A Widget used to edit a person's detail.
 * It is composed of several TextBoxes, ListBox and so on
 * 
 * @author Arnaud Tournier
 *
 */
public class PersonForm extends Composite
{
	Person personne;

	FlexTable table;

	TextBox nom = new TextBox();
	TextBox prenom = new TextBox();
	TextBox age = new TextBox();
	ListBox passions = new ListBox( true );
	Label category = new Label();

	/**
	 * Builds a PersonForm widget.
	 * 
	 * DataBind a Person object and displays its fields
	 */
	public PersonForm()
	{
		table = new FlexTable();
		initWidget( table );

		table.setText( 0, 0, "Nom" );
		table.setText( 1, 0, "Prenom" );
		table.setText( 2, 0, "Age" );
		table.setText( 3, 0, "Passions" );
		table.setText( 4, 0, "Nom catégorie" );

		table.setWidget( 0, 1, nom );
		table.setWidget( 1, 1, prenom );
		table.setWidget( 2, 1, age );
		table.setWidget( 3, 1, passions );
		table.setWidget( 4, 1, category );

		// Binds the personne's name to the name text box
		Bind( this, "personne.nom" ).To( nom );
		Bind( this, "personne.prenom" ).To( prenom );
		
		// Binds the person's age to the age text box. Note the use of a Converter to change from int to String
		Bind( this, "personne.age" ).WithConverter( Converters.IntegerToString ).To( age );
		
		// Binds the person's passion list (List<String>) to our own PropertyAdapter.
		// Note the use of the OneWay binding mode, becase our PropertyAdapter cannot signal and give changes
		Bind( this, "personne.passions" ).Mode( Mode.OneWay ).To( new WriteOnlyPropertyAdapter()
		{
			@Override
			public void setValue( Object object )
			{
				passions.clear();
				@SuppressWarnings( "unchecked" )
				List<String> list = (List<String>) object;
				if( list == null )
					return;

				for( String passion : list )
					passions.addItem( passion, passion );
			}
		});
		Bind( this, "personne.category.name" ).Mode( Mode.OneWay ).To( category, "text" );

		Bind( this, "personne.category.color" ).Mode( Mode.OneWay ).WithConverter( new OneWayConverter()
		{
			@Override
			public Object convert( Object value )
			{
				if( value == null)
					return "";

				return "5px solid " + (String) value;
			}
		} ).To( getElement().getStyle(), "border" );
	}

	/**
	 * Sets the currently displayed person.
	 * 
	 * If a DataBinding client is connected on the 'personne' property of this object, it
	 * will receive a property change event
	 * 
	 * @param person The Person instance to be displayed
	 */
	public void setPersonne( Person person )
	{
		this.personne = person;
		
		// Notify the Binding system our property value change
		NotifyPropertyChangedEvent.notify( this, "personne" );
	}
}
