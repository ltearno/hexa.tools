# Hexa Binding

HexaBinding is a tool allowing dynamic binding between DTOs, Widgets, and any other Java objects in GWT applications. It is open and you can add your own property adapters to the binding engine.

As en example, you will be able to write :

		Binder.Bind(listBox, "selectedPerson").Mode(Mode.ONE_WAY).To(personForm, "$DTOMap");

