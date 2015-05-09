package fr.lteconsulting;

import java.lang.String;

import fr.lteconsulting.hexa.databinding.properties.Properties;

public final class Company {
	private String name;
	
	public Company() {
	}

	public Company(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
		Properties.notify(this, "name");
	}

	public String getName() {
		return this.name;
	}
}
