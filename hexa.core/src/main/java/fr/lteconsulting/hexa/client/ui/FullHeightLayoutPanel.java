package fr.lteconsulting.hexa.client.ui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ProvidesResize;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class FullHeightLayoutPanel extends Composite implements RequiresResize, ProvidesResize
{
	VerticalPanel panel = new VerticalPanel();
	
	public FullHeightLayoutPanel()
	{
		SimpleLayoutPanel s = new SimpleLayoutPanel();
		s.add( panel );
		panel.setWidth( "100%" );
		panel.setHeight( "100%" );
		initWidget( s );
	}
	
	public void add(Widget widget)
	{
		panel.add( widget );
	}
	
	public void addFull(Widget widget)
	{
		panel.add( widget );
		
		widget.setWidth( "100%" );
		widget.setHeight( "100%" );
		
		widget.getElement().getParentElement().getParentElement().getStyle().setHeight( 100, Unit.PCT );
	}

	@Override
	public void onResize()
	{
		int nb = panel.getWidgetCount();
		for( int i=0; i<nb; i++ )
		{
			Widget child = panel.getWidget( i );
			if( child instanceof RequiresResize )
				((RequiresResize)child).onResize();
		}
	}
}
