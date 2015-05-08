package fr.lteconsulting.hexa.databinding.test;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import fr.lteconsulting.hexa.databinding.Binder;
import fr.lteconsulting.hexa.databinding.properties.Properties;
import fr.lteconsulting.hexa.databinding.test.dto.A;
import fr.lteconsulting.hexa.databinding.test.dto.ANotif;
import fr.lteconsulting.hexa.databinding.test.dto.B;
import fr.lteconsulting.hexa.databinding.test.dto.BNotif;

public class BinderTest extends TestCase
{
	public void test001()
	{
		A a = new A();
		B b = new B();

		a.firstName = "Hello";

		Binder.bind( a, "firstName" ).to( b, "firstName" );

		assertEquals( "Hello", b.firstName );
	}

	public void test002()
	{
		ANotif source = new ANotif();
		BNotif destination = new BNotif();

		// Binds the source and destination with default options (bidirectional)
		Binder.bind( source, "firstName" ).to( destination, "firstName" );

		// Check source to destination
		String value = "Hello";
		source.setFirstName( value );
		assertEquals( value, destination.getFirstName() );

		// Check destination to source
		value = "Hi !";
		destination.setFirstName( value );
		assertEquals( value, source.getFirstName() );
	}

	public void test003()
	{
		List<String> list = Arrays.asList( "zero", "one", "two", "three", "four" );

		Properties.setValue( list, "selected", 3 );
		assertEquals( 3, Properties.getValue( list, "selected" ) );
	}

	public void test004()
	{
		ANotif source = new ANotif();
		BNotif destination = new BNotif();

		// Binds the source and destination
		Binder.map( source, destination );

		// Check the firstName field

		// Check source to destination
		String value = "Hello";
		source.setFirstName( value );
		assertEquals( value, destination.getFirstName() );

		// Check destination to source
		value = "Hi !";
		destination.setFirstName( value );
		assertEquals( value, source.getFirstName() );

		// Check the lastName field

		// Check source to destination
		value = "Lasty";
		source.setLastName( value );
		assertEquals( value, destination.getLastName() );

		// Check destination to source
		value = "Toooooss";
		destination.setLastName( value );
		assertEquals( value, source.getLastName() );
	}
}