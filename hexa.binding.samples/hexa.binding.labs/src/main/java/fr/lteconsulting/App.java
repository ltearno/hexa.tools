package fr.lteconsulting;

import fr.lteconsulting.hexa.databinding.Binder;
import fr.lteconsulting.hexa.databinding.Mode;
import fr.lteconsulting.hexa.databinding.tools.Property;

/**
 * Hello world!
 *
 */
public class App 
{
	private Property<Car> car = new Property<Car>(this, "car", null);
	
    public static void main( String[] args )
    {
        new App().run();
    }
    
    private void run() {
		UniversalEditor editor = new UniversalEditor();

		Binder.bind(this, "car").mode(Mode.OneWay).to(editor, "value");

		System.out.println("create car");
		System.out.println("set the car");

		car.setValue(new Car());
		car.getValue().setAge(10);
		car.getValue().setColor("red");
		car.getValue().setName("ferari");

		System.out.println("change car values");

		car.getValue().setAge(13);
		car.getValue().setColor("bianco");
		car.getValue().setName("retro");

		System.out.println("change car");

		car.setValue(new Car());
		car.getValue().setAge(1);
		car.getValue().setColor("yellow brown");
		car.getValue().setName("citroen");
	}
}
