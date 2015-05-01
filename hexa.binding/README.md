# Hexa Binding

## A sometime useful data binding non-invasive library for GWT

HexaBinding does dynamic binding between values, DTOs, Widgets, and any other Java objects in GWT applications. It is open and extensible with new property adapters to the binding engine.

For example, one is able to write ...

		Binder.Bind(listBox).Mode(Mode.OneWay).To(personForm);

... getting a one way data binding between the `listBox` and the `personForm` wich displays and edits the selected person.

But there's more, there are plenty of options you can use ! For example :

- path can be coupounded : "person.category.color" will be dynamically binded






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

The library maintains live a singleton managing all the objects going through a data binding. One thing you need to  do is to let the binding system know when one of your data-bound object property has changed. This is done by executing the following code :

	// Here the object on which the property 'name' changes is 'this'
	NotifyPropertyChangedEvent.notify( this, "name" );

That's typically what you will have to add in your DTO's setters !

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