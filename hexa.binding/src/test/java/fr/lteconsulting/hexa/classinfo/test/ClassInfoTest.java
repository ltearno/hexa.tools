package fr.lteconsulting.hexa.classinfo.test;

import junit.framework.TestCase;
import fr.lteconsulting.hexa.classinfo.ClassInfo;
import fr.lteconsulting.hexa.classinfo.Clazz;

public class ClassInfoTest extends TestCase
{
	public void test001()
	{
		Clazz<?> clz = ClassInfo.FindClazz( A.class );
		assertNotNull( clz );
	}
	
	public void test002()
	{
		Clazz<?> clz = ClassInfo.FindClazz( A.class );
		assertEquals( clz.getAllFields().size(), 2 );
	}
}

class A
{
	@SuppressWarnings( "unused" )
	private int a;
	
	int b;
}