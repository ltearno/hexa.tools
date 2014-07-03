package fr.lteconsulting.hexa.client.interfaces;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.user.client.ui.Widget;

public interface IBkgndStackPanel
{
	public interface Callback
	{
		void onMouseWheel( Object cookie, MouseWheelEvent event, int x, int delta );

		void onMouseMove( Object cookie, int x );

		void onKeyUp( Object cookie, KeyUpEvent event );
	}

	public interface Background
	{
		void add( Widget w );

		void add( Widget w, int x, int y );

		void setWidgetPosition( Widget w, int x, int y );

		void removeItem( Widget w );

		void clearAll();
	}

	void setCallback( Callback callback, Object cookie );

	void setSize( int width, int height );

	void clearBackground();

	Background getBackgroundCanvas();

	IStackPanel getStackPanel();
}
