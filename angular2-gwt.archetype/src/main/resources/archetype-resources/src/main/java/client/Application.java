package ${package}.client;

import com.google.gwt.core.client.EntryPoint;

import fr.lteconsulting.angular2gwt.client.interop.angular.Angular;

/**
 * This is the application entry point. It just bootstraps Angular...
 */
public class Application implements EntryPoint
{
	@Override
	public void onModuleLoad()
	{
		/**
		 * Here we just bootstrap the Angular 2 framework with our application controller.
		 * 
		 * The application controller is implemented in the {@link ApplicationComponent} class
		 */
		Angular.bootstrap( ApplicationComponent_AngularComponent.get() );
	}
}