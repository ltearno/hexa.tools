package fr.lteconsulting.hexa.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class UserLoggedInEvent extends GwtEvent<UserLoggedInEvent.Handler>
{
	public interface Handler extends EventHandler
	{
		public void onUserLoggedIn( UserLoggedInEvent event );
	}

	public UserLoggedInEvent()
	{
	}

	protected void dispatch( UserLoggedInEvent.Handler handler )
	{
		handler.onUserLoggedIn( this );
	}

	public GwtEvent.Type<UserLoggedInEvent.Handler> getAssociatedType()
	{
		return TYPE;
	}

	public static final GwtEvent.Type<UserLoggedInEvent.Handler> TYPE = new GwtEvent.Type<UserLoggedInEvent.Handler>();
}
