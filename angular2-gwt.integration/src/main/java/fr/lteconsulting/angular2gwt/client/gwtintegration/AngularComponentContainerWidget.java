package fr.lteconsulting.angular2gwt.client.gwtintegration;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.angular2gwt.client.interop.ng.core.ApplicationRef;
import fr.lteconsulting.angular2gwt.client.interop.ng.core.ComponentFactory;
import fr.lteconsulting.angular2gwt.client.interop.ng.core.ComponentRef;

public class AngularComponentContainerWidget<T> extends Widget
{
	private ComponentFactory factory;
	private ApplicationRef applicationRef;
	private ComponentRef componentRef;

	public AngularComponentContainerWidget( ComponentFactory factory, ApplicationRef applicationRef )
	{
		this.factory = factory;
		this.applicationRef = applicationRef;

		setElement( Document.get().createDivElement() );
	}

	@SuppressWarnings( "unchecked" )
	public T getComponentInstance()
	{
		return (T) componentRef.instance();
	}

	public void detectChanges()
	{
		componentRef.changeDetectorRef.detectChanges();
	}

	public ComponentRef getComponentRef()
	{
		return componentRef;
	}

	@Override
	protected void onAttach()
	{
		super.onAttach();

		if( componentRef != null )
			return;

		componentRef = factory.create( applicationRef.getInjector(), new Object[0], getElement() );

		applicationRef._loadComponent( componentRef );
	}
}