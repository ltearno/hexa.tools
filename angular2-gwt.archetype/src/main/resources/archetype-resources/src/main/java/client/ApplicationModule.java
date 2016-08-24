package ${package}.client;

import fr.lteconsulting.angular2gwt.client.interop.ng.forms.FormsModule;
import fr.lteconsulting.angular2gwt.client.interop.ng.platformBrowser.BrowserModule;
import fr.lteconsulting.angular2gwt.ng.core.NgModule;
import jsinterop.annotations.JsType;

@NgModule(
		imports = { BrowserModule.class, FormsModule.class },
		declarations = ApplicationComponent.class,
		bootstrap = ApplicationComponent.class )
@JsType
public class ApplicationModule
{
}
