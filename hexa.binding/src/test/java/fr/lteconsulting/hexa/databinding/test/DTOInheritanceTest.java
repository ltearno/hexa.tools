package fr.lteconsulting.hexa.databinding.test;

import fr.lteconsulting.hexa.databinding.Binder;
import fr.lteconsulting.hexa.databinding.NotifyPropertyChangedEvent;
import junit.framework.TestCase;

public class DTOInheritanceTest extends TestCase
{
	public void test()
	{
		A a = new SubA();
		A b = new SubA();
		
		Binder.Bind( a, "firstName" ).To( b, "firstName" );
		
		a.setFirstName( "titi" );
		assertEquals( "titi", b.getFirstName() );
		
		b.setFirstName( "tata" );
		assertEquals( "tata", a.getFirstName() );
	}
}

class A
{
	private String firstName;

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName( String firstName )
	{
		this.firstName = firstName;
	}
}

class SubA extends A
{
	public void setFirstName( String firstName )
	{
		super.setFirstName( firstName );
		
		NotifyPropertyChangedEvent.notify( this, "firstName" );
	}
}