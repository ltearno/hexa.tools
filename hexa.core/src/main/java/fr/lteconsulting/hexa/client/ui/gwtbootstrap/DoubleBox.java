package fr.lteconsulting.hexa.client.ui.gwtbootstrap;

public class DoubleBox extends NumberBox {
    public DoubleBox() {
        this("", false);
    }

    public DoubleBox(String placeholder) {
        this(placeholder, false);
    }

    public DoubleBox(String placeholder, boolean allowNegative) {
        super(placeholder, true, allowNegative);
    }

    public Double getValue() {
        String text = textBox.getValue();
        if (text == null)
            return null;

        try {
            return Double.parseDouble(text);
        } catch (Exception e) {
        }

        return null;
    }

    public void setValue(Double value) {
        if (value == null)
            textBox.setText("");
        else
            textBox.setText(String.valueOf(value));
    }
}
