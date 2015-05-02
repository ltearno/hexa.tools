package fr.lteconsulting.hexa.databinding.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;
import fr.lteconsulting.hexa.databinding.Binder;
import fr.lteconsulting.hexa.databinding.ObjectPropertiesUtils;
import fr.lteconsulting.hexa.databinding.test.dto.Person;
import fr.lteconsulting.hexa.databinding.test.dto.Workplace;

public class PathBindingTest extends TestCase
{
	public void test()
	{
		List<Workplace> places = new ArrayList<>();
		for( int i = 0; i < 10; i++ )
			places.add( workplace() );

		List<Person> persons = new ArrayList<>();
		for( int i = 0; i < 100; i++ )
			persons.add( person( places ) );

		for( Workplace w : places )
			w.setOwner( persons.get( (int) (Math.random() * persons.size()) ) );
		
		Person personCopy = new Person();
		
		ObjectPropertiesUtils.SetProperty( persons, "selected", persons.get( 0 ) );
		
		Binder.Bind( persons, "selected" ).MapTo( personCopy );
		
		for( int i=0; i<persons.size(); i++)
		{
			ObjectPropertiesUtils.SetProperty( persons, "selected", persons.get( i ) );
			
			assertEquals( persons.get( i ), personCopy );
		}
	}

	private Workplace workplace()
	{
		Workplace r = new Workplace();

		r.setName( TestUtils.randomName() );
		r.setColor( TestUtils.randomColor() );

		return r;
	}

	private Person person( List<Workplace> places )
	{
		Person p = new Person();

		p.setName( TestUtils.randomName() );
		p.setBirthDate( new Date( (long) (new Date().getTime() * Math.random()) ) );
		p.setWorkplace( places.get( (int) (Math.random() * places.size()) ) );

		return p;
	}
}
