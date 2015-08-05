# Hexa Binding

## A sometimes useful non-invasive data binding library for Java and GWT

HexaBinding does fast dynamic binding between values, DTOs, Widgets (with the GWT add-on artifact), and any other objects in Java applications. It is open and extensible with the possibility to add new property adapters to the binding engine. The library supports standard Java and also has a version for GWT.

*It might also work on Android and with JavaFX, although it wasn't tested on it. Any feedback is appreciated !*

Suppose you have two classes `A` and `B`, one having a `name` field and the other having a `fullName`. Imagine you have two instances `a` and `b` of those classes. You can write :

		Binder.bind( a, "name" ).to( b, "fullName" );

With this one line of code, you have bound the two fields dynamically in a two-way data binding mode.

Now imagine, you have a `Person` and a `Workplace` classes. You can write :

		Binder.bind( person, "workplace.address" ).to( form, "address" );

This will bind the person.getWorkplace().getAddress() value to the form.getAddress() value. Still in a two-way fashion.

Another example. In a UI code you will typically use the data binding library like this :

		Binder.bind( listBox ).mapTo( personForm );

This will build a one way data binding between the `listBox` and the `personForm` which displays and edits the selected person. Then, each matching fields between the selected object and the form will be two-way data bound. Isn't that cool ?!

Inside a GWT application you can write :

		Binder.bind( personDto, "category.color" ).to( widget, "style.borderColor" );

to bind the person's category's color to the widget's border color.

But there's more, there are plenty of options you can use !



## Quick start

Here is a short step by step guide to use the HexaBinding library in a Java project. **For a GWT application, check the GWT Quick start**, it is written later in the document.

First create a Java project. Then add this dependency in your pom.xml :

	<dependency>
		<groupId>fr.lteconsulting</groupId>
		<artifactId>hexa.binding</artifactId>
		<version>1.2</version>
	</dependency>

Then, specify at least the Java 6 language level :

	<plugin>
		<artifactId>maven-compiler-plugin</artifactId>
		<version>3.1</version>
		<configuration>
			<source>1.8</source>
			<target>1.8</target>
		</configuration>
	</plugin>

In your application, create one pojo class all by hand

	import fr.lteconsulting.hexa.databinding.properties.Properties;
	
	public class Person {
		private String name;
	
		public void setName(String name) {
			this.name = name;
			
			Properties.notify(this, "name");
		}
	
		public String getName() {
			return this.name;
		}
	}

Then in the application's main, bind two instances of those classes

	Person a = new Person();
	Person b = new Person();
	
	// bind the name property
	Binder.bind( a, "name" ).to( b, "name" );
	
	a.setName( 'jack' );
	
	// will print 'jack'
	System.out.println( b.getName() );
	
	b.setName( 'paul' );
	
	// will print 'paul'
	System.out.println( a.getName() );

That's really a first step. You can do many other things ! Now, you can read the other part of the manual or investigate into the samples :

- [Sample 1](../hexa.binding.samples/hexa.binding.sample1/), a simple data binding hellow world in pure Java
- [Sample 2](../hexa.binding.samples/hexa.binding.sample2/), a simple data binding master/detail view in Java with GWT






## How to create a binding ?

There are three phases when constructing a data binding :

- specifying the **source**,
- specifying **options**,
- and specifying the **destination**

This is done through the fluent Binder API.

### The source

There are three possible methods to specify the value source :

		public static Binder bind( Object source, String propertyPath )

The first parameter (*source*) is the object on which properties are watched. The second parameter (*propertyPath*) is the path to the property value, starting from the source.

		public static Binder bindObject( Object source )

When using this method, the source data of the binding will be fixed and will be the source object itself.

		public static Binder bind( HasValue<?> widget )

This one is used with GWT. `HasValue` is a standard interface of this framework. When using a HasValue as a data source, the binding system will use the standard GWT mechanism to get and subscribe to the value (that is, the addValueChangeHandler method).

		public static Binder bind( PropertyAdapter source )

This is the more general way to specify a data source. With this method, you have to implement the `PropertyAdapter` by yourself. Note that several implementations are available for common use cases (*WriteOnlyPropertyAdapter*, *DTOMapperPropertyAdapter*, ...).

### The options

After specifying the source, you have an opportunity to customize the options on the data binding.

		public Binder mode( Mode mode )

With this you specify the data binding mode. There are three values : `OneWay`, `TwoWay` and `OneWayToSource`.

		public Binder log( String prefix )

The Log method accepts a prefix. When an event will occur on this binding, it will be logged using this prefix so you can identify easily when things go wrong !

		public Binder withConverter( Converter converter )

