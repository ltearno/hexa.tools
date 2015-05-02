package fr.lteconsulting.hexa.demo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import fr.lteconsulting.hexa.client.tools.HTMLSnip;
import fr.lteconsulting.hexa.client.ui.containers.CenterPanel;
import fr.lteconsulting.hexa.demo.client.databinding.DataBindingDemo;

/**
 * The GWT EntryPoint class to demo things from the Hexa Tools library
 * 
 * @author Arnaud Tournier
 *
 */
public class HexaDemo implements EntryPoint
{
	@Override
	public void onModuleLoad()
	{
		CenterPanel centerPanel = new CenterPanel();
		RootLayoutPanel.get().add( centerPanel );
//		centerPanel.setWidget( new Form() );
		
		DataBindingDemo dataBindingDemo = new DataBindingDemo();
		dataBindingDemo.run( centerPanel );
	}
}

class Form extends ComplexPanel
{
	HTMLSnip snip = new HTMLSnip( "<b>Demos</b>"
			+ "<ul>"
			+ "<li id='#DATABINDING#'>Data Binding</li>"
			+ "<li id='#PERSISTENCE#'>Jpa 4 Gwt</li>"
			+ "</ul>" );
	
	Element dataBinding;
	Element persistence;
	
	public Form()
	{
		snip.addElement( "DATABINDING" );
		snip.addElement( "PERSISTENCE" );
		
		DivElement main = Document.get().createDivElement();
		
		main.setInnerHTML( snip.getSnip() );
		
		addDomHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				if( event.getNativeEvent().getEventTarget() == dataBinding.cast() )
					Window.alert( "Data Binding !" );
				if( event.getNativeEvent().getEventTarget() == persistence.cast() )
					Window.alert( "Persistence !" );
			}
		}, ClickEvent.getType() );
	}
	
	@Override
	protected void onLoad()
	{
		super.onLoad();
		
		dataBinding = Document.get().getElementById( snip.getElementId( "DATABINDING" ) );
		persistence = Document.get().getElementById( snip.getElementId( "PERSISTENCE" ) );
	}
}