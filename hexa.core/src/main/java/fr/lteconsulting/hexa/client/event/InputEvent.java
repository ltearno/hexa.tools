package fr.lteconsulting.hexa.client.event;

import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.shared.EventHandler;

import fr.lteconsulting.hexa.client.event.InputEvent.InputHandler;

public class InputEvent extends DomEvent<InputHandler> {
    private static Type<InputHandler> TYPE = new Type<>("input", new InputEvent());

    public static Type<InputHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<InputHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(InputHandler handler) {
        handler.onInput(this);
    }

    public interface InputHandler extends EventHandler {
        void onInput(InputEvent event);
    }
}