Sometimes, you need to convert values between the source and the destination. This is done by calling this method, to which you provide an implementation of the `Converter` interface. You will able to implement the backward and forward conversions.

		public Binder deferActivate()

By default, the data binding is synchronous. For whatever reason, you may want it to be deferred. In that case, you just have to call this method (*it just works with GWT right now*).

### The destination

At the end of the fluent call, you specify the destination of the data binding. You have several possibilities :

		public DataBinding to( Object destination, String propertyPath )

As for the source, this specifies an object and a property path to walk in order to get or set the value.

		public DataBinding to( HasValue<?> widget )

When your destination is an instance of the `HasValue` interface (GWT applications), you can use this method.

		public DataBinding to( PropertyAdapter destination )

Once again, you can provide your own `PropertyAdapter` for the destination of the binding.

		public DataBinding mapTo( Object destination )

The MapTo method will use the source value of the data binding to create a new two-way data binding between each field of the source and each field of the destination.

### Mapping two objects

There is one left method in the Binder that will create a two-way data binding between all matching properties of the objects on the two sides of the data binding. Here is the method :

		public DataBinding map( Object source, Object destination )





## Freeing a data binding

To free all resources associated with a data binding, you just have to call the `terminate()` method on it :

		DataBinding binding = Binder.bind(...)...to(...);
		
		...
		
		binding.terminate();

This will desactivate the binding and unregister all the data handlers. That's a good practice to check whether an application
leaks memory because it will run faster and smoother if less resource is consumed. Refer to the statistics paragraph to see how
it is possible to detect resource leaks with HexaBinding.







## The binding system

The binding system bases itself on two things :

- The concept of Property,
- And the notification system.

### Property

A Property is just a simplified way to look at Java objects' methods and fields. It is a named value belonging to an object, that can be read, written and subscribed for.

The HexaBinding library can use the getters and setters of the objects. It can also directly use the object's field if no getter or getter is found. It will even create *virtual* fields if there is no field in the class with the same name as the property. This adds a very dynamic aspect to the data binding. It is very useful to enhance existing classes dynamically, and allow to attach virtual properties to arbitrary objects. For example one can add the "selected" property to a `java.util.ArrayList` object.

#### Virtual properties

To set a virtual property value, you just have to call :

	Properties.setValue( object, "propertyName", value );

If the object does not already have a "propertyName" property, a virtual one will be created.

To get its value, you call :

	String value = Properties.getValue( object, "propertyName" );

##### Sample : using the dynamic properties to manage the currently selected item.

It is quite usual to maintain a list of objects together with the currently selected object in the list. The selected object is then often edited by the user in some UI component.

The standard `java.util.ArrayList` class does not have the concept of a "selected item". That's OK, because we will use the dynamic property functionality of HexaBinding :

		// A normal Java list creation
		java.util.List<MyPojo> list = new ArrayList<>();
		...
		// We set the "selected" property of the list instance
		Properties.setProperty( list, "selected", value );
	
		// We bind (two-way) each field of the selected value to each field of the editing view
		Binder.bind( list, "selected" ).mapTo( view );

That may seem a little, and that's really a little written code for a lot of things done !

### Notification system

The notification system allows to register for object property value changes and to notify clients when a change occurs on them.

To register for value changes from a property on an object :

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

To notify a change of the property value :

		// Notifies that the "propertyName" value
		// in the 'this' object was just modified
		Properties.notify( this, "propertyName" );

This will update all registered clients for that property of that object. This method should be called by your application's objects in order for the data binding system to work. That's typically the method you will have call in your DTO's setters.

### Integrating your Java objects with the binding system

Any object of any class can be used with the data binding.

If your object has a setter method, then it should calls the `notify(...)` method. Like this :

	// Setter in a java class
	public void setName( String name )
	{
		this.name = name;
		Properties.notify( this, "name" );
	}

If you cannot modify the object's class, you can always call the `notify` method after calling its setter :

	pojo.setName( value );
	Properties.notify( pojo, "name" );

If the object has no getter, you can call :

	Properties.setValue( pojo, "name", value );

But for performance reasons, you may prefer to just write :

	pojo.name = value;
	Properties.notify( pojo, "name" );

Appart from that there is no special things to call to have the data binding library to work with your classes.

#### The @Observable annotation

The HexaBinding library can create observable POJOs for you. This is done by defining a very reduced specification of the desired POJO, with the @Observable annotation on the class. For example :

	@Observable
	class MyPojo
	{
		String name;
		int weight;
		double value;
	}

The HexaBinding annotation processor will process the class and generate a `ObservableMyPojo` class inheriting from `MyPojo` with all the correct getters and setters, along with the constructors calling the super class one's. When the annotated class name ends with 'Internal', the generated class name will be the annotated class name without the 'Internal' suffix. This allows you to choose your naming schema.

