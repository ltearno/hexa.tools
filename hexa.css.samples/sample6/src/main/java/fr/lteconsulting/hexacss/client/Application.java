package fr.lteconsulting.hexacss.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * This sample displays the {@link PureCssShowcase} widget written with UiBinder.
 * 
 * This widget uses HexaCss to bind to the CSS files.
 * 
 * The theme files directly come from free Skeleton CSS file that can be found on :
 * - http://getskeleton.com/
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
		PureCssShowcase showCase = new PureCssShowcase();
		RootLayoutPanel.get().add( showCase );
	}
}
