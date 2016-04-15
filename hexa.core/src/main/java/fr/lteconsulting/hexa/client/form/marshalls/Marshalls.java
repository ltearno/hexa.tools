package fr.lteconsulting.hexa.client.form.marshalls;

public class Marshalls
{
	public static MarshallString string = MarshallString.get();
	public static MarshallInteger integer = MarshallInteger.get();
	public static MarshallBoolean bool = MarshallBoolean.get();
	public static MarshallHexaDate date = MarshallHexaDate.get();
	public static MarshallHexaTime time = MarshallHexaTime.get();
}
