package ${package}.client;

import com.google.gwt.core.client.EntryPoint;

import fr.lteconsulting.angular2gwt.client.interop.ng.platformBrowserDynamic.PlatformBrowserDynamic;

/**
 * This is the application entry point. It just bootstraps Angular...
 */
public class Application implements EntryPoint
{
	@Override
	public void onModuleLoad()
	{
		/**
		 * Here we just bootstrap the Angular 2 framework with our application module.
		 * 
		 * The application module is implemented in the {@link ApplicationModule} class
		 */
		PlatformBrowserDynamic.platformBrowserDynamic().bootstrapModule( ApplicationModule_AngularModule.getNgModulePrototype() );
	}
}