*Note :* For your project to work with the Java annotation processing, it must be at least Java 6 and your IDE needs to be correctly configured.

#### The Property class

The Property class can also help you reducing the amount of boilerplate code. It stores a value and exposes a getter and a setter which calls the `notify(...)` method.

An example :

	class MyPojo
	{
		Property<String> name = new Property( ... );
	}
	
	MyPojo pojo = new MyPojo();
	
	Binder.bind( pojo, "name" ).to( otherObject, pptyPath );





## Using the statistics to detect resource leak

It is possible to forget to unregister a binding when it is not used anymore. In that case, the application leaks resources and after a sufficiently long execution, the whole thing could crash.

In order to help the developper in the search of the code which causes binding leaks, one can call the `getStatistics()` method :

		Window.alert( Properties.getStatistics() );

This will show a message looking like this :

		PropertyChanges stats :
		# registered handlers : 6
		# notifications       : 2
		# dispatches          : 2
		
		selected@ArrayList 				=> 5 (diff: 2)
		borderColor@JavaScriptObject$ 	=> 1

First there is three stats numbers : the total number of registered handlers (used to subscribe to property changes), the total number of notifications of property changes that happened so far, and the total number of dispatches that were executed.

Next, comes the number of handlers for each type of property that's watched. In the above example, the 6 registered handlers are in fact 5 handlers on the `selected` property of the `ArrayList` class (that's a virtual property, because the ArrayList class does not have a 'selected' property), and 1 handler on a `borderColor` property of a `JavaScriptObject`object.

The `(diff: 2)` text means that the number of those handlers increased by 2 since the last call of the `getStatistics` method. That's very useful to check whether a part of an application leaks or not. Call the method before opening a view and just after closing it. Inspect the differences and it will give you a clue about which part of the code forgot to call the `terminate()` method and on wich data binding.






## Using the HexaBinding library with GWT

The data binding library is built upon an internal introspection system which allows runtime type information availability. To ensure minimum generated code size, the introspection system needs to know on which classes it needs to work on at *compile* time. This is done by adding this *glue* code :

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

From there onwards, you can use the data binding on objects of those classes !

Note that you can create `ClazzBundle`s at several places in the code, the set of reflected classes will just grow accordingly.

Note also that you don't have to declare subclasses of the JavaScriptObject classes, they are managed automatically. But if you need to use a JavaScriptObject class or subclass, then you need to register the JavaScriptObject class.

### The @Observable annotation

The @Observable annotation can be used instead of @Observable in GWT projects because it generates also the `ClazzBundle` glue code for the
annotated class. Refer to the @Observable paragraph to learn more about POJO generation.

## GWT Quick start

GWT is a very good tool to write web applications in Java. Luckily the HexaBinding library is compatible with GWT and leverage the
compiler architecture to produce efficient data binding.

Here is a quick start guide to create a GWT application using HexaBinding. The code that is discussed here can be found [here](../hexa.binding.samples/hexa.binding.quickstart/).

### Maven

First create a Java GWT project. Then add those dependencies in your pom.xml :

	<dependency>
		<groupId>fr.lteconsulting</groupId>
		<artifactId>hexa.binding.gwt</artifactId>
		<version>1.2</version>
		<scope>compile</scope>
	</dependency>

Then, specify at least the Java 6 language level :

	<plugin>
		<artifactId>maven-compiler-plugin</artifactId>
		<version>3.1</version>
		<configuration>
			<source>1.7</source>
			<target>1.7</target>
		</configuration>
	</plugin>

Our project will be using the `@Observable` annotation and thus annotation processing. To give the generated classes to the gwt maven compiler plugin, you will need to add that :

	<properties>
		<generated-sources>${project.build.directory}/generated-sources/annotations</generated-sources>
	</properties>
	
	<plugin>
		<artifactId>maven-compiler-plugin</artifactId>
		<version>3.1</version>
		<configuration>
			<generatedSourcesDirectory>${generated-sources}</generatedSourcesDirectory>
		</configuration>
	</plugin>
	
	<plugin>
		<groupId>org.codehaus.mojo</groupId>
		<artifactId>build-helper-maven-plugin</artifactId>
		<version>1.9.1</version>
		<executions>
			<execution>
				<id>add-source</id>
				<phase>process-classes</phase>
				<goals>
					<goal>add-source</goal>
				</goals>
				<configuration>
					<sources>
						<source>${generated-sources}</source>
					</sources>
				</configuration>
			</execution>
		</executions>
	</plugin>

### GWT Module

In your gwt module file, add this line :

	<inherits name='fr.lteconsulting.hexa.HexaBinding'/>

### First sample

