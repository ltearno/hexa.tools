---
layout: page
title: Angular 2 Boot
---

*LATEST NEWS !* Angular2Boot [will be presented](https://oracle.rainfocus.com/scripts/catalog/oow16.jsp?event=javaone&search=angular&search.event=javaone) at **[JavaOne 2016](https://www.oracle.com/javaone/index.html)** this year ! We hope to meet your there and discuss with you !

**Angular2Boot** is an opiniated framework to build web applications in Java 8. It is built upon rock-solid foundations : [Angular 2](https://angular.io/), [GWT](http://www.gwtproject.org/) and [Spring Boot](http://projects.spring.io/spring-boot/). You can of course use it with any Java backend, like JavaEE with JAX-RS Web services.

It provides a very effective way to build Single Page Applications. The development mode is iterative and quick (live code reload), with the strongly typed qualities of Java and the functionalities brought by modern IDEs. The produced code is easy to deploy (relying on one *jar* only), robust (runs on the JVM) and optimized (Angular 2 change detection and GWT optimized compilation).

Build a starting application in less than 5 minutes and enter into the details to get most of **Angular2Boot** !

*Angular2Boot is ready for Angular 2.0.0-rc.5, see the [release notes](https://github.com/angular/angular/blob/master/CHANGELOG.md) !*


*Here is a presentation video (in French) of how it works, at the Toulouse Angular User Group in June 2016.*

<iframe width="560" height="315" src="https://www.youtube.com/embed/fD0uViFYVRA" frameborder="0" allowfullscreen></iframe>

# Summary of this document

* TOC
{:toc}

# How to build an application from the official archetype ?

Use the archetype which builds a fully working Angular2 + SpringBoot application :

{% highlight bash %}
mvn archetype:generate \
  -DarchetypeGroupId=fr.lteconsulting \
  -DarchetypeArtifactId=angular2-gwt.archetype \
  -DarchetypeVersion=1.3
{% endhighlight %}

**Note about the version** : *you can use either the latest release version like in the example above, or the next snapshot version (`1.4-SNAPSHOT` in this case) and benefit from the latest improvements and bug fixes. Features only available in the snapshot version are marked in the documentation. You can expect a new release few days after new features are tested and validated.*

Enter the desired *groupId*, *artifactId*, *version* and *package* and your application will be created in a sub directory named after the choosen *artifactId*.

Enter into this directory and build your application :

{% highlight bash %}
mvn clean install
{% endhighlight %}

Everything should work fine. Now you can run the built application with this :

{% highlight bash %}
java -jar target/YOUR_ARTIFACT_ID.jar
{% endhighlight %}

This will launch the SpringBoot application with a minimal client side Angular 2 controller. When entering [http://localhost:8080](http://localhost:8080) in your browser, a page should load and show a "Your application is working !" message. This is the sign that everything has been bootstraped correctly and that you can begin to work. There is also an `input` box, if you change its content the previous title will change too. That's shows that the two-way Angular data binding is working in your application !

# Development mode

During development, building the entire application is too time-consuming. In order to save your time, you can stop the java process we just launched and launch the SpringBoot development mode instead :

{% highlight bash %}
mvn spring-boot:run
{% endhighlight %}

This will allow to have hot swapping of classes on the server side (to a certain extent, see [documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-hotswapping.html)).

You may also want to hot reload client classes when you change them. In this case you need to start the GWT Super Dev Mode. But that has to be done in another terminal because we don't want to stop the Spring Boot server !

{% highlight bash %}
mvn gwt:run-codeserver
{% endhighlight %}

You can open the application project in your IDE (see the chapter on configuration problems that can happen), work on the code and refresh in the browser to get live updates.

# And now, what's next ?

Now that you have a working boostrapped application, you can either continue reading to get more details about what is inside the application you just created. Or you can directly jump to the [how-to](#howto) section.

You can also read the [cookbook](cookbook/) to learn advanced and deepened techniques.

Typically you will want to :

- add new Angular components to your application. Use the [Angular](https://angular.io/docs/ts/latest/guide/) documentation and the Angular2Boot reference [below](#howto).
- add new REST services on the backend with [SpringBoot](https://spring.io/guides/gs/rest-service/),

# Anatomy of your project

The project you have just built contains already several components. If you inspect the `pom.xml` file, you will see that the project inherits from `spring-boot-starter-parent` which is a way to bootstrap a SpringBoot application. The main dependencies your project has are :

- **spring-boot-starter-web**: just enough Spring Boot to serve static files and REST services.
- **gwt-user** and **gwt-dev**: GWT runtime and compiler, needed to compile the front side of the application.
- **angular2-gwt**: Angular 2 bindings and tools for GWT.

Regarding the project build, only two plugins are used: **gwt-maven-plugin** and **spring-boot-maven-plugin**.

Static resources are served from the `src/main/resources/static` directory. You can add more files there and they will be then accessible from the browser.

## index.html

Appart from all the `.js` files which are the required runtime scripts, the most important file in the `src/main/resources/static` directory is `index.html`. The browser loads this page to run your application and it contains everything needed to bootstrap it.

Let's inspect some parts of it. You will find a `<base href='/'>` tag which is needed by Angular to make the routing component work in the default mode. Note that you can remove it if you don't make use of the Router (we will see Routing details later) or if you use the router with the hash strategy on.

Then you will see a bunch of several `<link>` and `<script>` tag to load bootstrap.css (this can be removed) and javascript files. Those provide the Angular runtime which is called by your Java/GWT application.

As we talk about it, the javascript file generated from your Java code is imported by this line :

{% highlight html %}
<script src="angular2gwt/angular2gwt.nocache.js"></script>
{% endhighlight %}

This script bootstraps GWT's core and call your application entry point which is the `onModuleLoad` method of the `Application` class (which in turn bootstraps Angular with your Java component classes).

The last remarkable thing in this file is this line :

{% highlight html %}
<my-app class="container">Application is loading...</my-app>
{% endhighlight %}

This tag is rendered as a `<div>` until all scripts are loaded. During this period, the *"Application is loading"* message is displayed. Once the application is initialized, the Angular engine runs and replaces the `<my-app>` content by the one of the `ApplicationComponent`.

We will see that just after the `Application` class.

## Application.java

This class is the entrypoint of your application, meaning that its `onModuleLoad` method will be the first to be called and it will be called just after the GWT runtime has been initialized.

Angular2Boot entrypoint classes are very simple, the only thing you find is the Angular bootstrapping call :

{% highlight java %}
PlatformBrowserDynamic
  .platformBrowserDynamic()
  .bootstrapModule( ApplicationModule_AngularModule.getNgModulePrototype() );
{% endhighlight %}

The `bootstrapModule` method corresponds to the `bootstrapModule` function of Angular 2's PlatformBrowserDynamic module. In fact it is bound to Java through GWT's JsInterop, so behind the scene it is the same function that is called. The parameter of the `bootstrapModule` is the result of a call to the `ApplicationModule_AngularModule.getNgModulePrototype()` method. This method returns an Angular2-compatible constructor of the `ApplicationModule` class. The `ApplicationModule_AngularModule` class is automatically generated for you by Angular2Boot, as you will see later on.

So here we just say to Angular to start with the `ApplicationModule` as the root module of our application.

Just one word on the commented line of code that shows :

{% highlight java %}
/** You can uncomment that line to switch Angular to Production mode */
// Core.enableProdMode();
{% endhighlight %}

By default, until this line is uncommented Angular will work in debug mode : it will produce helper messages in the console and also add more runtime data associated with the components of the application (helping tools like `Augury` to work). If you deploy your application, don't forget to uncomment this line !

## ApplicationModule.java

[_NgModules_](https://angular.io/docs/ts/latest/guide/ngmodule.html) first appeared in the `rc5` version of Angular2. They allow you to split your application into big functional blocks and reuse them in other applications. They allow the Angular tool to perform more optimizations for you : the modules are statically analyzed by the _Angular Ahead of Time Compiler (ngc)_ and some pruning can happen.

Naturally, your java application too should declare a module. This is done in the `ApplicationModule` class. It reads :

{% highlight java %}
@NgModule(
	imports = { 
		BrowserModule.class, 
		FormsModule.class },
	declarations = ApplicationComponent.class,
	bootstrap = ApplicationComponent.class )
@JsType
public class ApplicationModule
{
}
{% endhighlight %}

It defines a module called `ApplicationModule` which imports the Angular `BrowserModule` and `FormsModule`. The first one brings everything that is needed to make Angular 2 work in the browser (it can run in other environments too) and the second one (_FromsModule_) brings useful usual form directives like `ngModel`. The module then declares the `ApplicationComponent` component so that it is avalaible to other modules if needed. And when the module is bootstrapped, it starts with the `ApplicationComponent` component which we will examine right now.

## ApplicationComponent.java

Here is the source code of the main component application :

{% highlight java %}
@Component(
	selector = "my-app",
	template = "<h1>{ {title}}</h1>" )
@JsType
public class ApplicationComponent
{
	@JsProperty
	private String title = "Your application is working !";
}
{% endhighlight %}

It doesn't do much but it already uses some of the main concepts of Angular : *Components*. Here are the very necessary **two** steps to create an Angular component out of a Java class :

- add the `@Component` annotation. This generates metadata about your component for Angular. Refer to the Angular documentation about the meaning of this annotation fields. Putting this annotation triggers the generation of the `ApplicationComponent_AngularComponent` java class.
- add the `@JsType` annotation. This tells the GWT compiler to make this class accessible to the Javascript world.

Here with `selector = "my-app"` we set this class to handle the `<my-app>` html tag. As you will have guessed, the content generated with this component will replace the content inside the `<my-app>` tag in the `index.html` page.

The content generated by the component is defined in the `template` field in the annotation (`template = "<h1>{ { title } }</h1>"`). It uses the double-brace syntax to refer to the `title` field's value.

The `title` field is declared like this:

{% highlight java %}
@JsProperty
private String title = "Your application is working !";
{% endhighlight %}

The `@JsProperty` tells the GWT compiler to make the field accesible to the javascript world and to Angular. It is necesarry here becaue the field is private but could have been omitted otherwise.

If this field's value change, Angular will know about and the DOM will be updated accordingly (given the change is made inside a zone, meaning inside an event handler and so on).

That's all for the front side of the application for the moment. Now let's inspect a backend class providing a very basic REST service.

## ApplicationController.java

This class contains two parts : one is the REST controller and the other is the main method, called by Spring Boot :

{% highlight java %}
@RestController
@EnableAutoConfiguration
public class ApplicationController
{
	@RequestMapping( "/test" )
	String test()
	{
		return "This is a test";
	}

	public static void main( String[] args ) throws Exception
	{
		SpringApplication.run( ApplicationController.class, args );
	}
}
{% endhighlight %}

<a name='howto'></a>

# How-to

In this how-to, we will go through functionalities provided by Angular2Boot by building a simple CRUD application step-by-step.

As in the tutorial in the Angular documentation, our main data object will be the **Hero** class. It represents a super-hero with an id, a name, a principal power and the name of its alter-ego.

This how-to follows most of the official Angular 2 tutorial, so it is strongly advised to [read it](https://angular.io/docs/ts/latest/tutorial/) also.

If you have a doubt about the source at any moment you can look at this [project](https://github.com/ltearno/angular2-gwt) sources.

## Create the `Hero` data object

**Note:** all the classes used in the front side of the application must reside under the `client` package.

Since we will use it everywhere in our application, let's create the DTO right now. You can add a `Hero` class written like this:

{% highlight java %}
@JsType
public class Hero
{
  @JsProperty private int id;

  @JsProperty private String name;

  @JsProperty private String power;

  @JsProperty private String alterEgo;

  public Hero( int id, String name, String power, String alterEgo )
  {
    this.id = id;
    this.name = name;
    this.power = power;
    this.alterEgo = alterEgo;
  }

  // getters
  public int getId() { ... }
  public String getName() { ... }
  public String getPower() { ... }
  public String getAlterEgo() { ... }
}
{% endhighlight %}

This class has nothing special appart from the annotations. It is annotated with `@JsType` which means that this Java class is accessible to the javascript world. This makes Angular able to watch over our data objects and update the views when needed. Its fields are annotated with `@JsProperty` so that they are accessible to the javascript world too. *Note that is they were not private, the annotation would be optionnal*.

## Add another component and use it inside ApplicationComponent

We will now add a Angular component which role is to display a list of heroes. This component will be called `HeroListComponent` and will handle the `<hero-list>` tag.

It's not our application which will instanciate the component but Angular itself so we have to give it all the information it depends on, in the form of java annotations.

### Component Java class

Create the `HeroListComponent` class, like in this example :

{% highlight java %}
@Component(
  selector = "hero-list",
  templateUrl = "hero-list.component.html" )
@JsType
public class HeroListComponent
{
  @JsProperty
  private Hero selectedHero;

  @JsProperty
  private JsArray<Hero> heroes;

  @JsConstructor
  public HeroListComponent()
  {
    heroes = JsArray.of(
      new Hero( 1, "Ignacio", "Procrastination", "Not found yet" ),
      new Hero( 2, "Bernardo", "Very clever", "Needs nobody" )
    );
  }

  @JsMethod
  protected void onSelect( Hero hero )
  {
    selectedHero = hero;
  }
}
{% endhighlight %}

The `@Component` annotation specifies that this component handles the `<hero-list>` tag and that its associated html template is located in the **hero-list.component.html**.

The `@JsType`, `@JsProperty` and `@JsMethod` allow Angular to access the controller fields and methods. They will be referenced in the HTML template associated to the component (*hero-list.component.html*).

The constructor simply makes up a hard-coded list of Heroes. Later on will will fetch them from the server through a REST service...

### Component template file

Now we are going to write the HTML template file for our new component. Create a `hero-list.component.html` file in the `src/main/resources/static/` directory, with the following content :

{% highlight html %}
<div>
  <ul>
    <li *ngFor="let hero of heroes" (click)="onSelect(hero)">
      <span class="badge">{ {hero.id}}</span>
      { {hero.name}}
    </li>
  </ul>
</div>
<div *ngIf="selectedHero">
  <h2>{ {selectedHero.name | uppercase}}</h2>
  <div class="row">
    <div class="col-xs-3">Name</div>
    <div class="col-xs-9">{ { selectedHero.name }}</div>
  </div>
  <div class="row">
    <div class="col-xs-3">Alter Ego</div>
    <div class="col-xs-9">{ { selectedHero.alterEgo }}</div>
  </div>
  <div class="row">
    <div class="col-xs-3">Power</div>
    <div class="col-xs-9">{ { selectedHero.power }}</div>
  </div>
</div>
{% endhighlight %}

This template is very simple and is made of two parts. The first one displays a list of Heroes and the second part displays a quick information about the currently selected hero.

The first part uses the `*ngFor` Angular built-in directive to iterate over the Heroes list (the `heroes` field) in the `HeroListComponent` class. For each hero present in the list, a `<li>` tag will be generated, the *id* and *name* of the hero is displayed. And when the user clicks on a hero, the components' `onSelect` method will be called.

When `onSelect` is called, the Java code just updates the `selectedHero` field. And since Angular watches that, it will impact the rendering of the second part of the template.

The `*ngIf="selectedHero"` built-in directive allows to render this part only if `selectedHero` is not null. In this case, its name is displayed (piped to the Uppercase transformer, built-in with Angular).

So we are finished for the moment with this component. Next thing to do is to integrate it with our application.

### Integrating `HeroListComponent` into `ApplicationComponent`

As you know, an Angular application is a cycle-less directed graph of components (this is a big difference with Angular 1). For now our application only contains one component in the graph, which is `ApplicationComponent`. We need to tell to Angular that we want to *inject* the `HeroListComponent` inside.

In order to do that, we need to make the following changes in the `ApplicationComponent` class :

- reference the `HeroListComponent.class` in the directives accessible from the `ApplicationComponent` class.
- change the template to include the `<hero-list></hero-list>` tag. This tag will be resolved by Angular as what is rendered by the `HeroListComponent` component.

The annotation on `ApplicationComponent` should now look like this (and nothing else is modified in this class) :

{% highlight java %}
@Component(
  selector = "my-app",
  template = "<h1>{ {title}}</h1><hero-list></hero-list>",
  directives = { HeroListComponent.class } )
{% endhighlight %}

### Testing what we have done

Now to run the project, open a terminal in the project folder and type :

{% highlight bash %}
mvn spring-boot:run
{% endhighlight %}

This starts the java backend server (Spring Boot). Open another terminal and launch the GWT SuperDevMode with this command :

{% highlight bash %}
mvn gwt:run-codeserver
{% endhighlight %}

When both are launched, you can browse `http://localhost:8080` in your preferred browser (a modern one is required). You will see the "Your application is working" message plus the list of the two heroes we have hard coded in `HeroListComponent`. If you click on one hero, a little table should present the selected hero's summary, as illustrated here :

![Application with the HeroListComponent](first-step.png)

Now you can play around with your code, changes should immediately apply when you refresh your browser's page.

### Adding a bit of CSS

Adding encapsulated CSS is very easy with Angular. Here we are going to make the selected hero highlighted.

First in the `HeroListComponent`, set the `styleUrls` field of the `@Component` annotation and create the corresponding CSS file in the `src/main/resources/static` directory.

The annotation now looks like :

{% highlight java %}
@Component(
  selector = "my-app",
  template = "<h1>{ {title}}</h1><hero-list></hero-list>",
  styleUrls = { "hero-list.component.css" },
  directives = { HeroListComponent.class } )
{% endhighlight %}

As you see, we added `styleUrls = { "hero-list.component.css" }` in the annotation.

Now, create the **hero-list.component.css** file and insert the following content :

{% highlight css%}
.selected {
	background-color: #CFD8DC !important;
	color: white;
}
{% endhighlight %}

Now we will bind the selected hero to the `.selected` CSS class. This is done by editing the component template file (*hero-list.component.html*). Only the `<li>` tag changes and we add it `[class.selected]="hero === selectedHero"`. The beginning of the file should look like this :

{% highlight html %}
<div>
  <ul class="heroes">
    <li *ngFor="let hero of heroes" [class.selected]="hero === selectedHero" (click)="onSelect(hero)"><span class="badge">{ {hero.id}}</span> { {hero.name}}</li>
  </ul>
</div>
{% endhighlight %}

Now when a generated `<li>` corresponds to the hero in the `selectedHero` field of our component, it will be highlighted.

Refresh your browser and check that it is the case.

## Adding a form component to edit a hero

Now we are going to examine how components can talk to each other and particularly how we can send a particular hero to a component which will make editing the hero possible.

### Creating the HeroFormComponent

We create the HeroForm editing component just like we did before. Create the `HeroFormComponent` as described here :

{% highlight java %}
@Component(
  selector = "hero-form",
  templateUrl = "hero-form.component.html" )
@JsType
public class HeroFormComponent
{
  @JsProperty
  private JsArray<String> powers = JsArray.of(
    "Really Smart",
    "Super Flexible",
    "Weather Changer",
    "Do nothing" );

  @Input
  @JsProperty
  private Hero model;
}
{% endhighlight %}

**Note** that the `model` field is annotated with the `@Input` annotation. This is **very important** for Angular to know that it will have to fill the value for you (the value will come as the selected hero in the HeroListComponent). If you don't put `@Input` the thing won't work because Angular will not know about this component inputs.

The `powers` field contains all the possible powers for a super-hero. And the `model` field will reference the currently edited hero.

Next, write the component template (in `src/main/resources/static/hero-form.component.html`) :

{% highlight html %}
<div class="container">
  <form *ngIf="model != null" #heroForm="ngForm">
    <h1>{ {model.name}} edition form</h1>
    <div class="form-group">
      <label for="name">Name</label> <input type="text" class="form-control" required
      [(ngModel)]="model.name" ngControl="name" #name="ngForm">
      <div [hidden]="name.valid || name.pristine" class="alert alert-danger">Name is required</div>
    </div>
    <div class="form-group">
      <label for="alterEgo">Alter Ego</label> <input type="text" class="form-control" [(ngModel)]="model.alterEgo" ngControl="alterEgo">
    </div>
    <div class="form-group">
      <label for="power">Hero Power</label> <select class="form-control" required
      [(ngModel)]="model.power" ngControl="power" #power="ngForm">
      <option *ngFor="let p of powers" [value]="p">{ {p}}</option>
      </select>
      <div [hidden]="power.valid || power.pristine" class="alert alert-danger">Power is required</div>
    </div>
  </form>
  <div *ngIf="!model">No hero to edit !</div>
  <button (click)="goBack()" class="btn btn-default">Back</button>
</div>
{% endhighlight %}

For detailed information about how forms work in Angular 2, please see this [page](https://angular.io/docs/ts/latest/guide/forms.html).

### Wiring-up

Now we are going to use the form component in conjonction with the hero list, so that when the user selectes a hero, the corresponding form will be displayed.

Edit the `hero-list.component.html` file and add `<hero-form [model]="selectedHero"></hero-form>` at the end so that it looks like this :

{% highlight html %}
<div>
  <ul class="heroes">
    <li *ngFor="let hero of heroes" [class.selected]="hero === selectedHero" (click)="onSelect(hero)"><span class="badge">{ {hero.id}}</span> { {hero.name}}</li>
  </ul>
</div>
<div *ngIf="selectedHero">
  <h2>{ {selectedHero.name | uppercase}}</h2>

  <div class="row">
    <div class="col-xs-3">Name</div>
    <div class="col-xs-9">{ { selectedHero.name }}</div>
  </div>
  <div class="row">
    <div class="col-xs-3">Alter Ego</div>
    <div class="col-xs-9">{ { selectedHero.alterEgo }}</div>
  </div>
  <div class="row">
    <div class="col-xs-3">Power</div>
    <div class="col-xs-9">{ { selectedHero.power }}</div>
  </div>

  <hero-form [model]="selectedHero"></hero-form>
</div>
{% endhighlight %}

This instructs Angular to inject the HeroForm component and to bind its `model` field to the `selectedHero` field from the HeroListComponent.

But to be able to inject our HeroFormComponent, Angular must know about it. For that we add the `HeroFormComponent.class` in the list of directives available to the HeroListComponent :

{% highlight java %}
@Component(
  selector = "hero-list",
  templateUrl = "hero-list.component.html",
  styleUrls = { "hero-list.component.css" },
  directives = { HeroFormComponent.class } )
@JsType
public class HeroListComponent
{
  ... unchanged ...
}
{% endhighlight %}

Refresh your browser and... You should be able to edit the Heroes !

![Editing a Hero in the HeroFormComponent](first-step.gif)

## Add routing to our application

*You can also refer to the official Angular [documentation](https://angular.io/docs/ts/latest/guide/router.html) because Angular2Boot uses the same annotation names.*

Now let's add routing in our application. As we add more and more components, it gets useful to base ourselves on Angular routing to help us organize things.

Before anything, we want to make sure that the first tag after the `<head>` in `index.html` is a **`<base href="/">`** tag. If not, add it so the routing mechanism works.

We are going to delegate the routing process in the `ApplicationComponent` component. Let's modify its template and instead of the `<hero-list>` tag, write `<router-outlet>`. This element will be the placeholder used by the routing service to display the right component.

Next we remove the reference to the `HeroListComponent.class` directive because we will provide it when defining the routes.

The Angular routing components need to be injected in our application for the whole thing to work, so we will reference `RouterDirectives.class` as a directive and `RouterProviders.class` as a service provider. Those classes are part of Angular2Boot and are located in the `fr.lteconsulting.angular2gwt.client.interop.angular` package.

The template will look like this :

{% highlight java %}
@Component(
  selector = "my-app",
  template = "<h1>{ {title}}</h1><router-outlet></router-outlet>",
  directives = { RouterDirectives.class },
  providers = { RouterProviders.class } )
@JsType
public class ApplicationComponent
{
  ...
}
{% endhighlight %}

What we have done is to enable the router mechanism that comes with Angular in our application. Now we need to feed it with our routing recommandations. That is done with the `@RouteConfig` annotation. For each route we want to define we have to add an anotation like this (*note that Java allows multiple annotation instances of the same class to be applied on the same language element*).

The `@RouteConfig` annotations has the following parameters :

- **path** is the relative url associated with the component that is routed,
- **name** is the name of this route, used when you link from one route to another,
- **component** is the class of the component associated to this route
- **useAsDefault** can be set to `true` to use this route as default.

We will set the `HeroListComponent` as the default root route and the `HeroFormComponent` as another route with a parameter : the id of the edited hero. This is done simply and the result looks like this :

{% highlight java %}
@Component(
  selector = "my-app",
  template = "<h1>{ {title}}</h1><router-outlet></router-outlet>",
  directives = { HeroListComponent.class, RouterDirectives.class },
  providers = { RouterProviders.class } )
@RouteConfig(
  path= "/",
  name= "Heroes",
  useAsDefault= true,
  component= HeroListComponent.class )
@RouteConfig(
  path= "/hero/:id",
  name= "Hero",
  component= HeroFormComponent.class )
@JsType
public class ApplicationComponent
{
	...
}
{% endhighlight %}

### Calling a route

Now we are going to update the `HeroListComponent` because it does not need to embbed `HeroFormComponent` anymore. The Angular router will do that for us when the correct link will be activated. In order to do that, just remove the `<hero-form [model]="selectedHero"></hero-form>` tag from the `hero-list.component.html` file.

And in the `HeroListComponent` java code, instead of just marking the hero as selected, we will instruct the router to go to a new route. There are several steps involved :

- injecting the router in the `HeroListComponent`,
- store the reference to the router for further use,
- ask the router to switch its route when the user selects a hero.

Injecting the router is really easy since we already have provided it in the `ApplicationComponent` component (which is a parent component of `HeroListComponent`). We just add a `Router router` parameter in the constructor, a private `Router router` field and store the instance that is passed through the constructor in the field. This is how dependency injection works in Angular programs !

Now in the `onSelect` method, we want to change the current route. This is done by adding this line :

{%  highlight java %}
router.navigate( JsArray.of( "Hero", new HeroLink( hero.getId() ) ) );
{% endhighlight %}

There is a lot here ! `router.navigate(...)` is the method to go to a new route. It takes as `JsArray` as a parameter, which is just a wrapper to the native javascript array. The first element of the array is the route name and the second one is a dto carrying the hero id.

Here is the `HeroLink` class, a very simple `@JsType` pojo :

{%  highlight java %}
@JsType
public class HeroLink {
  @JsProperty int id;

  public HeroLink( int id ) {
    this.id = id;
  }
}
{% endhighlight %}

*Note*: you can also call a route link from a template by using this syntax : `[routerLink]="[name]"`.

### Receiving a route parameter

Now we need to change the `HeroFormComponent` component so that instead of receiving the model through an `@Input` field, it will receive the hero through the router.

Just as we injected the `Router` service just before, we are going to inject and store a reference to the `RouterParams` in the `HeroFormComponent`'s constructor.

Then we take advantage of the Angular component [life cycle](https://angular.io/docs/ts/latest/guide/lifecycle-hooks.html) and use `RouterParams` reference inside a `ngOnInit` method. In this method we extract the hero id, retreive the corresponding `Hero` instance from the `HeroService` (that we will see right after), and update our `model` field.

The `HeroFormComponent` now looks like this :

{% highlight java %}
@Component( ... )
@JsType
public class HeroFormComponent
{
  private RouteParams routeParams;

  private JsArray<String> powers = JsArray.of( ... );

  // no need to use @Input anymore
  @JsProperty private Hero model;

  @JsConstructor
  public HeroFormComponent( RouteParams routeParams )
  {
    this.routeParams = routeParams;
  }

  @JsMethod
  private void ngOnInit()
  {
    Object oId = routeParams.get( "id" );
    int id = Integer.parseInt( String.valueOf( oId ) );
    // we will see that just after
    Hero hero = heroService.getHero( id );
    this.model = hero;
  }
}
{% endhighlight %}

### Creating and injecting a service

Instead of having the collection of heroes in the `HeroListComponent`, we are going to create an Angular service to manage it so that other components like the `HeroFormComponent` can have access to it (through injection).

Let's create a very basic `HeroService` class :

{% highlight java %}
@JsType
public class HeroService
{
  private JsArray<Hero> heroes;

  public HeroService()
  {
    heroes = JsArray.of( new Hero( 1, "Ignacio", "Procrastination", "Not found yet" ), new Hero( 2, "Bernardo", "Very clever", "Needs nobody" ) );
  }

  public JsArray<Hero> getHeroes()
  {
    return heroes;
  }

  // we will soon be able to use streams
  // so this code will be much shorter !
  public Hero getHero( int id )
  {
    for( Hero hero : heroes.iterate() )
    if( hero.getId() == id )
      return hero;
    return null;
  }
}
{% endhighlight %}

This is again a very simple class, with a `@JsType` annotation because it needs to be injected by Angular. The `heroes` field has no `@JsProperty` annotation because it does not need to be accessed by Angular, so it will be optimized by the GWT compiler.

The `heroes` field represents our heroes catalog. It is hard coded in the constructor and has a getter for components to get acces to. Later on we will get the heroes list from the server through a json REST service (using Promises together with our `Hero` dto).

Now let's make this service injectable and accessible to other components. Since all components might be interested in getting th hero collection, we will provide it at the application level, by adding it in the `providers` field of the `@Component` annotation on the `ApplicationComponent` class.

{% highlight java %}
@Component(
  selector = "my-app",
  template = "<h1>{ {title}}</h1><router-outlet></router-outlet>",
  directives = { HeroListComponent.class, RouterDirectives.class },
  // HERE WE ADD THE HeroService.class :
  providers = { RouterProviders.class, HeroService.class } )
{% endhighlight %}

We then fix the `HeroListComponent` to get the `HeroService` injected and to get the hero list from it. In order to get that done, we simply add `HeroService heroService` in the constructor and Angular will inject that for us. Here is what it looks like now :

{% highlight java %}
@Component( selector = "hero-list", templateUrl = "hero-list.component.html", styleUrls = { "hero-list.component.css" }, directives = { HeroFormComponent.class } )
@JsType
public class HeroListComponent
{
  private HeroService heroService;
  private Router router;

  @JsProperty
  private Hero selectedHero;

  @JsProperty
  private JsArray<Hero> heroes;

  @JsConstructor
  public HeroListComponent( HeroService heroService, Router router )
  {
    this.heroService = heroService;
    this.router = router;
    heroes = heroService.getHeroes();
  }

  @JsMethod
  protected void onSelect( Hero hero )
  {
    selectedHero = hero;

    router.navigate( JsArray.of( "Hero", new HeroLink( hero.getId() ) ) );
  }
}
{% endhighlight %}

Let's do that in the `HeroFormComponent` too so that we get the hero from the service. We add the service in the constructor of the component and use it in the `ngOnInit` method to get the `Hero` from the id. Here is the new `HeroFormComponent` class content:

{% highlight java %}
@Component( selector = "hero-form", templateUrl = "hero-form.component.html" )
@JsType
public class HeroFormComponent
{
  private HeroService heroService;
  private RouteParams routeParams;

  @JsProperty
  private JsArray<String> powers = JsArray.of(
    "Really Smart",
    "Super Flexible",
    "Super Hot" );

  @JsProperty private Hero model;

  @JsConstructor
  public HeroFormComponent( HeroService heroService, RouteParams routeParams )
  {
    this.heroService = heroService;
    this.routeParams = routeParams;
  }

  @JsMethod
  private void ngOnInit()
  {
    Object oId = routeParams.get( "id" );
    int id = Integer.parseInt( String.valueOf( oId ) );
    Hero hero = heroService.getHero( id );
    this.model = hero;
  }

  // we add this method to handle the click on
  // the 'back' button in the template
  @JsMethod
  private void goBack()
  {
    back();
  }

  // this is the JsInterop way to declare
  // the 'window.history.back()' javascript function
  @JsMethod( namespace = "window.history", name = "back" )
  private static native void back();
}
{% endhighlight %}

Congratulations, at this point your application should work and you have the heroes list contained in a centralized service !

Now what about getting the data from the server and add CRUD operations ?

## Using a REST service instead of hard-coded data (using Promises)

To implement client-server communication we need to do two things : create a Java class in the server package of the application (correctly annotated for Spring Boot), and use `XMLHttpRequest` together with **promises** to get the data in the front side, all of which is nicely wrapped by the Angular2Boot library.

As you will see the `Hero` class that we wrote previously will also serve us on the back-end side.

### Creating a REST service

In fact there is already a REST service in the code generated by the artifact so let's just improve it, it is the `ApplicationController` class. We remove the `test()` method and replace it with a `getHeroes()` method which returns a `List<Hero>`. The return value will automatically be converted to JSON by Spring Boot and will be parsed very easily on the front-end side.

Here is the `ApplicationController` class content :

{% highlight java %}
@RestController
@EnableAutoConfiguration
public class ApplicationController
{
  // a modifiable list so we can add the CRUD methods later on
  private List<Hero> heroes = new ArrayList<>( Arrays.asList(
    new Hero( 1, "Ignacio", "Procrastination", "Not found yet" ),
    new Hero( 2, "Bernardo", "Very clever", "Needs nobody" ) ) );

  // will be converted to JSON
  @RequestMapping( "/heroes" )
  List<Hero> getHeroes()
  {
    return heroes;
  }

  public static void main( String[] args ) throws Exception
  {
    SpringApplication.run( ApplicationController.class, args );
  }
}
{% endhighlight %}

*At this point you may need to restart the `mvn spring-boot:run` command. It is occasionnally necessary when method signatures are changed.*

If you type **http://localhost:8080/heroes** in your browser, you should get the list of heroes, JSON encoded.

### Using `XMLHttpRequest` and `Promises`

Now let's use this service in the application instead of having the list hard-coded in the front.

In the `HeroService` class, we will not return a `JsArray<Hero>` anymore but a `Promise<JsArray<Hero>, String>`. This is sthe standard way of representing asynchronous operations in javascript. This represents the promise of a result (typed with `JsArray<Hero>`) later on, or an error (typed as a `String` if the operation failed). It is possible to chain asynchronous operations by calling the `then(...)` function with a callback which will receive the promise's result (or error if any).

We will keep a reference to the promise in the component because successive calls to the same promise give the same result, and so it holds the result in a kind of way.

#### The dto problem

There is a problem with how javascript works and it prevents to directly use the `Hero` class when doing the ajax query. Because the dto that is received from the ajax call has the `Object` prototype, casting it to a `Hero` in the java code fails completely. Hopefully, Angular2Boot provides a method to convert any javascript object into a `@JsType` object belonging to the java world. This method is `sendRequestAndConvertDtoList` and its sisters and the next code extract will show how to use it.

Here is how the `HeroService` looks like now :

{% highlight java %}
@JsType
public class HeroService
{
  private Promise<JsArray<Hero>, String> heroes;

  public HeroService()
  {
    // initialize the Promise as soon as the service is created
    heroes = new Promise<>( ( resolver, rejecter ) -> {
      // send an ajax request to /heroes url
      Ajax.sendRequestAndConvertDtoList( "GET", "heroes", Hero.class )
        .then(
          resolver::resolve, // resolve the promise with the request reply
          error -> GWT.log( "error getting heroes " + error ) );
    } );
  }

  public Promise<JsArray<Hero>, String> getHeroes()
  {
    return heroes;
  }

  public Promise<Hero, String> getHero( int id )
  {
    return new Promise<>( ( resolver, rejecter ) -> {
      heroes.then( heroes -> {
        for( Hero hero : heroes.iterate() )
          if( hero.getId() == id )
          {
          resolver.resolve( hero );
          return;
          }
        rejecter.reject( "no hero found" );
      }, null );
    } );
  }
}
{% endhighlight %}

This is quite a lot maybe !

First thing is the `Promise<JsArray<Hero>, String> heroes` field holding the promise to the hero list. We hold it so we can reuse it later on, to find a specific hero for instance.

#### Making a promise

The next interesting lines are in the constructor. It is written in java 8 with method reference so it is kind of short for what it does. We first initialize the `heroes` field to a Promise in which we send an ajax request with the **"GET"** method to the **"heroes"** url. Since we use the `sendRequestAndConvertDtoList()` helper method which converts javascript objects to `@JsType` objects, we need to provide it the class of the destination class. This is the `Hero.class` and is the third parameter of this method.

When the ajax call finishes, the `then()` method parameters are called. If it is a success, the call is delegated to the `resolver::resolve` method reference. This means that the ajax result is directly passed to the code which called `getHeroes()`. If there is an error, it logged in the console.

#### Taking a promise

Now the `getHero( int id )` method implementation has to rely somehow on the `getHeroes()` method so it has to return a Promise too.

For this it creates a Promise in which it takes the `heroes` promise, finds the searched hero and resolves its own promise with this hero (with the `resolver` variable). If it does not find one, the promise is rejected with the `rejecter` variable.

The `HeroFormComponent.ngOnInit` also needs to be refactored to use the promise. It now looks like this :

{% highlight java %}
@JsMethod
  private void ngOnInit()
  {
    Object oId = routeParams.get( "id" );
    int id = Integer.parseInt( String.valueOf( oId ) );
    heroService.getHero( id ).then(
      hero -> this.model = hero,
      error -> this.model = null );
  }
{% endhighlight %}

The last line in the method takes the hero promise and affects the model to it. In case of error, a `null` hero is affected.

The `HeroListComponent` constructor should also be modified to take the Promise in account :

{% highlight java %}
@JsConstructor
public HeroListComponent( HeroService heroService, Router router )
{
  this.heroService = heroService;
  this.router = router;
  heroService.getHeroes().then( list -> heroes = list, error -> heroes = null );
}
{% endhighlight %}

At this point, you can refresh your browser and you should see that the list of heroes now comes from the server (*maybe you have to restart Spring Boot or GWT SuperDevMode sometimes...*).

## Add the CRUD operations on the hero list

Now that we get the list from the server, we will add the other basic operations which are *add* and *remove*.

### Service methods

First since we implement the *add* and *remove* method in the `HeroService`class. Here are the two methods we add :

{% highlight java %}
public Promise<Hero, String> addHero()
{
  return new Promise<>( ( resolver, rejecter ) -> {
    Ajax.sendRequestAndConvertDto( "GET", "addHero", Hero.class ).then( hero -> {
      heroes.then( heroes -> {
        heroes.push( hero );
        resolver.resolve( hero );
      }, null );
    }, error -> GWT.log( "error creating hero " + error ) );
  } );
}

// avalaible only with the 1.2-SNAPSHOT archetype and 1.1-SNAPSHOT angular2-gwt
public Promise<Hero, String> updateHero( int heroId )
{
  return new Promise<Hero, String>( ( resolver, rejecter ) -> {
    getHero( heroId ).then( hero -> {
      GWT.log( "hero sent " + hero );
      // see the Hero dto sent in the ajax request
      Ajax.sendRequestAndConvertDto( "POST", "updateHero", hero, Hero.class )
        .then(
          heroConfirmed -> resolver.resolve( heroConfirmed ),
          error -> GWT.log( "error updating hero " + error )
        );
    }, error -> GWT.log( "error updating hero " + error ) );
  } );
}

public Promise<Boolean, String> removeHero( int id )
{
  return new Promise<>( ( resolver, rejecter ) -> {
    Ajax.sendRequest( "GET", "deleteHero" ).then( text -> {
      heroes.then( heroes -> {
        getHero( id ).then( hero -> {
          int index = heroes.indexOf( hero );
          if( index >= 0 )
          heroes.splice( index, 1 );
          resolver.resolve( true );
        }, null );
      }, null );
    }, error -> GWT.log( "error deleting hero " + error ) );
  } );
}
{% endhighlight %}

You see that both operations rely on the `heroes` promise. This seems quite a lot of code at the beginning, but the good thing is that when we refresh the heroes promise, all other operations will work with the updated data.

On the server side, we add the three methods responding to the `addHero`, `updateHero` (*only available with version 1.2-SNAPSHOT of the archetype*) and `deleteHero` urls. Those are very simple since we don't persist data. Here is the server methods we add to the `ApplicationController` class :

{% highlight java %}
@RequestMapping( "/addHero" )
Hero addHero()
{
  Hero hero = new Hero( (int) (1000 * Math.random()), "New comer", "None yet", "" );
  heroes.add( hero );
  return hero;
}

// avalaible only with the 1.2-SNAPSHOT archetype and 1.1-SNAPSHOT angular2-gwt
@RequestMapping( value = "/updateHero", method = RequestMethod.POST )
Hero updateHero( @RequestBody Hero hero )
{
  if( hero == null )
    return null;

  heroes.stream().filter( h -> h.getId() == hero.getId() ).findFirst().ifPresent( h -> {
    int index = heroes.indexOf( h );
    heroes.remove( index );
    heroes.add( index, hero );
  } );

  return hero;
}

@RequestMapping( "/deleteHero" )
Boolean deleteHero( int id )
{
  heroes.stream()
    .filter( hero -> hero.getId() == id )
    .findFirst()
    .ifPresent(
      hero -> heroes.remove( hero )
    );
  return true;
}
{% endhighlight %}

Those three methods just change the server's list and return the added/updated `Hero` or true when a hero is deleted. The communication layer is handled by Spring Boot and the browser !

### *Add* and *Remove* buttons

The `HeroListComponent` will trigger those operations through two buttons, one to add a new Hero and one to remove an existing hero.

First we add the buttons in the `HeroListTemplate. The *add* button is put in the list div and calls the `newHero()` method and the delete button is put into the form and calls the `delete` method. The delete method is called with two parameters : the `selectedHero` and the `$event` which represents the dom event corresponding to the click. This makes `hero-list.component.html` looks like this :

{% highlight java %}
<div>
  <ul class="heroes">
    <li *ngFor="let hero of heroes" [class.selected]="hero===selectedHero" (click)="onSelect(hero)">
      <span class="badge">{ {hero.id}}</span> { {hero.name}}
    </li>
  </ul>

  <button (click)="newHero()" class="btn btn-default" >New Hero</button>
</div>
<div *ngIf="selectedHero">
  <h2>{ {selectedHero.name | uppercase}}</h2>

  <div class="row">
    <div class="col-xs-3">Name</div>
    <div class="col-xs-9">{ { selectedHero.name }}</div>
  </div>
  <div class="row">
    <div class="col-xs-3">Alter Ego</div>
    <div class="col-xs-9">{ { selectedHero.alterEgo }}</div>
  </div>
  <div class="row">
    <div class="col-xs-3">Power</div>
    <div class="col-xs-9">{ { selectedHero.power }}</div>
  </div>

  <button (click)="deleteHero(selectedHero, $event)" class="btn btn-default">
    Delete { {selectedHero.name}}
  </button>
</div>
{% endhighlight %}

In the `HeroListComponent` class, we implement the `newHero()` and `deleteHero(...)` methods so that they call the service. All the data is updated automatically because everything happens inside the Angular zone...

{% highlight java %}
@JsMethod
private void newHero()
{
  heroService.addHero();
}

@JsMethod
void deleteHero( Hero hero )
{
  if( hero != null )
  heroService.removeHero( hero.getId() );
}
{% endhighlight %}

The last thing is to add the *save* button in the `HeroFormComponent` so that the user can trigger an hero update when he finishes editing one. Let's add that in the template :

{% highlight html %}
<button (click)="save()" class="btn btn-default">Save</button>
{% endhighlight %}

This button will call the `save()` method of our component, so we have to implement it :

{% highlight java %}
@JsMethod
private void save()
{
  if( model != null )
    heroService.updateHero( model.getId() );
}
{% endhighlight %}

Note that there is a last little thing to change in the `Hero` class. Since objects of this class must now be serialized from JSON by Spring Boot when the hero to be updated is received by the REST service, we need to add a default constructor to the `Hero` class. The side effect of this is that we need to annotate constructors differently because of GWT's *JsInterop* system. Note that the constructor with parameters first delegate to the `@JsConstructor` constructor. That's a constraint brought by *JsInterop*.

Here is what we changed in the `Hero` class :

{% highlight java %}
@JsType
public class Hero
{
  // internal fields have not changed
  ...

  // constructor for the SpringBoot JSON to Java serialization
  @JsConstructor
  public Hero()
  {
  }

  // constructor to create heroes on the server side
  @JsIgnore
  public Hero( int id, String name, String power, String alterEgo )
  {
    this();

    this.id = id;
    this.name = name;
    this.power = power;
    this.alterEgo = alterEgo;
  }

  // getters have not changed
  ...
}
{% endhighlight %}

With this, you have a little application implementing the CRUD operations on a Java Dto. The `Hero` dto class is used both on the server and client side and require no special serialization service since all the communication happens in the JSON format. The client uses modern javascript paradigms like `Promises` and *ajax* requests. The java 8 lambda expressions help to keep the code relatively small when handling asynchronous operations. And since the code is written in pure Java, one benefits from the great tooling (IDEs, build tools, ...) and the ecosystem.

*At this point you may need to restart the `mvn spring-boot:run` command since it can get a bit confused with the new methods and annotations...*

The live reload of the code is also very appreciable and brings a comfortable development experience.

## Leaving and returning from Angular zones

When you work with asynchronous operations, it could be that you escape from Angular [zones](https://angular.io/docs/js/latest/api/core/NgZone-class.html). If that is so, you can inject the current zone in the component constructor and use the `runOutsideAngular` and `run` methods to leave and return to the Angular zone. Put simply, if you make an asynchronous call, alter a field and have no update in the view, calling `NgZone.run( () -> { /* your code here */ } );` can help. Here is how to use the zone in a component :

{% highlight java %}
@JsType
@Component( ... )
public class MyComponent
{
  @JsProperty String message;

  private NgZone zone;
  public MyComponent( NgZone zone )
  {
    this.zone = zone;
  }

  // called on a click so that Angular checks
  // for updates in the component data
  @JsMethod
  private void click()
  {
    // do something asynchronous other than
    // setTimeout, setInterval and so on...
    doSomethingAsynchrosous( () -> zone.run( () -> message = "update !" ) );
  }
}
{% endhighlight %}

## Component interaction

This page has moved to its own [place](component-interaction/).

# Using with another backend technology

While *Spring Boot* provides a very good platform to host your angular application, you may want to use Angular2Boot with another backend like *JBoss*, *Tomcat*, *Spark* or *Jetty*.

This is completely possible and documentation and archetypes will be provided soon.

It is even possible to use Angular2Boot only for the front-end and get on with a non-Java technology on the server...

# Motivation

Angular 2 has been wrapped around Typescript and Dart because they allow elegant metadata specification through annotations. Java also has annotations and is a language of choice when it comes to build robust applications.

Angular 2 is a very good framework to build industry grade web applications. It is based on several years of experience with Angular 1 and is now mature and answers most of the developper needs.

GWT provides (optimized) translation from Java to Javascript and bindings to Angular 2. With the latest GWT version (2.8), the new *JsInterop* specification allows easy interoperability with Javascript. And the SuperDevMode has been improved so that recompile time is greatly reduced, providing a development experience similar to what a Javascript developper can expect.

Add the simplicity of Spring Boot for server side application development and you get **Angular2Boot** !

# IDE Configuration

## Eclipse

You need to install `m2e-apt` component. Otherwise, everything should work out of the box by importing the maven project.

## IntelliJ

*Documentation not yet written*

## Version matrix

Angular version | `angular2-gwt.archetype` versions | `angular2-gwt` versions
----------------|-----------------------------------|--------------------------
`2 beta 17`     | `1.1`, `1.2-SNAPSHOT`             | `1.0`, `1.1-SNAPSHOT`
`2 beta 16`     | `1.0`                             | `1.0`

## Troubleshooting

Sometimes when you add too much classes like this, with new annotations, the GWT SuperDevMode gets confused and nothing works. This is a limitation caused by GWT. One way to circumvent this is to clear the project and build it again, but it's time consumming. Another way of doing so it to quit the GWT SuperDevMode and to run the `rm -rf target/gwt-unitCache/` command. This will clear GWT compilation cache. You can then restart the GWT SuperDevMode (`mvn gwt:run-codeserver`) and things should work fine again.

# Contact

If you have any question or remark, feel free to use the comment zone at the bottom of the page or to email directly to <contact@lteconsulting.fr>.

License. [Dual commercial / GPLv3](licensing)

# Useful links

- [Angular 2 Official website](https://angular.io/)
- [Spring Boot Official website](http://projects.spring.io/spring-boot/)
- [Learn Angular 2](http://learnangular2.com/)
- [Sample Angular2Boot application](https://github.com/ltearno/angular2-gwt)