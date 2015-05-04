# Hexa Binding

## A sometimes useful data binding non-invasive library for Java (and GWT)

HexaBinding does dynamic binding between values, DTOs, Widgets (for GWT), and any other objects in Java applications. It is open and extensible with possibility for new property adapters added to the binding engine. The library supports vanilla Java and also has a version for GWT.

Suppose you have two classes `A` and `B`, each one having a `Name` field. Imagine you have two instances `a` and `b` of thoses classes. You can write :

		Binder.Bind( a, "name" ).To( b, "name" );

With this one line of code, you have bound the two fields dynamically in a two-way data binding mode.

Now imagine, you have a `Person` and a `Workplace` classes. You can write :

		Binder.Bind( person, "workplace.address" ).To( form, "address" );

This will bind the person.getWorkplace().getAddress() value to the form.getAddress() value. Still in a two-way fashion.

In a UI code, you will typically write :

		Binder.Bind(listBox).Mode(Mode.OneWay).MapTo(personForm);

This will build a one way data binding between the `listBox` and the `personForm` which displays and edits the selected person. In this case, the person form will be inspected to find matching fields with the object selected in the listBox.

There's more, there are plenty of options you can use !

## Path binding

You can write :

		Binder.Bind( personDto, "category.color" ).To( view, "borderColor" );

And the binding system will automatically follow the color of the category of the person. If the person changes its category or if the category's color changes, the view's borderColor will automatically be updated. And since the binding is by defaut two-way, if the view's borderColor changes, the person's category's color will be also updated.




## How do I create a binding ?

There are three phases when constructing a data binding :

- specifying the **source**,
- specifying **options**,
- and specifying the **destination**

This is done through the fluent Binder API.

### The source

There are three possible methods to specify the value source :

		public static Binder Bind( Object source, String propertyPath )

The first parameter (*source*) is the object on which properties are watched. The second parameter (*propertyPath*) is the path to the property value, starting from the source.

		public static Binder Bind( HasValue<?> widget )

This one is use with GWT. `HasValue` is a standard interface of this framework. When using a HasValue as a data source, the binding system will use the standard GWT mechanism to get and subscribe to the value.

		public static Binder BindObject( Object source )

When using this method, the source data of the binding will be fixed and will be the source object itself.

		public static Binder Bind( PropertyAdapter source )

This is the more general way to specify a data source. With this method, you have to implement the `PropertyAdapter` by yourself. Note that several implementations are available for common use case (*WriteOnlyPropertyAdapter*, *DTOMapperPropertyAdapter*, ...).

### The options

After specifying the source, you have an opportunity to customize the options on the data binding.

		public Binder Mode( Mode mode )

With this you specify the data binding mode. There are three values : `OneWay`, `TwoWay` and `OneWayToSource`.

		public Binder Log( String prefix )

The Log method accepts a prefix. When an event will occur on this binding, it will be logged, using the prefix so you can identify easily when things go wrong !

		public Binder WithConverter( Converter converter )

Sometimes, you need to convert values between the source and the destination. This is done with this method, to which you provide an implementation of the `Converter` interface. You will have the opportunity to implement the conversion for the two ways the data binding can happen.

		public Binder DeferActivate()

By default, the databinding is synchronous. For whatever reason, you may want it to be deferred. In that case, you just have to call this method.

### The destination

At the end of the fluent call, you have to specify the destination of the data binding.

		public DataBinding To( Object destination, String propertyPath )

As for the source, this specifies an object and a property path to walk in order to get or set the value.

		public DataBinding To( HasValue<?> widget )

When your destination is an instance of the `HasValue` interface (GWT applications), you can use this method.

		public DataBinding To( PropertyAdapter destination )

Once again, you can provide your own `PropertyAdapter` for the destination of the binding.

		public DataBinding MapTo( Object destination )

The MapTo method will use the source value of the data binding to create a new two-way databinding between each field of the source and each field of the destination.

### Mapping two objects

There is one left method in the Binder which is :

		public DataBinding Map( Object source, Object destination )

This method will create a two-way data binding between all matching properties of the two objects.





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

		Properties.notify( this, "propertyName" );

This will update all registered clients for that property of that object.

To register for changes from a property on an object :

		Properties.registerPropertyChangedEvent( source, "propertyName", new PropertyChangedHandler()
		{
			public void onPropertyChanged( PropertyChangedEvent event )
			{
				// here is the object on which the property changed
				Object sender = event.getSender();

				// here is the name of the property that changed
				String propertyName = event.getPropertyName();
			}
		} )

To register on all properties of an object, `"*"` can be passed instead of the property name.

The library maintains live a singleton managing all the objects going through a data binding. One thing you need to  do is to let the binding system know when one of your data-bound object property has changed. This is done by executing the following code :

		// Here the object on which the property 'name' changes is 'this'
		Properties.notify( this, "name" );

That's typically what you will have to add in your DTO's setters.

## The reflection system

**This is only useful for GWT users, others don't mind !**

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


## Notes

### How to write POJO that interact well with the binding system ?

TODO : Explain better how to add the Properties.notify( this, "toto" );

### Using the dynamic properties to manage the currently selected item.

Often it is needed to maintain a list of objects, together with a currently selected object. The selected object is then often edited in some view.

The standard ArrayList class does not have the concept of the "selected item". That's OK, because we will use the dynamic property of HexaBinding :

		// We set the "selected" property of the list instance
		ObjectPropertyUtils.SetProperty( list, "selected", value );
	
		// We bind (two-way) each field of the selected value to each field of the editing view
		Binder.Bind( list, "selected" ).MapTo( view );

That may seem a little, and that's really a little written code for a lot of things done !

### Using the @Observable annotation to create watchable POJO easily

TODO : show how to configure Eclipse and how to write an Observable (with the two naming conventions), and what to expect from it.

### The Property class

TODO : show how this can ease the notification management

### Example with Converter

### Example WriteOnlyPropertyAdapter

### DOC WhenChangesHappen

### WatchableCollection