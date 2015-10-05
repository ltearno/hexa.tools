package fr.lteconsulting.hexa.client.ui.gwtbootstrap;

import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.Composite;

public abstract class NumberBox extends Composite {
    protected TextBox textBox;

    String allowedChars;
    boolean allowDecimal;
    boolean allowNegative;
    private final KeyPressHandler keyPressHandler = new KeyPressHandler() {
        @Override
        public void onKeyPress(KeyPressEvent event) {
            String key = stringFromCharCode(event.getCharCode());
            if (key == null || key.isEmpty())
                return;

            assert key.length() == 1;

            char c = key.charAt(0);
            if (c == 0)
                return;

            String value = textBox.getText();

            String allowed = allowedChars;

            if (allowNegative && (value == null || value.isEmpty()))
                allowed += "-";

            if (allowDecimal && !value.isEmpty() && !value.contains(",") && !value.contains("."))
                allowed += ".,";

            if (allowed.indexOf(c) < 0)
                event.preventDefault();
        }
    };

    public NumberBox(String placeholder, boolean allowDecimal, boolean allowNegative) {
        allowedChars = "0123456789";
        this.allowDecimal = allowDecimal;
        this.allowNegative = allowNegative;

        textBox = UiUtils.textBox(placeholder);

        textBox.addKeyPressHandler(keyPressHandler);

        initWidget(textBox);
    }

    private static native String stringFromCharCode(int key)
    /*-{
        return String.fromCharCode(key);
    }-*/;
}
