package fr.lteconsulting.hexabinding.sample;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Document;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.SingleSelectionModel;

import fr.lteconsulting.hexa.classinfo.gwt.ClazzBundle;
import fr.lteconsulting.hexa.classinfo.gwt.ReflectedClasses;
import fr.lteconsulting.hexa.client.css.bindings.SkeletonHexaCss;
import fr.lteconsulting.hexa.databinding.gwt.Binder;
import fr.lteconsulting.hexa.databinding.gwt.propertyadapters.SelectionModelAdapter;
import fr.lteconsulting.hexa.databinding.properties.Properties;
import fr.lteconsulting.hexa.databinding.propertyadapters.ChangeDetector;
import fr.lteconsulting.hexa.databinding.propertyadapters.WriteOnlyPropertyAdapter;

public class Application implements EntryPoint
{
	@Override
	public void onModuleLoad()
	{
		// register the classes with introspection
		// when we call the register() method, the type information of the
		// bundle classes is registered into the type system
		((MyBundle) GWT.create( MyBundle.class )).register();

		// create an array of hard-coded person
		final List<Person> persons = new ArrayList<>();
		for( int i = 0; i < 10; i++ )
			persons.add( new Person( "John", i + " Smith" ) );

		// create the person cell list widget
		final CellList<Person> cellList = new CellList<>( new AbstractCell<Person>()
		{
			@Override
			public void render( com.google.gwt.cell.client.Cell.Context context, Person value, SafeHtmlBuilder sb )
			{
				sb.appendEscaped( value.getName() );
			}
		} );
		
		// create the list's selection model
		SingleSelectionModel<Person> model = new SingleSelectionModel<>();
		cellList.setSelectionModel( model );
		
		// gives the data to the list
		cellList.setRowData( persons );
		model.setSelected( persons.get( 0 ), true );
		
		// create the person form
		PersonForm form = new PersonForm();

		// build the UI
		RootPanel.get().setStyleName( SkeletonHexaCss.CSS.container() );
		cellList.setStyleName( SkeletonHexaCss.CSS.column() + " " + SkeletonHexaCss.CSS.threeColumns() );
		form.setStyleName( SkeletonHexaCss.CSS.column() + " " + SkeletonHexaCss.CSS.sixColumns() );
		RootPanel.get().add( cellList );
		RootPanel.get().add( form );
		
		// bind the selected element in the CellList to the selected person
		// in the persons list.
		// Note that we use the 'selected' property of the list although
		// it does not exist in the ArrayList class. HexaBinding will create
		// a virtual property container for us.
		Binder.bind( new SelectionModelAdapter<>( model ) ).to( persons, "selected" );

		// bind-map the selected person to the form. Each field of the 
		// selected person will be two-way bound to the corresponding 
		// field in the form
		Binder.bind( persons, "selected" ).mapTo( form );
		
		// bind the selected person to a special property adapter. Its role
		// is to call the onChange method each time a property of the bound
		// object (here the selected person) changes.
		Binder.bind( persons, "selected" ).to( new ChangeDetector()
		{
			@Override
			protected void onChange( Object object, String property )
			{
				// Since the CellList does not support (easily) updating one row only, we
				// just re-set the cell list data again. That's a performance issue and should
				// not go in production
				cellList.setRowData( persons );
			}
		} );
		
		// We do a first call to the getStatistics method, as the comparison point when we'll call it a second time
		Properties.getStatistics();
		
		// bind the selected person's preferred color to the form's element's border style.
		Binder.bind( persons, "selected.preferredColor" ).to( form, "element.style.borderColor" );
		Binder.bind( persons, "selected.preferredColor" ).to( Document.get(), "body.style.backgroundColor" );
		
		// bind the selected person's name to a WriteOnlyPropertyAdapter. As it name suggests,
		// it can only receive values and cannot be read. Our implementation
		// changes the window's title according to the selected person's name.
		// Note that we can't use the HexaBinding (yet) to bind to the "title" static property
		// of the Window class. That's why we write our own {@link PropertyAdapater}
		Binder.bind( persons, "selected.name" ).to( new WriteOnlyPropertyAdapter()
		{
			@Override
			public void setValue( Object object )
			{
				Window.setTitle( object == null ? "Hexa Binding demo" : (String)object );
			}
		} );
		
		// We now show the actual statistics again. It will contain a text indicating
		// the number of resources created and destroyed since the last Properties.getStatistics() call
		Window.alert( Properties.getStatistics() );
	}

	/**
	 * Declaration of the types which need introspection at runtime.
	 * Here, we give the classes of objects involved with data binding.
	 */
	interface MyBundle extends ClazzBundle
	{
		// List of the classes for which the data binding system needs introspection at runtime
		@ReflectedClasses( classes = { PersonForm.class, ArrayList.class, JavaScriptObject.class } )
		void register();
	}
}
