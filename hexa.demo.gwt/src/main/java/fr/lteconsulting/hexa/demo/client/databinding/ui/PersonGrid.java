package fr.lteconsulting.hexa.demo.client.databinding.ui;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;

import fr.lteconsulting.hexa.client.databinding.Binder;
import fr.lteconsulting.hexa.client.databinding.Mode;
import fr.lteconsulting.hexa.client.databinding.NotifyPropertyChangedEvent;
import fr.lteconsulting.hexa.demo.client.databinding.data.Person;

public class PersonGrid extends Composite
{
	private final FlexTable table;
	private List<Person> personnes = null;
	
	private Person selectedPersonne;
	
	public PersonGrid()
	{
		table = new FlexTable();
		
		initWidget( table );
	}
	
	public void setPersonList( List<Person> personnes )
	{
		this.personnes = personnes;
		
		fillDisplay();
	}
	
	public Person getSelectedPersonne()
	{
		return selectedPersonne;
	}

	public void setSelectedPersonne( Person p )
	{
		if( selectedPersonne != null )
			table.getRowFormatter().getElement( personnes.indexOf( selectedPersonne ) ).getStyle().clearBackgroundColor();
			
		selectedPersonne = p;
		
		if( selectedPersonne != null )
			table.getRowFormatter().getElement( personnes.indexOf( selectedPersonne ) ).getStyle().setBackgroundColor( "yellow" );

		// Notify the binding system
		NotifyPropertyChangedEvent.notify( this, "selectedPersonne" );
	}
	
	private void fillDisplay()
	{
		table.clear( true );
		
		for( int i = 0; i < personnes.size(); i++ )
		{
			final Person p = personnes.get( i );

			// Bind each anchor's text to person's description
			Anchor a = new Anchor();
			Binder.Bind( p, "description" ).Mode( Mode.OneWay ).To( a, "text" );

			table.setWidget( i, 0, a );
			a.addClickHandler( new ClickHandler()
			{
				@Override
				public void onClick( ClickEvent event )
				{
					setSelectedPersonne( p );
				}
			} );
		}
	}
}
