package fr.lteconsulting.hexa.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import fr.lteconsulting.hexa.client.event.DeletedEvent.DeletedEventHandler;

public class DeletedEvent<T> extends GwtEvent<DeletedEventHandler<T>>
{
	private T item;

	public DeletedEvent( T item )
	{
		this.item = item;
	}

	public T getItem()
	{
		return item;
	}

	public interface DeletedEventHandler<T> extends EventHandler
	{
		void onDeleted( T item );
	}

	public static Type<DeletedEventHandler<?>> TYPE = new Type<>();

	@SuppressWarnings( "unchecked" )
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DeletedEventHandler<T>> getAssociatedType()
	{
		return (Type<DeletedEventHandler<T>>)(Type<?>)TYPE;
	}

	@Override
	protected void dispatch( DeletedEventHandler<T> handler )
	{
		handler.onDeleted( item );
	}
}
