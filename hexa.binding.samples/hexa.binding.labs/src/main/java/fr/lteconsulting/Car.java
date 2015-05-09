package fr.lteconsulting;

import fr.lteconsulting.hexa.databinding.properties.Properties;

public class Car {
	private String name;
	private int age;
	private String color;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		Properties.notify(this, "name");
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
		Properties.notify(this, "age");
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		Properties.notify(this, "color");
	}
}
