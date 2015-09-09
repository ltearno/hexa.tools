package fr.lteconsulting.hexa.databinding.test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.TextBox;

import fr.lteconsulting.hexa.classinfo.gwt.ClazzBundle;
import fr.lteconsulting.hexa.classinfo.gwt.ReflectedClasses;
import fr.lteconsulting.hexa.databinding.gwt.Binder;

/**
 * In addition to the normal binding adapters that are found in HexaBinding, the
 * GWT version provides binding functionnality for Widgets implementing the
 * HasValue interface.
 * 
 * Here is the related tests.
 * 
 * @author Arnaud Tournier (c) LTE Consulting - 2015 http://www.lteconsulting.fr
 *
 */
public class HasValueDataBindingGwtTest extends GWTTestCase
{
	@Override
	public String getModuleName()
	{
		return "fr.lteconsulting.hexa.databinding.HexaBindingTest";
	}

	@Override
	protected void gwtSetUp() throws Exception
	{
		super.gwtSetUp();
		
		GWT.<MyBundle>create( MyBundle.class ).register();
	}

	interface MyBundle extends ClazzBundle
	{
		@ReflectedClasses( classes = { DTO.class } )
		void register();
	}

	/**
	 * Binding of a DTO property to a TextBox
	 */
	public void test01()
	{
		DTO dto = new DTO();

		TextBox box = new TextBox();

		Binder.bind( dto, "name" ).to( box );

		dto.setName( "toto" );
		assertEquals( "toto", box.getValue() );

		box.setValue( "titi", true );
		assertEquals( "titi", dto.getName() );

		/**
		 * The setText method doesn't throw an event so the 
		 * value does not get updated in b2.
		 * Note that if the user changes the text and leaves 
		 * the text box, it will trigger an event and activate
		 * the data binding
		 */
		box.setText( "tata" );
		assertEquals( "titi", dto.getName() );
	}

	/**
	 * Bind two TextBox together
	 */
	public void test02()
	{
		TextBox b1 = new TextBox();
		TextBox b2 = new TextBox();

		Binder.bind( b1 ).to( b2 );

		b2.setValue( "titi", true );
		assertEquals( "titi", b1.getText() );
		
		/**
		 * The setText method doesn't throw an event so the 
		 * value does not get updated in b2.
		 * Note that if the user changes the text and leaves 
		 * the text box, it will trigger an event and activate
		 * the data binding
		 */
		b1.setText( "toto" );
		assertEquals( "titi", b2.getValue() );
	}
}