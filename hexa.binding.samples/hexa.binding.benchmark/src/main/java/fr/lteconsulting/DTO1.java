package fr.lteconsulting;

import fr.lteconsulting.hexa.databinding.properties.Properties;


public class DTO1 {
	private int a;
	private Integer b;
	private String c;

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
		Properties.notify(this, "a");
	}

	public Integer getB() {
		return b;
	}

	public void setB(Integer b) {
		this.b = b;
		Properties.notify(this, "b");
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
		Properties.notify(this, "c");
	}

}
