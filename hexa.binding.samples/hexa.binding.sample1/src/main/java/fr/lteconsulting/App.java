package fr.lteconsulting;

import fr.lteconsulting.hexa.databinding.Binder;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		App app = new App();
		app.run();
	}

	private void run() {
		// A typical UI text field
		TextField textField = new TextField();

		// The three dtos
		Person person = new Person();
		Company companyA = new Company("Acme");
		Company companyB = new Company();

		// Binding between the person's company name and the text field value
		Binder.bind(person, "company.name").to(textField, "value");
		
		person.setCompany(companyA);
		
		System.out.println("text field value: " + textField.getValue());
		
		person.setCompany(companyB);
		
		textField.setValue("LTE Consulting");
		
		System.out.println("companyB name: " + companyB.getName());
	}

	
}
