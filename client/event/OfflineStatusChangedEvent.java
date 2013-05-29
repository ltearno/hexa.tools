package com.hexa.client.event;

import paloit.devoxx2013.app.client.data.OfflineStatusPolicyManager;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class OfflineStatusChangedEvent extends GwtEvent<OfflineStatusChangedEvent.Handler>
{
	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler
	{
		void offlineStatusChanged( OfflineStatusPolicyManager.Status status );
	}

	private final OfflineStatusPolicyManager.Status status;

	public OfflineStatusChangedEvent( OfflineStatusPolicyManager.Status status )
	{
		this.status = status;
	}

	@Override
	protected void dispatch( Handler handler )
	{
		handler.offlineStatusChanged( status );
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType()
	{
		return TYPE;
	}
}