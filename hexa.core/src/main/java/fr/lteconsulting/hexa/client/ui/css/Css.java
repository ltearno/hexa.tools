package fr.lteconsulting.hexa.client.ui.css;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public class Css
{
	interface CssBundle extends ClientBundle
	{
		CommonCss commonCss();
	}

	public interface CommonCss extends CssResource
	{
		String borderBoxSizing();
	}

	private static CssBundle bundle;

	public static CommonCss css()
	{
		if( bundle == null )
		{
			bundle = GWT.create( CssBundle.class );
			bundle.commonCss().ensureInjected();
		}

		return bundle.commonCss();
	}
}
