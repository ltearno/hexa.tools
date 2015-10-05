package fr.lteconsulting.hexa.client.ui.widget;

public interface ValidatorCallback {
    public void onValidatorAction(Button button);

    public enum Button {
        Ok,
        Cancel;
    }
}
