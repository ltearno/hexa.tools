package fr.lteconsulting.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import fr.lteconsulting.hexa.classinfo.gwt.ClazzBundle;
import fr.lteconsulting.hexa.classinfo.gwt.ReflectedClasses;
import fr.lteconsulting.hexa.databinding.OneWayConverter;
import fr.lteconsulting.hexa.databinding.gwt.Binder;
import fr.lteconsulting.hexa.databinding.propertyadapters.WriteOnlyPropertyAdapter;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint
{
	public void onModuleLoad()
	{
		((MyClassBundle) GWT.create( MyClassBundle.class )).register();

		//
		Label label = new Label();
		TextBox textBox = new TextBox();
		TextBox textBox2 = new TextBox();

		Binder.bind( textBox ).to( label, "text" );
		Binder.bind( textBox ).to( textBox2 );

		RootPanel.get().add( new HTML( "<h3>Type something in one box and click outside, now the boxes and the label are bound !</h3>" ) );
		RootPanel.get().add( textBox );
		RootPanel.get().add( textBox2 );
		RootPanel.get().add( label );

		//
		Person p = new Person();
		HTML html = new HTML();
		TextBox nameBox = new TextBox();

		Binder.bind( p, "name" ).to( nameBox );
		Binder.bind( p, "name" ).withConverter( new OneWayConverter()
		{
			@Override
			public Object convert( Object value )
			{
				return "You are editing person <b>'" + value + "'</b>";
			}
		} ).to( html, "HTML" );

		RootPanel.get().add( new HTML( "<h3>Here, the label and text box are both bound to a Person POJO, through a Converter to format the text</h3>" ) );
		RootPanel.get().add( html );
		RootPanel.get().add( nameBox );
		
		//
		TextBox colorBox = new TextBox();
		
		Binder.bind( colorBox ).to( Document.get().getBody().getStyle(), "backgroundColor" );
		Binder.bind( colorBox ).to( new WriteOnlyPropertyAdapter()
		{
			@Override
			public void setValue( Object object )
			{
				Window.setTitle( (String) object );
			}
		} );
		
		RootPanel.get().add( new HTML( "<h3>Type some color in the box below and it will change the body's background color</h3>" ) );
		RootPanel.get().add( colorBox );
	}

	interface MyClassBundle extends ClazzBundle
	{
		@ReflectedClasses( classes = { JavaScriptObject.class, Label.class, HTML.class, TextBox.class, Person.class } )
		void register();
	}
}
