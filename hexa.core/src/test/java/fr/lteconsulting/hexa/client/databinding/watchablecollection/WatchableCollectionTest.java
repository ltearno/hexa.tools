package fr.lteconsulting.hexa.client.databinding.watchablecollection;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.junit.client.GWTTestCase;

import fr.lteconsulting.hexa.client.databinding.Binder;
import fr.lteconsulting.hexa.client.databinding.watchablecollection.WatchableCollection.Change;
import fr.lteconsulting.hexa.client.tools.Action1;

public class WatchableCollectionTest extends GWTTestCase
{
	@Override
	public String getModuleName()
	{
		return "fr.lteconsulting.hexa.HexaTest";
	}
	
	public void testA()
	{
		WatchableCollection<A> collection = new WatchableCollection<>();
		assertEquals( collection, collection );
		
		collection.addCallback( new Action1<List<Change>>() {
			@Override
			public void exec( List<Change> param )
			{
				finishTest();
			}
		});
		
		collection.add( new A() );
		
		delayTestFinish(500);
	}
	
	public void testB()
	{
		MyClassBundle bundle = GWT.create( MyClassBundle.class );
		bundle.register();
		
		A a = new A();
		A b = new A();
		
		Binder.Bind( a, "value" ).To( b, "value" ).activate();
		Binder.Bind( a, "children" ).To( b, "children" ).activate();
		
		a.setValue( 55 );
		b.setChildren( new WatchableCollection<A>() );
		
		assertEquals( a.getChildren(), b.getChildren() );
		a.getChildren().addCallback( new Action1<List<Change>>() {
			@Override
			public void exec(List<Change> param) {
				finishTest();
			}
		});
		
		b.addChild();
		
		assertEquals( a.getValue(), b.getValue() );
		assertEquals( a.getValue(), 55 );
		
		delayTestFinish( 500 );
	}
}
