package fr.lteconsulting.hexa.client.classinfo.test;

import fr.lteconsulting.hexa.client.classinfo.ClassInfo;
import fr.lteconsulting.hexa.client.classinfo.Clazz;
import junit.framework.TestCase;

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