We will begin with a very simple binding between two text boxes and a label. This will go like this :

	Label label = new Label();
	TextBox textBox = new TextBox();
	TextBox textBox2 = new TextBox();

	RootPanel.get().add( textBox );
	RootPanel.get().add( textBox2 );
	RootPanel.get().add( label );

	Binder.bind( textBox ).to( label, "text" );
	Binder.bind( textBox ).to( textBox2 );

The binding system needs to get one information from you : the classes of the objects that you will use at runtime with the data binding. This will allow HexaBinding to optimize the generated code at compile time. Here the classes used in the data binding are Label and TextBox. We have to create an interface and call the `register` method on it. Here is the interface :

	// Declares the classes used with data binding
	interface MyClassBundle extends ClazzBundle
	{
		@ReflectedClasses( classes = { 
			Label.class,
			TextBox.class
		} )
		void register();
	}

Then before using those classes, we have to register the Label and TextBox types runtime information, like this :

	((MyClassBundle) GWT.create( MyClassBundle.class )).register();

You can now start the application. Typing a value in one textbox should be replicated in the other text box and in the label.

### Second sample

In the second sample, we will bind a TextBox and a Label to a field of a Java POJO.

First, let's define the POJO :

	public class Person
	{
		private String name;

		public void setName( String name )
		{
			this.name = name;
			Properties.notify( this, "name" );
.		}

		public String getName()
		{
			return this.name;
		}
	}

That's a really simple POJO. The only thing asking explanation is the line `Properties.notify( this, "name" );`. It is located in the setter after the modification of the "name" field. It notifies the binding system that the property value has changed. This is required if you want the binding system to be able to watch your values. If you don't call this method, the binding will work, but won't react on your property changes.

To help your productivity, the `@Observable` annotation can be used to generate the Person class. Instead of writing the Person class by hand, you could write a reduced PersonInternal class annotated with @Observable :

	@Observable
	class PersonInternal
	{
		String name;
	}

And the Person class will be generated automatically. This feature uses the annotation processing mechanism, so if you want to use that, you should have correctly configured your IDE.

You don't need to add this POJO class in the set of @ReflectedClasses because the generated implementation does that for you.

In the main code, we first create the pojo, the widgets and add them into the DOM :

	Person p = new Person();
	HTML html = new HTML();
	TextBox nameBox = new TextBox();
	RootPanel.get().add( html );
	RootPanel.get().add( nameBox );

Then we bind things together. First let's bind the TextBox value to the person's name :

	Binder.bind( p, "name" ).to( nameBox );

Then, let's bind the person's name to the html widget, adding a converter in order to format the string :

	Binder.bind( p, "name" ).withConverter( new OneWayConverter()
	{
		@Override
		public Object convert( Object value )
		{
			return "You are editing person <b>'" + value + "'</b>";
		}
	} ).to( html, "HTML" );

The converter accepts the value that is currently transferring between the two sides of the binding and must return the converted value. Here the `value` parameter receives the person's name and returns the String that will be set as the HTML of the html widget.

Now, launch the application and look at how things go...

### Third example

The third example is much simpler. We are going to bind a TextBox's value to the body's background color :

	TextBox colorBox = new TextBox();
		
	Binder.bind( colorBox ).to( Document.get(), "body.style.backgroundColor" );
	
	RootPanel.get().add( colorBox );

For this to work, we have to add the `JavaScript` object in the set of reflected classes. That's because all the Document instance, and the getBody() and getStyle() methods return a subclass of the JavaScriptObject class. With the JavaScriptObject classes, you don't have to register 
subclasses but at least the JavaScriptObject class.

Now let's try a last thing. What if we want to bind the text box value to the window's title ? Since the Window class in GWT has only static methods, we cannot use an instance of set property values on. So we need to create a custom adapter :

	Binder.bind( colorBox ).to( new WriteOnlyPropertyAdapter()
	{
		@Override
		public void setValue( Object object )
		{
			Window.setTitle( (String) object );
		}
	} );

Each time the colorBox value changes, the binding system propagates this change and calls the `setValue( Object object )` method with the propagated value. Here we use the abstract class `WriteOnlyPropertAdapter` which allows us to only implement the setValue.

## Going further

To go further, you can also checkout the [Sample 2](../hexa.binding.samples/hexa.binding.sample2/), which is a very basic application showing a master detail edition view. It is full of comments so it's easy to use it as a first step guide.

If you have questions, remarks or ideas, you can push a pull request or a comment !

Thanks !

## Contributions

Hexa Tools is developped with love by [LTE Consulting](http://www.lteconsulting.fr). If you want to contribute, have suggestions or ideas, please feel free to submit pull requests or to contact me.

(c) LTE Consulting - 2015
