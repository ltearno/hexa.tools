package fr.lteconsulting.hexa.client.ui.widget;

public interface ValidatorCallback
{
	public enum Button
	{
		Ok,
		Cancel;
	}

	public void onValidatorAction( Button button );
}
