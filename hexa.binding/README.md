# Hexa Binding

## A sometimes useful data binding non-invasive library for Java (and GWT)

HexaBinding does dynamic binding between values, DTOs, Widgets (for GWT), and any other objects in Java applications. It is open and extensible with possibility for new property adapters added to the binding engine.

For example, one is able to write ...

		Binder.Bind(listBox).Mode(Mode.OneWay).To(personForm);

... getting a one way data binding between the `listBox` and the `personForm` which displays and edits the selected person.

But there's more, there are plenty of options you can use ! For example :

## Path binding

You can write :

		Binder.Bind( personDto, "category.color" ).To( view, "borderColor" );

And the binding system will automatically follow the color of the category of the person. If the person changes its category or if the category's color changes, the view's borderColor will automatically be updated. And since the binding is by defaut two-way, if the view's borderColor changes, the person's category's color will be also updated.

public static Binder Bind( Object source, String propertyPath )
public static Binder Bind( HasValue<?> widget )
public static Binder Bind( PropertyAdapter source )
public Binder Mode( Mode mode )
public Binder Log( String prefix )
public Binder WithConverter( Converter converter )
public Binder DeferActivate()
public DataBinding To( Object destination, String propertyPath )
public DataBinding To( HasValue<?> widget )
public DataBinding MapTo( Object destination )
public DataBinding To( PropertyAdapter destination )

## The binding system

The binding system bases itself on two things :

	- The concept of Property,
	- The notification system.

### Property

A Property is just a simplified way to look at Java objects' methods and fields. Concerning data access, it can be done through direct field access or through the usual getter/getter methods.

So a Property is a named value belonging to an object, that can be readden and written.

If there is a getter or a setter for a property, those are used. But if none are found, direct field access is used.

When there is no getter/setter nor a field to hold a property value, a dynamic property bag is created for the object, allowing the property value to be accessed. So the Property system will allow to store and read values, even if they were not written in the original class.

TODO : show set/get property value

### Notification system

The notification system allows to register for object property values and to notify when a change occur on them. There is no need to implement anything in the client application classes.

To notify a change in a property :

		NotifyPropertyChangedEvent.notify( this, "propertyName" );

This will update all registered clients for that property of that object.

To register for changes from a property on an object :

		NotifyPropertChangedEvent.registerPropertyChangedEvent( source, "propertyName", new Handler()
		{
			public void onNotifyPropertChanged( NotifyPropertyChangedEvent event )
			{
				Object sender = event.getSender();
				String propertyName = event.getPropertyName();
			}
		} )

To register on all properties of an object, "*" can be passed instead of the property name.

The library maintains live a singleton managing all the objects going through a data binding. One thing you need to  do is to let the binding system know when one of your data-bound object property has changed. This is done by executing the following code :

	// Here the object on which the property 'name' changes is 'this'
	NotifyPropertyChangedEvent.notify( this, "name" );

That's typically what you will have to add in your DTO's setters.

## The reflection system

The data binding library is built upon an internal reflection system which allows runtime type information availability. To ensure minimum generated codesize, the reflection system needs to know on which classes it needs to work on at *compile* time. This is done by adding this *glue* code :

	// to be declared somewhere :
	interface MyClassBundle extends ClazzBundle
	{
		// list of classes with introspection
		@ReflectedClasses( classes = {
				ArrayList.class, 
				WatchableCollection.class, 
				Article.class } )
		void register();
	}

	...
	
	// introspection bundle registration
	MyClassBundle bundle = GWT.create( MyClassBundle.class );
	bundle.register();

From there onwards, you can use the data binding tool !

Note that this can happen at several places in the code, the reflected set of classes will just grow accordingly.