package fr.lteconsulting.hexa.client.databinding.test.watchablecollection;

import com.google.gwt.junit.client.GWTTestCase;

public class WatchableCollectionGwtTest extends GWTTestCase
{
	@Override
	public String getModuleName()
	{
		return "fr.lteconsulting.hexa.client.databinding.HexaBindingTest";
	}
	
	public void testA()
	{
		// Only works in the context of javascript, due to an optimization.
		// Need to fix that before re-enabling the tests
//		WatchableCollection<A> collection = new WatchableCollection<>();
//		assertEquals( collection, collection );
//		
//		collection.addCallback( new Action1<List<Change>>() {
//			@Override
//			public void exec( List<Change> param )
//			{
//				finishTest();
//			}
//		});
//		
//		collection.add( new A() );
//		
//		delayTestFinish(500);
	}
	
	public void testB()
	{
		// Only works in the context of javascript, due to an optimization.
		// Need to fix that before re-enabling the tests
//		MyClassBundle bundle = GWT.create( MyClassBundle.class );
//		bundle.register();
//		
//		A a = new A();
//		A b = new A();
//		
//		Binder.Bind( a, "value" ).To( b, "value" ).activate();
//		Binder.Bind( a, "children" ).To( b, "children" ).activate();
//		
//		a.setValue( 55 );
//		b.setChildren( new WatchableCollection<A>() );
//		
//		assertEquals( a.getChildren(), b.getChildren() );
//		a.getChildren().addCallback( new Action1<List<Change>>() {
//			@Override
//			public void exec(List<Change> param) {
//				finishTest();
//			}
//		});
//		
//		b.addChild();
//		
//		assertEquals( a.getValue(), b.getValue() );
//		assertEquals( a.getValue(), 55 );
//		
//		delayTestFinish( 500 );
	}
}
