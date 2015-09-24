package fr.lteconsulting;

import java.lang.String;

import fr.lteconsulting.hexa.databinding.properties.Properties;

public final class Person {
    private String name;

    private Company company;

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
        Properties.notify(this, "company");
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        Properties.notify(this, "name");
    }
}
