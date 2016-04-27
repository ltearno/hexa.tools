package ${package}.client;

import fr.lteconsulting.angular2gwt.Component;
import fr.lteconsulting.angular2gwt.RouteConfig;
import fr.lteconsulting.angular2gwt.client.interop.angular.RouterDirectives;
import fr.lteconsulting.angular2gwt.client.interop.angular.RouterProviders;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Main angular component for the application.
 * 
 * <p>
 * It simply binds to the <my-app> tag.
 */
//@formatter:off
@Component(
	selector = "my-app",
	template = "<h1>{{title}}</h1>" )
//@formatter:on
@JsType
public class ApplicationComponent
{
	@JsProperty
	private String title = "Your application is working !";
}
