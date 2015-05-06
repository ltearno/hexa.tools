package fr.lteconsulting.hexacss.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import fr.lteconsulting.hexa.client.css.ThemeManager;

/**
 * This sample displays the {@link BootstrapShowcase} widget written with UiBinder.
 * 
 * This widget uses HexaCss to bind to the CSS files, and uses the {@link ThemeManager}
 * to switch between different Bootstrap themes.
 * 
 * The theme files directly come from free Bootstrap CSS files that can be found on :
 * - Bootswatch : https://bootswatch.com/
 * - Bootstrap Zero : http://bootstrapzero.com/
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 *
 */
public class Application implements EntryPoint
{
	@Override
	public void onModuleLoad()
	{
		BootstrapShowcase showCase = new BootstrapShowcase();
		RootLayoutPanel.get().add( showCase );
	}
}
