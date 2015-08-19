package fr.lteconsulting.hexa.databinding.test;

import static fr.lteconsulting.hexa.databinding.properties.Properties.getValue;
import static fr.lteconsulting.hexa.databinding.properties.Properties.setValue;
import junit.framework.TestCase;
import fr.lteconsulting.hexa.databinding.properties.Properties;
import fr.lteconsulting.hexa.databinding.tools.Property;

/**
 * One brick of the data binding system is the Property concept.<br/><br/>
 * 
 * A Property is a value attached to an object, that can be set and get.
 * The Property system is responsible to manage how to get and set the 
 * property values.<br/><br/>
 * 
 * It will use by preference :<br/>
 * <ol>
 * 	<li>the getter/setter pair,</li>
 * 	<li>a class member,</li>
 * 	<li>a dynamic property value holder.</li>
 * </ol>
 * 
 * The dynamic property will be created only if necessary.
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 *
 */
public class PropertiesTest extends TestCase
{
	/**
	 * The DTO class for this test.<br/><br/>
	 *
	 * It has a field 'a', a getter/setter pair for the 'b' property
	 * and a {@link Property} instance named 'c'.
	 *
	 * @author Arnaud Tournier
	 */
	class DTO
	{
		int a = 10;

		private String _b;

		public String getB()
		{
			return _b;
		}

		public void setB( String b )
		{
			this._b = b;
		}

		final Property<Integer> c = new Property<Integer>( this, "c", null );

		public Integer getC()
		{
			return c.getValue();
		}

		public void setC( Integer c )
		{
			this.c.setValue( c );
		}
	}

	/**
	 * We cannot access the private member a of class {@link DTO},
	 * but we can use the {@link Properties} class
	 * to help us.
	 */
	public void testPrivateMember()
	{
		DTO dto = new DTO();
		
		// Sets the property value
		setValue( dto, "a", 31 );
		
		// Gets the property value
		Integer a = getValue( dto, "a" );
		assertEquals( 31, (int) a );
	}
	
	/**
	 * If the data class has got a getter and a setter for the
	 * desired property, they are used, as shown
	 */
	public void testGetterSetter()
	{
		DTO dto = new DTO();
		
		// Sets the property value with the normal setter
		dto.setB( "Hello" );
		
		// Gets the property value with the Property getter
		String b = getValue( dto, "b" );
		assertEquals( "Hello", b );
		
		// Sets the property value with the Property setter
		setValue( dto, "b", "By property" );
		
		// Gets the property value with the normal getter
		b = dto.getB();
		assertEquals( "By property", b );
	}
	
	/**
	 * One can hold a property value with a {@link Property} object.
	 * This object will allow to subscribe to data change as we will see
	 * later.
	 */
	public void testPropertyObject()
	{
		DTO dto = new DTO();
		
		// Sets the value
		dto.setC( 42 );
		
		// Gets the value. Note how the {@link Property} class is 
		// "transparent" to the Property system
		Integer c = getValue( dto, "c" );
		assertEquals( 42, (int) c );
		
		// Sets the value. Once again, note that the {@link} property
		// class is "transparent" to the Property system
		setValue( dto, "c", 81 );
		
		// Gets the value
		c = dto.getC();
		assertEquals( 81, (int) c );
	}
	
	/**
	 * Sometimes, the object does not have neither a field, nor a getter/setter
	 * pair to store an information.
	 * 
	 * The Property system provides dynamic property values, so it is possible
	 * to get, set and subscribe to a property value, even if it does not exist
	 * in the original object.
	 */
	public void testDynamicProperty()
	{
		DTO dto = new DTO();
		
		String value = "The value of the dynamic property 'd'";
		
		// Sets the value with the Property system. Since no member of the DTO 
		// class has a name 'd', a dynamic property value holder will be
		// automatically provided
		setValue( dto, "d", value );
		
		// Gets the value with the Property system.
		String d = getValue( dto, "d" );
		assertEquals(value, d);
	}
}