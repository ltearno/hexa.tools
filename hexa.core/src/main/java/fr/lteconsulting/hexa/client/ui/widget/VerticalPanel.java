package fr.lteconsulting.hexa.client.ui.widget;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public class VerticalPanel extends ComplexPanel
{
	public VerticalPanel()
	{
		setElement( Document.get().createDivElement() );
	}
	
	@Override
	public void add( Widget child )
	{
		DivElement container = Document.get().createDivElement();
		getElement().appendChild( container );
		
		add( child, container );
	}
	
	@Override
	public boolean remove( Widget w )
	{
		Element container = w.getElement().getParentElement();
		boolean res = super.remove( w );
		container.removeFromParent();
		return res;
	}
}
