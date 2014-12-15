package fr.lteconsulting.hexa.client.ui.widget;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Image;

import fr.lteconsulting.hexa.client.css.HexaCss;

public class ImageButton extends Image implements ClickHandler
{
	public interface Callback
	{
		void onClick( Object cookie );
	}

	interface Css extends HexaCss
	{
		static final Css CSS = GWT.create( Css.class );

		String main();
	}

	Object cookie = null;
	Callback callback = null;
	boolean enabled = true;

	public @UiConstructor
	ImageButton( ImageResource resource, String title )
	{
		super( resource );
		setTitle( title );

		addStyleName( Css.CSS.main() );
	}

	public void setCallback( Callback callback, Object cookie )
	{
		this.callback = callback;
		this.cookie = cookie;

		addClickHandler( this );
	}

	@Override
	public void onClick( ClickEvent event )
	{
		assert (callback != null);

		if( enabled )
			callback.onClick( cookie );
	}

	public void setEnabled( boolean enabled )
	{
		this.enabled = enabled;
	}
}
