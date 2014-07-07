package fr.lteconsulting.hexa.demo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

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
