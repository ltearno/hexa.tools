package fr.lteconsulting.hexa.classinfo.test.gwt;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;

import fr.lteconsulting.hexa.classinfo.ClassInfo;
import fr.lteconsulting.hexa.classinfo.Clazz;
import fr.lteconsulting.hexa.classinfo.gwt.ClazzBundle;
import fr.lteconsulting.hexa.classinfo.gwt.ReflectedClasses;

public class ClassInfoGwtTest extends GWTTestCase
{
	@Override
	public String getModuleName()
	{
		return "fr.lteconsulting.hexa.classinfo.HexaClassInfoTest";
	}
	
	@Override
	protected void gwtSetUp() throws Exception
	{
		super.gwtSetUp();

		// Registers the class introspection bundle
		GWT.<TestClazzBundle>create( TestClazzBundle.class ).register();
	}

	public void testRegisteredClazz()
	{
		Clazz<?> clz = ClassInfo.FindClazz( A.class );
		assertNotNull( clz );
	}

	public void testFields()
	{
		Clazz<?> clz = ClassInfo.FindClazz( A.class );
		assertEquals( clz.getAllFields().size(), 2 );
	}
}

interface TestClazzBundle extends ClazzBundle
{
	@ReflectedClasses( classes = { A.class } )
	void register();
}