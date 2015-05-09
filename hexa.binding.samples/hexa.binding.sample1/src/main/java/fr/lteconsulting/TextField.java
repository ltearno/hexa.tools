package fr.lteconsulting;

import java.lang.String;

import fr.lteconsulting.hexa.databinding.properties.Properties;

public final class TextField {
	private String value;

	public void setValue(String value) {
		this.value = value;
		Properties.notify(this, "value");
	}

	public String getValue() {
		return this.value;
	}
}
