package fr.lteconsulting;

import java.util.ArrayList;
import java.util.List;

import fr.lteconsulting.hexa.client.tools.Action;
import fr.lteconsulting.hexa.databinding.Binder;
import fr.lteconsulting.hexa.databinding.properties.Properties;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		new App().go2();
	}

	void go1() {
		DTO1 src = new DTO1();

		final List<DTO1> list1 = new ArrayList<>();
		int nb = 3000;

		for (int i = 0; i < nb; i++) {
			DTO1 n = new DTO1();
			list1.add(n);

			Binder.bindObject(src).mapTo(n);
		}

		stamp("change source", () -> {
			for (int i = 0; i < nb; i++)
				src.setA(2);
		});

		System.out.println(Properties.getStatistics());
	}

	void go2() {
		DTO1 c = new DTO1();
		int nb = 100;

		DTO1 first = c;

		for (int i = 0; i < nb; i++) {
			DTO1 d = new DTO1();

			Binder.bind(c, "a").to(d, "a");

			c = d;
		}

		DTO1 last = c;

		stamp("changing source", () -> {
			for (int i = 0; i < 10000; i++) {
				first.setA(i);
				last.setA(i + 1);
			}
		});
	}

	void stamp(String title, Action action) {
		System.out.println("running " + title);

		long start = System.nanoTime();
		action.exec();
		long end = System.nanoTime();

		System.out.println("duration: " + (end - start) / 1000 + "us");
	}
}
