package fr.lteconsulting.hexa.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class SynchronizationDownlinkEvent extends GwtEvent<SynchronizationDownlinkEvent.Handler> {
    public static final Type<Handler> TYPE = new Type<Handler>();

    @Override
    protected void dispatch(Handler handler) {
        handler.onSynchronizationDownlinkEvent();
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
        return TYPE;
    }

    public interface Handler extends EventHandler {
        void onSynchronizationDownlinkEvent();
    }
}
