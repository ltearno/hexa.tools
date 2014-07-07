package fr.lteconsulting.hexa.demo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
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
		
		DataBindingDemo dataBindingDemo = new DataBindingDemo();
		dataBindingDemo.run( centerPanel );
	}
}

class Form extends ComplexPanel
{
	static String html = "<b>Demos</b>"
			+ "<ul>"
			+ "<li>Data Binding</li>"
			+ "<li>Jpa 4 Gwt</li>"
			+ "</ul>";
	
	public Form()
	{
		DivElement main = Document.get().createDivElement();
		
		HTMLSnip snip = new HTMLSnip();
		main.setInnerHTML( html );
	}
}