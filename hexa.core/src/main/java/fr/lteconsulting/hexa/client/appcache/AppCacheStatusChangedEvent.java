package fr.lteconsulting.hexa.client.appcache;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class AppCacheStatusChangedEvent extends GwtEvent<AppCacheStatusChangedEvent.Handler> {
    public static final Type<Handler> TYPE = new Type<Handler>();
    private final AppCacheStatus status;

    public AppCacheStatusChangedEvent(AppCacheStatus status) {
        this.status = status;
    }

    @Override
    protected void dispatch(Handler handler) {
        handler.onAppCacheStatusChanged(status);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
        return TYPE;
    }

    public interface Handler extends EventHandler {
        void onAppCacheStatusChanged(AppCacheStatus status);
    }
}