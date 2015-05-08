package fr.lteconsulting.hexa.databinding.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;
import fr.lteconsulting.hexa.databinding.Binder;
import fr.lteconsulting.hexa.databinding.properties.Properties;
import fr.lteconsulting.hexa.databinding.test.dto.ObservableWorkplace;
import fr.lteconsulting.hexa.databinding.test.dto.Person;

public class PathBindingTest extends TestCase
{
	public void test()
	{
		List<ObservableWorkplace> places = new ArrayList<>();
		for( int i = 0; i < 10; i++ )
			places.add( workplace() );

		List<Person> persons = new ArrayList<>();
		for( int i = 0; i < 100; i++ )
			persons.add( person( places ) );

		for( ObservableWorkplace w : places )
			w.setOwner( persons.get( (int) (Math.random() * persons.size()) ) );
		
		Person personCopy = new Person();
		
		Properties.setValue( persons, "selected", persons.get( 0 ) );
		
		Binder.bind( persons, "selected" ).mapTo( personCopy );
		
		for( int i=0; i<persons.size(); i++)
		{
			Properties.setValue( persons, "selected", persons.get( i ) );
			
			assertEquals( persons.get( i ), personCopy );
		}
	}

	private ObservableWorkplace workplace()
	{
		ObservableWorkplace r = new ObservableWorkplace();

		r.setName( TestUtils.randomName() );
		r.setColor( TestUtils.randomColor() );

		return r;
	}

	private Person person( List<ObservableWorkplace> places )
	{
		Person p = new Person();

		p.setName( TestUtils.randomName() );
		p.setBirthDate( new Date( (long) (new Date().getTime() * Math.random()) ) );
		p.setWorkplace( places.get( (int) (Math.random() * places.size()) ) );

		return p;
	}
}
