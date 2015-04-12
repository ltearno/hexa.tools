# Hexa Binding

HexaBinding is a tool allowing dynamic binding between DTOs, Widgets, and any other Java objects in GWT applications. It is open and you can add your own property adapters to the binding engine.

For example, you will be able to write :

		Binder.Bind(listBox).Mode(Mode.OneWay).To(personForm);

With this, you get a one way data binding between the `listBox` and the `personForm` wich displays the selected person.

But there's more, there are plenty of options you can use ! For example :

	