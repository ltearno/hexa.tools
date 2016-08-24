package ${package}.client;

import fr.lteconsulting.angular2gwt.ng.core.Component;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Main angular component for the application.
 * 
 * <p>
 * It simply binds to the <my-app> tag.
 */
@Component(
		selector = "my-app",
		template = "<h1>{{title}}</h1>You can edit the title by changing the text in this box :<br/><input [(ngModel)]='title'/>" )
@JsType
public class ApplicationComponent
{
	@JsProperty
	private String title = "Your application is working !";
}
