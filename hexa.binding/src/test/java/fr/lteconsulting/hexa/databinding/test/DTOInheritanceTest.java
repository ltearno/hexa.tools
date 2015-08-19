package fr.lteconsulting.hexa.databinding.test;

import junit.framework.TestCase;
import fr.lteconsulting.hexa.databinding.Binder;
import fr.lteconsulting.hexa.databinding.properties.Properties;

public class DTOInheritanceTest extends TestCase
{
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

			Properties.notify( this, "firstName" );
		}
	}
	
	public void test()
	{
		A a = new SubA();
		A b = new SubA();
		
		Binder.bind( a, "firstName" ).to( b, "firstName" );
		
		a.setFirstName( "titi" );
		assertEquals( "titi", b.getFirstName() );
		
		b.setFirstName("tata");
		assertEquals("tata", a.getFirstName());
	}
}