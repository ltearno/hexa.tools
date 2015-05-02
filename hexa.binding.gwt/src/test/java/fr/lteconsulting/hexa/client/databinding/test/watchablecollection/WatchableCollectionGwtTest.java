package fr.lteconsulting.hexa.client.databinding.test.watchablecollection;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;

import fr.lteconsulting.hexa.client.databinding.PlatformSpecificProvider;
import fr.lteconsulting.hexa.client.databinding.gwt.Binder;
import fr.lteconsulting.hexa.client.databinding.watchablecollection.Change;
import fr.lteconsulting.hexa.client.databinding.watchablecollection.WatchableCollection;
import fr.lteconsulting.hexa.client.tools.Action1;

public class WatchableCollectionGwtTest extends GWTTestCase
{
	@Override
	public String getModuleName()
	{
		return "fr.lteconsulting.hexa.client.databinding.HexaBindingTest";
	}
	
	public void testA()
	{
		WatchableCollection<A> collection = new WatchableCollection<>();
		assertEquals( collection, collection );
		
		delayTestFinish(500);
		
		collection.addCallback( new Action1<List<Change>>() {
			@Override
			public void exec( List<Change> param )
			{
				finishTest();
			}
		});
		
		collection.add( new A() );
	}
	
	public void testC()
	{
		A a = new A();
		
		PlatformSpecificProvider.get().setObjectMetadata( a, this );
		assertEquals( this, PlatformSpecificProvider.get().getObjectMetadata( a ) );
	}
	
	public void testB()
	{
		MyClassBundle bundle = GWT.create( MyClassBundle.class );
		bundle.register();
		
		A a = new A();
		A b = new A();
		
		Binder.Bind( a, "value" ).To( b, "value" );
		Binder.Bind( a, "children" ).To( b, "children" );
		
		a.setValue( 55 );
		b.setChildren( new WatchableCollection<A>() );
		
		delayTestFinish( 500 );
		
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
	}
}
