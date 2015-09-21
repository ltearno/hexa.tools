package fr.lteconsulting.mvp.client;

import com.google.gwt.user.client.ui.IsWidget;

public interface IWPContext
{
	void display( IsWidget view );
	
	void setDisplayMode( WPDisplayMode mode );

	void exit();
	void exit(Throwable throwable);
	void exit(Object result);
}
