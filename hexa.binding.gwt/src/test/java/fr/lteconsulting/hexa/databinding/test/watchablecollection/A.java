package fr.lteconsulting.hexa.databinding.test.watchablecollection;

import fr.lteconsulting.hexa.databinding.properties.Properties;
import fr.lteconsulting.hexa.databinding.watchablecollection.WatchableCollection;

public class A
{
	int value;
	
	WatchableCollection<A> children;
	
	public A addChild()
	{
		if( children == null )
			setChildren( new WatchableCollection<A>() );
		
		A a = new A();
		children.add( a );
		
		return a;
	}

	public WatchableCollection<A> getChildren() {
		return children;
	}

	public void setChildren(WatchableCollection<A> children) {
		this.children = children;
		Properties.notify( this, "children" );
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
		Properties.notify( this, "value" );
	}
}