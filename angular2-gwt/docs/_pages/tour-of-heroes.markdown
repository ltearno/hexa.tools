---
layout: page
title: Tour of Heroes Tutorial
permalink: /tutorial/
---
In this how-to, we will go through functionalities provided by Angular2Boot by building a simple application step-by-step.

This how-to follows most of the official Angular 2 tutorial, so it is strongly advised to [read it](https://angular.io/docs/ts/latest/tutorial/) also. The official tutorial introduction says :

> Our grand plan is to build an app to help a staffing agency manage its stable of heroes. Even heroes need to find work.
> [...] What we do build will have many of the features we expect to find in a full-blown, data-driven application: acquiring and displaying a list of heroes, editing a selected hero's detail, and navigating among different views of heroic data.

> The Tour of Heroes covers the core fundamentals of Angular. We’ll use built-in directives to show/hide elements and display lists of hero data. We’ll create a component to display hero details and another to show an array of heroes. We'll use one-way data binding for read-only data. We'll add editable fields to update a model with two-way data binding. We'll bind component methods to user events like key strokes and clicks. We’ll learn to select a hero from a master list and edit that hero in the details view. We'll format data with pipes. We'll create a shared service to assemble our heroes. And we'll use routing to navigate among different views and their components.

> We’ll learn enough core Angular to get started and gain confidence that Angular can do whatever we need it to do. We'll be covering a lot of ground at an introductory level but we’ll find plenty of links to chapters with greater depth.

If you have a doubt about the source at any moment you can look at this [project](https://github.com/ltearno/hexa.tools/tree/master/angular2-gwt) sources.

# Let's get started

If you have not created a project yet, do it with the following command :

{% highlight bash %}
mvn archetype:generate \
  -DarchetypeGroupId=fr.lteconsulting \
  -DarchetypeArtifactId=angular2-gwt.archetype \
  -DarchetypeVersion=1.3
{% endhighlight %}

Go into the project folder and build it to check that everything is ok :

{% highlight bash %}
mvn clean install
{% endhighlight %}

You can now import the project in your IDE.

Meanwhile, we are going to launch the development mode with hot reload on both server and client sides. In two terminals, launch those two commands :

{% highlight bash %}
# In one terminal :
mvn spring-boot:run

# And once the first is ready,
# in another terminal :
mvn gwt:run-codeserver
{% endhighlight %}

Now go to the url `http://localhost:8080` and you should see your application running.

# The Hero Editor

## Show our Hero

First let's modify the `ApplicationComponent` class so that it looks like this :

{% highlight java %}
@Component(
  selector = "my-app",
  template = "<h1>{ {title}}</h1><h2>{ {hero}} details!</h2>" )
@JsType
public class ApplicationComponent
{
  public String title = "Tour of Heroes";
  public String hero = "Windstorm";
}
{% endhighlight %}

Refresh your browser, and it should display the `title` and `hero` properties. This is the _interpolation_ form of one-way data binding.



## Create the `Hero` data object

**Note:** all the classes used in the front side of the application must reside under the `client` package.

Since we will use it everywhere in our application, let's create the DTO right now. Let's create a `Hero` class looking like this:

{% highlight java %}
@JsType
public class Hero
{
  public int id;
  public String name;

  public Hero( int id, String name )
  {
    this.id = id;
    this.name = name;
  }
}
{% endhighlight %}

This class has nothing special appart from the annotations. It is annotated with `@JsType` which means that this Java class is accessible to the javascript world. This makes Angular able to watch over our data objects and update the views when needed. The fields `title` and `name` are automatically exposed to Javascript because they are `public`. If you choose to make them `private`, you will need to annotate them with the `@JsProperty` annotation so that they keep being accessible from the javascript world too.

Now that we have a `Hero` class, let’s refactor our component’s hero attribute to be of type `Hero`. Then initialize it with an `id` of `1` and the `name`, "Windstorm".

{% highlight java %}
public Hero hero = new Hero( 1, "Windstorm" );
{% endhighlight %}

Because we changed the hero from a string to an object, we update the binding in the template to refer to the hero’s `name` property. Update the `template`field of the `@Component` annotation in the `ApplicationComponent` :

{% highlight java %}
template = "<h1>{{title}}</h1><h2>{{hero.name}} details!</h2>"
{% endhighlight %}

The browser refreshes and continues to display our hero’s name.

## Adding more HTML

Displaying a name is good, but we want to see all of our hero’s properties. We’ll add a `<div>` for our hero’s `id` property and another `<div>` for our hero’s `name`. Our template now looks like that :

{% highlight java %}
template = "<h1>{ {title}}</h1>"
  + "<h2>{ {hero.name}} details!</h2>"
  + "<div><label>id: </label>{ {hero.id}}</div>"
  + "<div><label>name: </label>{ {hero.name}}</div>"
{% endhighlight %}

## Editing Our Hero

We want to be able to edit the hero name in a textbox.

### Two-way binding

We intend to display the name of the hero in an `<input>`, change it, and see those changes wherever we bind to the hero’s name. In short, we want two-way data binding.

Before we can use two-way data binding for **form inputs**, we need to import the `FormsModule` package in our Angular module. Hopefully, since it is so common to use this module, the Angular2Boot archetype already imported it for you (int the `ApplicationModule` class) !

Now update the template and replace the `{{ "{{ hero.name " }}}}` text by `<input [(ngModel)]='hero.name' placeholder='name'>`. The class now looks lie this :

{% highlight java %}
{% raw %}
@Component(
		selector = "my-app",
		template = "<h1>{{title}}</h1>"
				+ "<h2>{{hero.name}} details!</h2>"
				+ "<div><label>id: </label>{{hero.id}}</div>"
				+ "<div><label>name: </label><input [(ngModel)]='hero.name' placeholder='name'></div>" )
@JsType
public class ApplicationComponent
{
	public String title = "Tour of Heroes";
	public Hero hero = new Hero( 1, "Windstorm" );
}
{% endraw %}
{% endhighlight %}

Refresh the browser. We see our hero again. We can edit the hero’s name and see the changes reflected immediately in the `<h2>`.

## The Road We’ve Travelled

Let’s take stock of what we’ve built.

Our Tour of Heroes uses the double curly braces of interpolation (a kind of one-way data binding) to display the application title and properties of a `Hero` object.
We can both display and change the hero’s name after adding a two-way data binding to the `<input>` element using the built-in `ngModel` directive.
The `ngModel` directive also propagates changes to every other binding of the `hero.name`.


# Master/Detail

## Displaying our Heroes

### Creating heroes

Let’s create a Javascript array of ten heroes in the `ApplicationComponent` class.

{% highlight java %}
{% raw %}
private static final JsArray<Hero> HEROES = JsArray.of(
			new Hero( 11, "Mr. Nice" ),
			new Hero( 12, "Narco" ),
			new Hero( 13, "Bombasto" ),
			new Hero( 14, "Celeritas" ),
			new Hero( 15, "Magneta" ),
			new Hero( 16, "RubberMan" ),
			new Hero( 17, "Dynama" ),
			new Hero( 18, "Dr IQ" ),
			new Hero( 19, "Magma" ),
			new Hero( 20, "Tornado" ) );
{% endraw %}
{% endhighlight %}

The `HEROES` array is of type `JsArray<Hero>`. The `JsArray<T>` type is a wrapper type to the native Javascript array, which is different from the usual `ArrayList<T>` in Java. Because Angular will need to access to our data and does not understand the `ArrayList` type, we need to use the `JsArray` type.

We aspire to fetch this list of heroes from a web service, but let’s take small steps first and display mock heroes.

### Exposing heroes

Let’s create a public property in `ApplicationComponent` that exposes the heroes for binding.

{% highlight java %}
public JsArray<Hero> heroes = HEROES;
{% endhighlight %}

### Displaying heroes in a template

Our component has `heroes`. Let’s create an unordered list in our template to display them. We’ll insert the following chunk of HTML below the title and above the hero details.

{% highlight java %}
+ "<h2>My Heroes</h2>"
+ "<ul class='heroes'>"
+ "<li>"
+ "<!-- each hero goes here -->"
+ "</li>"
+ "</ul>"
{% endhighlight %}

Now we have a template that we can fill with our heroes.

### Listing heroes with ngFor

We want to bind the array of `heroes` in our component to our template, iterate over them, and display them individually. We’ll need some help from Angular to do this. Let’s do this step by step.

First modify the `<li>` tag by adding the built-in directive `*ngFor`.

{% highlight java %}
+ "<li *ngFor='let hero of heroes'>"
{% endhighlight %}

Now we insert some content between the `<li>` tags that uses the hero template variable to display the hero’s properties.

{% highlight java %}
{% raw %}
+ "<li *ngFor='let hero of heroes'>"
+ "<span class='badge'>{{hero.id}}</span> {{hero.name}}"
+ "</li>"
+ "</ul>"
{% endraw %}
{% endhighlight %}

When the browser refreshes, we see a list of heroes!

### Styling our heroes

Our list of heroes looks pretty bland. We want to make it visually obvious to a user which hero we are hovering over and which hero is selected.

Let’s add some styles to our component.

First, create a file named `application.component.css` in the `src/main/resources/static` folder of your project. Add this content :

{% highlight css %}
.selected {
	background-color: #CFD8DC !important;
	color: white;
}

.heroes {
	margin: 0 0 2em 0;
	list-style-type: none;
	padding: 0;
	width: 15em;
}

.heroes li {
	cursor: pointer;
	position: relative;
	left: 0;
	background-color: #EEE;
	margin: .5em;
	padding: .3em 0;
	height: 1.6em;
	border-radius: 4px;
}

.heroes li.selected:hover {
	background-color: #BBD8DC !important;
	color: white;
}

.heroes li:hover {
	color: #607D8B;
	background-color: #DDD;
	left: .1em;
}

.heroes .text {
	position: relative;
	top: -3px;
}

.heroes .badge {
	display: inline-block;
	font-size: small;
	color: white;
	padding: 0.8em 0.7em 0 0.7em;
	background-color: #607D8B;
	line-height: 1em;
	position: relative;
	left: -1px;
	top: -4px;
	height: 1.8em;
	margin-right: .8em;
	border-radius: 4px 0 0 4px;
}
{% endhighlight %}

Now we refer to this file in the `ApplicationComponent` by setting the `styleUrls` :

{% highlight java %}
styleUrls = "application.component.css"
{% endhighlight %}

When we assign styles to a component they are scoped to that specific component. Our styles will only apply to our `ApplicationComponent` and won't "leak" to the outer HTML.

The complete `ApplicationComponent` file should now look like this :

{% highlight java %}
{% raw %}
@Component(
		selector = "my-app",
		template = "<h1>{{title}}</h1>"
				+ "<h2>My Heroes</h2>"
				+ "<ul class='heroes'>"
				+ "<li *ngFor='let hero of heroes'>"
				+ "<span class='badge'>{{hero.id}}</span> {{hero.name}}"
				+ "</li>"
				+ "</ul>"
				+ "<h2>{{hero.name}} details!</h2>"
				+ "<div><label>id: </label>{{hero.id}}</div>"
				+ "<div><label>name: </label><input [(ngModel)]='hero.name' placeholder='name'></div>",
		styleUrls = "application.component.css" )
@JsType
public class ApplicationComponent
{
	private static final JsArray<Hero> HEROES = JsArray.of(
			new Hero( 11, "Mr. Nice" ),
			new Hero( 12, "Narco" ),
			new Hero( 13, "Bombasto" ),
			new Hero( 14, "Celeritas" ),
			new Hero( 15, "Magneta" ),
			new Hero( 16, "RubberMan" ),
			new Hero( 17, "Dynama" ),
			new Hero( 18, "Dr IQ" ),
			new Hero( 19, "Magma" ),
			new Hero( 20, "Tornado" ) );

	public String title = "Tour of Heroes";
	public Hero hero = new Hero( 1, "Windstorm" );
	public JsArray<Hero> heroes = HEROES;
}
{% endraw %}
{% endhighlight %} 

## Selecting a Hero

We have a list of heroes and we have a single hero displayed in our app. The list and the single hero are not connected in any way. We want the user to select a hero from our list, and have the selected hero appear in the details view. This UI pattern is widely known as "master-detail". In our case, the master is the heroes list and the detail is the selected hero.

Let’s connect the master to the detail through a `selectedHero` component property bound to a click event.

### Click event

We modify the `<li>` by inserting an Angular event binding to its click event.

{% highlight java %}
{% raw %}
+ "<li *ngFor='let hero of heroes' (click)='onSelect(hero)'>"
{% endraw %}
{% endhighlight %}

Focus on the event binding

{% highlight %}
{% raw %}
(click)='onSelect(hero)
{% endraw %}
{% endhighlight %}

The parenthesis identify the `<li>` element’s `click` event as the target. The expression to the right of the equal sign calls the `ApplicationComponent` method, `onSelect()`, passing the template input variable `hero` as an argument. That’s the same `hero` variable we defined previously in the `ngFor`.

### Add the click handler

Our event binding refers to an `onSelect` method that doesn’t exist yet. We’ll add that method to our component now.

What should that method do? It should set the component’s selected hero to the hero that the user clicked.

Our component doesn’t have a “selected hero” yet either. We’ll start there.

### Expose the selected hero

We no longer need the `hero` property of the `ApplicationComponent`. **Replace** it with this simple `selectedHero` property:

{% highlight java %}
public Hero selectedHero = null;
{% endhighlight %}

We’ve decided that none of the heroes should be selected before the user picks a hero so we initialize the `selectedHero` to `null`.

Now **add an `onSelect` method** that sets the selectedHero property to the hero the user clicked.

{% highlight java %}
public void onSelect( Hero hero )
{
    selectedHero = hero;
}
{% endhighlight %}

We will be showing the selected hero's details in our template. At the moment, it is still referring to the old `hero` property. Let’s fix the template to bind to the new `selectedHero` property.

{% highlight java %}
{% raw %}
+ "<h2>{{selectedHero.name}} details!</h2>"
+ "<div><label>id: </label>{{selectedHero.id}}</div>"
+ "<div><label>name: </label><input [(ngModel)]='selectedHero.name' placeholder='name'></div>"
{% endraw %}
{% endhighlight %}

### Hide the empty detail with `ngIf`

When our app loads we see a list of heroes, but a hero is not selected. The selectedHero is undefined. That’s why we'll see the following error in the browser’s console:

        EXCEPTION: TypeError: Cannot read property 'name' of undefined in [null]

Remember that we are displaying `selectedHero.name` in the template. This name property does not exist because `selectedHero` itself is undefined.

We'll address this problem by keeping the hero detail out of the DOM until there is a `selected` hero.

We wrap the HTML hero detail content of our template with a `<div>`. Then we add the `ngIf` built-in directive and set it to the `selectedHero` property of our component.

{% highlight java %}
{% raw %}
+ "<div *ngIf='selectedHero'>"
+ "<h2>{{selectedHero.name}} details!</h2>"
+ "<div><label>id: </label>{{selectedHero.id}}</div>"
+ "<div><label>name: </label><input [(ngModel)]='selectedHero.name' placeholder='name'></div>"
+ "</div>"
{% endraw %}
{% endhighlight %}

When there is no `selectedHero`, the `ngIf` directive removes the hero detail HTML from the DOM. There will be no hero detail elements and no bindings to worry about.

When the user picks a hero, `selectedHero` becomes "truthy" and `ngIf` puts the hero detail content into the DOM and evaluates the nested bindings.

Refresh the browser and see the list of heroes but not the selected hero detail. The `ngIf` keeps it out of the DOM as long as the `selectedHero` is `null`. When we click on a hero in the list, the selected hero displays in the hero details. Everything is working as we expect.

### Styling the selection

We see the selected hero in the details area below but we can’t quickly locate that hero in the list above. We can fix that by applying the `selected` CSS class to the appropriate `<li>` in the master list. For example, when we select Magneta from the heroes list, we can make it pop out visually by giving it a subtle background color.

We’ll add a property binding on `class` for the `selected` class to the template. We'll set this to an expression that compares the current `selectedHero` to the hero.

The key is the name of the CSS class (`selected`). The value is `true` if the two heroes match and `false` otherwise. We’re saying _“apply the `selected` class if the heroes match, remove it if they don’t”_.

{% highlight html %}
{% raw %}
[class.selected]='hero === selectedHero'
{% endraw %}
{% endhighlight %}

Notice in the template that the `class.selected` is surrounded in square brackets (`[]`). This is the syntax for a **property binding**, a binding in which data flows one way from the data source (the expression `hero === selectedHero`) to a property of `class`.

{% highlight java %}
{% raw %}
+ "<li *ngFor='let hero of heroes'"
+ " (click)='onSelect(hero)'"
+ " [class.selected]='hero === selectedHero'>"
+ " <span class='badge'>{{hero.id}}</span> {{hero.name}}"
+ "</li>"
{% endraw %}
{% endhighlight %}

Refresh the browser. We select the hero Magneta and the selection is clearly identified by the background color.

We select a different hero and the tell-tale color switches to that hero.

Here's the complete `ApplicationComponent.java` as it stands now:

{% highlight java %}
{% raw %}
@Component(
		selector = "my-app",
		template = "<h1>{{title}}</h1>"
				+ "<h2>My Heroes</h2>"
				+ "<ul class='heroes'>"
				+ "<li *ngFor='let hero of heroes'"
				+ " (click)='onSelect(hero)'"
				+ " [class.selected]='hero === selectedHero'>"
				+ " <span class='badge'>{{hero.id}}</span> {{hero.name}}"
				+ "</li>"
				+ "</ul>"
				+ "<div *ngIf='selectedHero'>"
				+ "<h2>{{selectedHero.name}} details!</h2>"
				+ "<div><label>id: </label>{{selectedHero.id}}</div>"
				+ "<div><label>name: </label><input [(ngModel)]='selectedHero.name' placeholder='name'></div>"
				+ "</div>",
		styleUrls = "application.component.css" )
@JsType
public class ApplicationComponent
{
	private static final JsArray<Hero> HEROES = JsArray.of(
			new Hero( 11, "Mr. Nice" ),
			new Hero( 12, "Narco" ),
			new Hero( 13, "Bombasto" ),
			new Hero( 14, "Celeritas" ),
			new Hero( 15, "Magneta" ),
			new Hero( 16, "RubberMan" ),
			new Hero( 17, "Dynama" ),
			new Hero( 18, "Dr IQ" ),
			new Hero( 19, "Magma" ),
			new Hero( 20, "Tornado" ) );

	public String title = "Tour of Heroes";
	public Hero selectedHero = null;
	public JsArray<Hero> heroes = HEROES;

	public void onSelect( Hero hero )
	{
		selectedHero = hero;
	}
}
{% endraw %}
{% endhighlight %}

## The Road We’ve Travelled

Here’s what we achieved in this chapter:

- Our Tour of Heroes now displays a list of selectable heroes
- We added the ability to select a hero and show the hero’s details
- We learned how to use the built-in directives `ngIf` and `ngFor` in a component’s template

## The Road Ahead

Our Tour of Heroes has grown, but it’s far from complete. We can't put the entire app into a single component. We need to break it up into sub-components and teach them to work together as we learn in the next chapter.

# Multiple components

Our app is growing. Use cases are flowing in for reusing components, passing data to components, and creating more reusable assets. Let's separate the heroes list from the hero details and make the details component reusable.

## Making a Hero Detail component

Our heroes list and our hero details are in the same component in the same file. They're small now but each could grow. We are sure to receive new requirements for one and not the other. Yet every change puts both components at risk and doubles the testing burden without benefit. If we had to reuse the hero details elsewhere in our app, the heroes list would tag along for the ride.

Our current component violates the Single Responsibility Principle. It's only a tutorial but we can still do things right — especially if doing them right is easy and we learn how to build Angular apps in the process.

Let’s break the hero details out into its own component.

### Separating the Hero Detail Component

Add a new class named `HeroDetailComponent` as follows.

{% highlight java %}
@Component(
		selector = "my-hero-detail" )
@JsType
public class HeroDetailComponent
{

}
{% endhighlight }

We create metadata with the @Component decorator where we specify the selector name that identifies this component's element.

When we finish here, we'll create a corresponding `<my-hero-detail>` element in the `ApplicationComponent`'s template.

#### Hero Detail Template

At the moment, the _Heroes_ and _Hero Detail_ views are combined in one template in `ApplicationComponent`. Let’s **cut** the _Hero Detail_ content from `ApplicationComponent` and **paste** it into the new template property of `HeroDetailComponent`.

We previously bound to the `selectedHero.name` property of the `ApplicationComponent`. Our `HeroDetailComponent` will have a `hero` property, not a `selectedHero` property. So we replace `selectedHero` with `hero` everywhere in our new template. That's our only change. The result looks like this:

{% highlight java %}
{% raw %}
@Component(
		selector = "my-hero-detail",
		template = "<div *ngIf='hero'>"
				+ "<h2>{{hero.name}} details!</h2>"
				+ "<div><label>id: </label>{{hero.id}}</div>"
				+ "<div><label>name: </label><input [(ngModel)]='hero.name' placeholder='name'></div>"
				+ "</div>" )
@JsType
public class HeroDetailComponent
{
}
{% endraw %}
{% endhighlight %}

Now our hero detail layout exists only in the `HeroDetailComponent`.

Add the hero property

Let’s add that `hero` property we were talking about to the component class.


{% highlight java %}
public Hero hero;
{% endhighlight %}

#### The `hero` property is an **`input`**

The `HeroDetailComponent` must be told what hero to display. Who will tell it? The parent `ApplicationComponent`!

The `ApplicationComponent` knows which hero to show: the hero that the user selected from the list. The user's selection is in its `selectedHero` property.

We will soon update the `ApplicationComponent` template so that it binds its `selectedHero` property to the `hero` property of our `HeroDetailComponent`. The binding _might look_ like this:

{% highlight html %}
<my-hero-detail [hero]='selectedHero'></my-hero-detail>
{% endhighlight %}

Notice that the `hero` property is the **target** of a property binding — it's in square brackets to the left of the (`=`).

Angular insists that we declare a **target** property to be an **input** property. If we don't, Angular rejects the binding and throws an error.

We'll do it by annotating the `hero` property with the `@Input` annotation.

{% highlight java %}
@Input
public Hero hero;
{% endhighlight %}

### Refresh the `ApplicationModule`

We return to the `AppModule`, the application's root module, and teach it to use the `HeroDetailComponent`.

We add `HeroDetailComponent` to the `NgModule` decorator's `declarations` array. This array contains the list of all components, pipes, and directives that we created and that belong in our application's module.

{% highlight java %}
@NgModule(
		imports = { BrowserModule.class, FormsModule.class },
		declarations = {
				ApplicationComponent.class,
				HeroDetailComponent.class
		},
		bootstrap = ApplicationComponent.class )
@JsType
public class ApplicationModule
{
}
{% endhighlight %}

### Refresh the `ApplicationComponent`

Now that the application knows about our `HeroDetailComponent`, find the location in the `ApplicationComponent` template where we removed the _Hero Detail_ content and add an element tag that represents the `HeroDetailComponent`.

{% highlight html %}
<my-hero-detail></my-hero-detail>
{% endhighlight %}

The two components won't coordinate until we bind the `selectedHero` property of the `ApplicationComponent` to the `HeroDetailComponent` element's `hero` property like this:

{% highlight html %}
<my-hero-detail [hero]='selectedHero'></my-hero-detail>
{% endhighlight %}

The `ApplicationComponent`’s template should now look like this

{% highlight java %}
{% raw %}
template = "<h1>{{title}}</h1>"
				+ "<h2>My Heroes</h2>"
				+ "<ul class='heroes'>"
				+ "<li *ngFor='let hero of heroes'"
				+ " (click)='onSelect(hero)'"
				+ " [class.selected]='hero === selectedHero'>"
				+ " <span class='badge'>{{hero.id}}</span> {{hero.name}}"
				+ "</li>"
				+ "<my-hero-detail [hero]='selectedHero'></my-hero-detail>"
				+ "</ul>",
{% endraw %}
{% endhighlight %}

Thanks to the binding, the `HeroDetailComponent` should receive the hero from the `ApplicationComponent` and display that hero's detail beneath the list. The detail should update every time the user picks a new hero.

### It works !

When we view our app in the browser we see the list of heroes. When we select a hero we can see the selected hero’s details.

What's fundamentally new is that we can use this `HeroDetailComponent` to show hero details anywhere in the app.

We’ve created our first reusable component!

## The Road We’ve Travelled

Let’s take stock of what we’ve built.

- We created a reusable component
- We learned how to make a component accept input
- We learned to declare the application directives we need in an Angular module. We list the directives in the `NgModule` annotation's `declarations` array.
- We learned to bind a parent component to a child component.

## The Road Ahead

Our Tour of Heroes has become more reusable with shared components.

We're still getting our (mock) data within the `ApplicationComponent`. That's not sustainable. We should refactor data access to a separate service and share it among the components that need data.

We’ll learn to create services in the next tutorial chapter.


# Services

The Tour of Heroes is evolving and we anticipate adding more components in the near future.

Multiple components will need access to hero data and we don't want to copy and paste the same code over and over. Instead, we'll create a single reusable data service and learn to inject it in the components that need it.

Refactoring data access to a separate service keeps the component lean and focused on supporting the view. It also makes it easier to unit test the component with a mock service.

Because data services are invariably asynchronous, we'll finish the chapter with a **Promise**-based version of the data service.

## Creating a Hero Service

Our stakeholders have shared their larger vision for our app. They tell us they want to show the heroes in various ways on different pages. We already can select a hero from a list. Soon we'll add a dashboard with the top performing heroes and create a separate view for editing hero details. All three views need hero data.

At the moment the `ApplicationComponent` defines mock heroes for display. We have at least two objections. First, defining heroes is not the component's job. Second, we can't easily share that list of heroes with other components and views.

We can refactor this hero data acquisition business to a single service that provides heroes, and share that service with all components that need heroes.

### Create the HeroService

Create a new class called `HeroService` and annotate it with the `@Injectable` and `@JsType` annotations.

### Injectable Services

Angular2Boot sees the @Injectable annotation and emits metadata about our service, metadata that Angular may need to inject other dependencies into this service.

The `HeroService` doesn't have any dependencies at the moment. Add the annotation anyway. It is a "best practice" to apply the `@Injectable` annotation ​from the start​ both for consistency and for future-proofing.

### Getting Heroes

Add a `getHeroes()` method stub.

{% highlight java %}
@Injectable
@JsType
public class HeroService
{
	public JsArray<Hero> getHeroes()
	{
	}
}
{% endhighlight %}

We're holding back on the implementation for a moment to make an important point.

The consumer of our service doesn't know how the service gets the data. Our HeroService could get `Hero` data from anywhere. It could get the data from a web service or local storage or from a mock data source.

That's the beauty of removing data access from the component. We can change our minds about the implementation as often as we like, for whatever reason, without touching any of the components that need heroes.

### Mock Heroes

We already have mock `Hero` data sitting in the `ApplicationComponent`. It doesn't belong there. It doesn't belong here either. We'll move the mock data to its own file.

Cut the `HEROES` array from `ApplicationComponent` and paste it into the `HeroService` class.

Then, in the `HeroService` class return the mock array in the `getHeroes()` method. The code looks like this :

{% highlight java %}
@Injectable
@JsType
public class HeroService
{
	private static final JsArray<Hero> HEROES = JsArray.of(
			new Hero( 11, "Mr. Nice" ),
			new Hero( 12, "Narco" ),
			new Hero( 13, "Bombasto" ),
			new Hero( 14, "Celeritas" ),
			new Hero( 15, "Magneta" ),
			new Hero( 16, "RubberMan" ),
			new Hero( 17, "Dynama" ),
			new Hero( 18, "Dr IQ" ),
			new Hero( 19, "Magma" ),
			new Hero( 20, "Tornado" ) );
	
	public JsArray<Hero> getHeroes()
	{
		return HEROES;
	}
}
{% endhighlight %}

### Use the Hero Service

We're ready to use the `HeroService` in other components starting with our `ApplicationComponent`.

### Do we _new_ the `HeroService`? No way!

We could create a new instance of the `HeroService` with the `new` operator by ourselves.

That's a bad idea for several reasons including

- Our component has to know how to create a `HeroService`. If we ever change the `HeroService` constructor, we'll have to find every place we create the service and fix it. Running around patching code is error prone and adds to the test burden.
- We create a new service each time we use `new`. What if the service should cache heroes and share that cache with others? We couldn't do that.
- We're locking the `ApplicationComponent` into a specific implementation of the `HeroService`. It will be hard to switch implementations for different scenarios. Can we operate offline? Will we need different mocked versions under test? Not easy.

_What if ... what if ... Hey, we've got work to do!_

We get it. Really we do. But it is so ridiculously easy to avoid these problems that there is no excuse for doing it wrong.

### Inject the `HeroService`

Insert the following lines in the `ApplicationComponent` class :

{% highlight java %}
private HeroService heroService;

public ApplicationComponent( HeroService heroService )
{
    this.heroService = heroService;
}
{% endhighlight %}

Now Angular will know it needs to supply an instance of the `HeroService` when it creates a new `ApplicationComponent`.

The _injector_ does not know yet how to create a `HeroService`. If we ran our code now, Angular would fail with an error:

        EXCEPTION: No provider for HeroService! (ApplicationComponent -> HeroService)

We have to teach the _injector_ how to make a `HeroService` by registering a `HeroService` provider. Do that by adding the following `providers` property to the bottom of the component metadata in the `@Component` annotation parameters of the `ApplicationComponent` class.

{% highlight java %}
providers = HeroService.class
{% endhighlight %}

The `providers` parameter tells Angular to create a fresh instance of the `HeroService` when it creates a new `ApplicationComponent`. The `ApplicationComponent` can use that service to get heroes and so can every child component of its component tree.

### `getHeroes` in the `ApplicationComponent`

We've got the service in a `heroService` private variable. Let's use it.

We pause to think. We can call the service and get the data in one line.

{% highlight java %}
heroes = heroService.getHeroes();
{% endhighlight %}

We don't really need a dedicated method to wrap one line. We write it anyway:

{% highlight java %}
public void getHeroes()
{
    heroes = heroService.getHeroes();
}
{% endhighlight %}

### The `ngOnInit` Lifecycle Hook

`ApplicationComponent` should fetch and display heroes without a fuss. Where do we call the `getHeroes()` method? In a constructor? We do not!

Years of experience and bitter tears have taught us to keep complex logic out of the constructor, especially anything that might call a server as a data access method is sure to do.

The constructor is for simple initializations like wiring constructor parameters to properties. It's not for heavy lifting. We should be able to create a component in a test and not worry that it might do real work — like calling a server! — before we tell it to do so.

If not the constructor, something has to call `getHeroes()`.

Angular will call it if we implement the Angular `OnInit` interface. Angular offers a number of interfaces for tapping into critical moments in the component lifecycle: at creation, after each change, and at its eventual destruction.

Each interface has a single method. When the component implements that method, Angular calls it at the appropriate time.

We declare that the `ApplicationComponent` implements the `OnInit` interface and implement the `ngOnInit` method with our initialization logic inside and leave it to Angular to call it at the right time. In our case, we initialize by calling `getHeroes()`.

{% highlight java %}
@Component( ... )
@JsType
public class ApplicationComponent implements OnInit
{
	...

	@Override
	public void ngOnInit()
	{
		getHeroes();
	}
	
	public void getHeroes()
	{
		heroes = heroService.getHeroes();
	}

    ...
}
{% endhighlight %}

Our application should be running as expected, showing a list of heroes and a hero detail view when we click on a hero name.

We're getting closer. But something isn't quite right.

## Async Services and Promises

Our `HeroService` returns a list of mock heroes immediately. Its `getHeroes()` method signature is synchronous.

Ask for heroes and they are there in the returned result.

Someday we're going to get heroes from a remote server. We don’t call http yet, but we aspire to in later chapters.

When we do, we'll have to wait for the server to respond and we won't be able to block the UI while we wait, even if we want to (which we shouldn't) because the browser won't block.

We'll have to use some kind of asynchronous technique and that will change the signature of our `getHeroes()` method.

We'll use _Promises_.

### The Hero Service makes a Promise

A **Promise** is ... well it's a promise to call us back later when the results are ready. We ask an asynchronous service to do some work and give it a callback function. It does that work (somewhere) and eventually it calls our function with the results of the work or an error.

Update the `HeroService` with this Promise-returning getHeroes method:

{% highlight java %}
public Promise<JsArray<Hero>> getHeroes()
{
    return Promise.resolve( HEROES );
}
{% endhighlight %}

We're still mocking the data. We're simulating the behavior of an ultra-fast, zero-latency server, by returning an **immediately resolved Promise** with our mock heroes as the result.

### Act on the Promise

Returning to the `AppplicationComponent` and its `getHeroes()` method, we have to change our implementation to act on the _Promise_ when it resolves. When the _Promise_ resolves successfully, then we will have heroes to display.

We pass our callback function as an argument to the Promise's **then** method:

{% highlight java %}
private void getHeroes()
{
    heroService.getHeroes().then( heroes -> this.heroes = heroes );
}
{% endhighlight %}

Our callback sets the component's `heroes` property to the array of heroes returned by the service. That's all there is to it!

Our app should still be running, still showing a list of heroes, and still responding to a name selection with a detail view.

## The Road We’ve Travelled

Let’s take stock of what we’ve built.

- We created a service class that can be shared by many components.
- We used the `OnInit` Lifecycle Hook to get our heroes when our `ApplicationComponent` activates.
- We defined our `HeroService` as a provider for our `ApplicationComponent`.
- We created mock hero data and imported them into our service.
- We designed our service to return a Promise and our component to get our data from the Promise.

## The Road Ahead

Our Tour of Heroes has become more reusable using shared components and services. We want to create a dashboard, add menu links that route between the views, and format data in a template. As our app evolves, we’ll learn how to design it to make it easier to grow and maintain.

We learn about Angular Component Router and navigation among the views in the next tutorial chapter.

## Appendix: Take it slow

We can simulate a slow connection.

Add the following `getHeroesSlowly()` method to the `HeroService` class:

{% highlight java %}
public Promise<JsArray<Hero>> getHeroesSlowly()
{
    return new Promise<>( ( resolver, rejecter ) -> {
        GlobalScope.setTimeout( () -> resolver.resolve(null), 2000 );
    } ).then( nop -> getHeroes() );
}
{% endhighlight %}

Like `getHeroes()`, it also returns a Promise. But this Promise waits 2 seconds before resolving the Promise with mock `heroes`.

Back in the `ApplicationComponent`, replace `heroService.getHeroes()` with `heroService.getHeroesSlowly()` and see how the app behaves.




# Routing

We received new requirements for our Tour of Heroes application:

- Add a _Dashboard_ view.
- Navigate between the _Heroes_ and _Dashboard_ views.
- Clicking on a hero in either view navigates to a detail view of the selected hero.
- Clicking a _deep link_ in an email opens the detail view for a particular hero.

## Action plan

Here's our plan:

- Turn `ApplicationComponent` into an application shell that only handles navigation
- Relocate the _Heroes_ concerns within the current `ApplicationComponent` to a separate `HeroesComponent`
- Add routing
- Create a new `DashboardComponent`
- Tie the _Dashboard_ into the navigation structure

## Splitting the `ApplicationComponent`

Our current app loads `ApplicationComponent` and immediately displays the list of heroes.

Our revised app should present a shell with a choice of views (_Dashboard_ and _Heroes_) and then default to one of them.

The `ApplicationComponent` should only handle navigation. Let's move the display of _Heroes_ out of `ApplicationComponent` and into its own `HeroesComponent`.

### `HeroesComponent`

`ApplicationComponent` is already dedicated to _Heroes_. Instead of moving anything out of `ApplicationComponent`, we'll just rename it `HeroesComponent` (and update all references to it with your IDE) and create a new `ApplicationComponent` shell separately.

Then we change the `selector` for the `HeroesComponent` to be `my-heroes`.

### Create the new `ApplicationComponent`

The new `ApplicationComponent` will be the application shell. It will have some navigation links at the top and a display area below for the pages we navigate to.

The initial steps are:

- Create the `ApplicationComponent` class.
- Add an `@Component` annotation on the class with a `my-app` selector.
- Move the following from `HeroesComponent` to `ApplicationComponent`:
-- `title` attribute
-- `@Component` template `<h1>` element, which contains a binding to `title`
- Add a `<my-heroes>` element to the app template just below the heading so we still see the heroes.
- Add `HeroesComponent` to the declarations array of `ApplicationModule` so Angular recognizes the `<my-heroes>` tags.
- Check that `bootstrap` in the `ApplicationModule` is set to `ApplicationComponent.class` and not `HeroesComponent.class` (when we did the refactoring your IDE might have updated this value) 
- Add `HeroService` to the providers array of `ApplicationModule` because we'll need it in every other view.
- Remove `HeroService` from the `HeroesComponent` providers array since it has been promoted.

Our `ApplicationModule` looks like this :

{% highlight java %}
@NgModule(
		imports = {
				BrowserModule.class,
				FormsModule.class },
		declarations = {
				ApplicationComponent.class,
				HeroesComponent.class,
				HeroDetailComponent.class
		},
		providers = {
				HeroService.class
		},
		bootstrap = {
				ApplicationComponent.class
		} )
@JsType
public class ApplicationModule
{
}
{% endhighlight %}

And the `ApplicationComponent` is :

{% highlight java %}
{% raw %}
@Component(
		selector = "my-app",
		template = "<h1>{{title}}</h1>"
				+ "<my-heroes></my-heroes>" )
@JsType
public class ApplicationComponent
{
	public String title = "Tour of Heroes";
}
{% endraw %}
{% endhighlight %}

The app still runs and still displays heroes. Our refactoring of `ApplicationComponent` into a new `ApplicationComponent` and a `HeroesComponent` worked! We have done no harm.

## Add Routing

We're ready to take the next step. Instead of displaying heroes automatically, we'd like to show them after the user clicks a button. In other words, we'd like to navigate to the list of heroes.

We'll need the _Angular Component Router_.

The Angular router is an external, optional Angular NgModule called `RouterModule`. The router is a combination of multiple provided services (`RouterModule`), multiple directives (`RouterOutlet`, `RouterLink`, `RouterLinkActive`), and a configuration (`Routes`). We'll configure our routes first.

### Configure routes

Our application doesn't have any routes yet. We'll start by creating a configuration class for the application routes.

_Routes_ tell the router which views to display when a user clicks a link or pastes a URL into the browser address bar.

Let's define a `Routes` class as follow :

{% highlight java %}
public class Routes implements ProviderWrapper
{
	@Override
	public Object get()
	{
		return RouterModule.forRoot( JsArray.of(
				RouterConfig.route(
						"heroes",
						HeroesComponent_AngularComponent.getComponentPrototype() ) ),
				new JsObject().set( "useHash", true ) );
	}
}
{% endhighlight %}

The `ProviderWrapper` interface tells Angular2Java that the class should be used as a service provider for Angular. Our one calls the angular `RouterModule.forRoot` function which builds a _router_ configured with the routes we pass in parameters.

We have only one route definition at the moment but rest assured, we'll add more.

This _route definition_ has the following parts:

- **path**: the router matches this route's path to the URL in the browser address bar (`heroes`).
- **component**: the component that the router should create when navigating to this route (`HeroesComponent`). **Note that we use the enhanced protype (generated by Angular2Java) and not the `HeroesComponent.class` class directly.**

### Make the router available

Add the `Routes.class` to the `imports` attribute of the `@NgModule` annotation in the `ApplicationModule` class.

You also need to add the `HeroesComponent.class` in the `entryComponents` attribute of the same annotation.

`ApplicationModule` now looks like this:

{% highlight java %}
@NgModule(
		imports = {
				BrowserModule.class,
				FormsModule.class,
				Routes.class },
		declarations = {
				ApplicationComponent.class,
				HeroesComponent.class,
				HeroDetailComponent.class
		},
		providers = {
				HeroService.class
		},
		entryComponents = HeroesComponent.class,
		bootstrap = {
				ApplicationComponent.class
		} )
@JsType
public class ApplicationModule
{
}
{% endhighlight %} 

### Router Outlet

If we paste the path, `#/heroes`, into the browser address bar, the router should match it to the `heroes` route and display the `HeroesComponent`. But where?

We have to **tell it where** by adding a `<router-outlet>` element to the bottom of the template. `RouterOutlet` is one of the directives provided by the `RouterModule`. The router displays each component immediately below the `<router-outlet>` as we navigate through the application.

### Router Links

We don't really expect users to paste a route URL into the address bar. We add an anchor tag to the template which, when clicked, triggers navigation to the `HeroesComponent`.

The revised template in `ApplicationComponent` looks like this:

{% highlight java %}
template = "<h1>{{title}}</h1>"
        + "<a routerLink='/heroes'>Heroes</a>"
        + "<router-outlet></router-outlet>"
{% endhighlight %}

Notice the `routerLink` binding in the anchor tag. We bind the `RouterLink` directive (another of the `RouterModule` directives) to a string that tells the router where to navigate when the user clicks the link.

Since our link is not dynamic, we define a routing instruction with a one-time binding to our route path. Looking back at the route configuration, we confirm that '#/heroes' is the path of the route to the `HeroesComponent`.

Refresh the browser. We see only the app title and heroes link. We don't see the heroes list.

We click the _Heroes_ navigation link, the browser bar updates to '#/heroes', and now we see the list of heroes. We are navigating at last!

At this stage, our `ApplicationComponent looks like this.`

{% highlight java %}
{% raw %}
@Component(
		selector = "my-app",
		template = "<h1>{{title}}</h1>"
				+ "<a routerLink='/heroes'>Heroes</a>"
				+ "<router-outlet></router-outlet>" )
@JsType
public class ApplicationComponent
{
	public String title = "Tour of Heroes";
}
{% endraw %}
{% endhighlight %}

The _ApplicationComponent_ is now attached to a router and displaying routed views. For this reason and to distinguish it from other kinds of components, we call this type of component a _Router Component_.

## Add a Dashboard

Routing only makes sense when we have multiple views. We need another view.

Create a placeholder `DashboardComponent` that gives us something to navigate to and from.

{% highlight java %}
@Component(
		selector = "my-dashboard",
		template = "<h3>My Dashboard</h3>" )
@JsType
public class DashboardComponent
{
}
{% endhighlight %}

We’ll come back and make it more useful later.

### Configure the dashboard route

Go back to the `Routes` class and teach it to navigate to the dashboard.

Add the following route definition to the array of definitions.

{% highlight java %}
RouterConfig.route(
    "dashboard",
    DashboardComponent_AngularComponent.getComponentPrototype() ) )
{% endhighlight %}

Also add `DashboardComponent.class` to our root `NgModule`'s both `declarations` and `entryComponents`.

#### `redirectTo`

We want the app to show the dashboard when it starts and we want to see a nice URL in the browser address bar that says '#/dashboard'. Remember that the browser launches with '/' in the address bar.

We can use a redirect route to make this happen. Add the following to our array of route definitions:

{% highlight java %}
RouterConfig.route(
    "",
    "/dashboard",
    "full" )
{% endhighlight %}

Add navigation to the template

Finally, add a dashboard navigation link to the template, just above the _Heroes_ link in the `ApplicationComponent`'s template.

{% highlight java %}
template = "<h1>{{title}}</h1>"
    + "<nav>"
    + "<a routerLink='/dashboard'>Dashboard</a>"
    + "<a routerLink='/heroes'>Heroes</a>"
    + "</nav>"
    + "<router-outlet></router-outlet>"
{% endhighlight %}

To see these changes in your browser, go to the application root (`/`) and reload. The app displays the dashboard and we can navigate between the dashboard and the heroes.

## Dashboard Top Heroes

Let’s spice up the dashboard by displaying the top four heroes at a glance.

Replace the `template` metadata with a `templateUrl` property that points to a new template file.

{% highlight java %}
templateUrl = "dashboard.component.html"
{% endhighlight %}

Create that file with this content (in the `src/main/resources/static/` folder):

{% highlight html %}
{% raw %}
<h3>Top Heroes</h3>
<div class="grid grid-pad">
	<div *ngFor="let hero of heroes" (click)="gotoDetail(hero)"
		class="col-1-4">
		<div class="module hero">
			<h4>{{hero.name}}</h4>
		</div>
	</div>
</div>
{% endraw %}
{% endhighlight %}

We use `*ngFor` once again to iterate over a list of heroes and display their names. We added extra `<div>` elements to help with styling later in this chapter.

There's a `(click)` binding to a `gotoDetail` method we haven't written yet and we're displaying a list of heroes that we don't have. We have work to do, starting with those heroes.

### Share the HeroService

We'd like to re-use the `HeroService` to populate the component's heroes array.

Recall earlier in the chapter that we removed the `HeroService` from the providers array of `HeroesComponent` and added it to the providers array of `ApplicationModule`.

That move created a singleton `HeroService` instance, available to all components of the application. Angular will inject `HeroService` and we'll use it here in the `DashboardComponent`.

### Get heroes

Now implement the `DashboardComponent` class like this:

{% highlight java %}
@Component(
		selector = "my-dashboard",
		templateUrl = "dashboard.component.html" )
@JsType
public class DashboardComponent implements OnInit
{
	public JsArray<Hero> heroes = JsArray.empty();

	private HeroService heroService;

	public DashboardComponent( HeroService heroService )
	{
		this.heroService = heroService;
	}

	@Override
	public void ngOnInit()
	{
		heroService.getHeroes().then( heroes -> this.heroes = heroes.slice( 1, 5 ) );
	}

	public void gotoDetail( Hero hero )
	{
		// not implemented yet
	}
}
{% endhighlight %}

We've seen this kind of logic before in the HeroesComponent:

- Define a `heroes` array property.
- Inject the `HeroService` in the constructor and hold it in a private `heroService` field.
- Call the service to get heroes inside the Angular `ngOnInit` lifecycle hook.

The noteworthy differences: we cherry-pick four heroes (2nd, 3rd, 4th, and 5th) and stub the `gotoDetail` method until we're ready to implement it.

Refresh the browser and see four heroes in the new dashboard.

## Navigate to Hero Details

Although we display the details of a selected hero at the bottom of the `HeroesComponent`, we don't yet navigate to the `HeroDetailComponent` in the three ways specified in our requirements:

- from the _Dashboard_ to a selected hero.
- from the _Heroes_ list to a selected hero.
- from a "deep link" URL pasted into the browser address bar.

Adding a hero-detail route seems like an obvious place to start.

### Routing to a hero detail

We'll add a route to the `HeroDetailComponent` in `Routes.java` where our other routes are configured.

The new route is a bit unusual in that we must tell the `HeroDetailComponent` which hero to show. We didn't have to tell the `HeroesComponent` or the `DashboardComponent` anything.

At the moment the parent `HeroesComponent` sets the component's hero property to a hero object with a binding like this.

{% highlight html %}
<my-hero-detail [hero]='selectedHero'></my-hero-detail>
{% endhighlight %}

That clearly won't work in any of our routing scenarios. Certainly not the last one; we can't embed an entire hero object in the URL! Nor would we want to.

### Parameterized route

We _can_ add the hero's `id` to the URL. When routing to the hero whose `id` is 11, we could expect to see an URL such as this:

        /detail/11

The `/detail/` part of that URL is constant. The trailing numeric `id` part changes from hero to hero. We need to represent that variable part of the route with a _parameter_ (or token) that stands for the hero's `id`.

### Configure a Route with a Parameter

Here's the route definition we'll use.

{% highlight java %}
RouterConfig.route( 
    "detail/:id", 
    HeroDetailComponent_AngularComponent.getComponentPrototype() )
{% endhighlight %}

The colon (:) in the path indicates that `:id` is a placeholder to be filled with a specific hero `id` when navigating to the HeroDetailComponent.

We're finished with the application routes.

We won't add a 'Hero Detail' link to the template because users don't click a navigation _link_ to view a particular hero. They click a _hero_ whether that hero is displayed on the dashboard or in the heroes list.

We'll get to those _hero_ clicks later in the chapter. There's no point in working on them until the `HeroDetailComponent` is ready to be navigated to.

That will require an `HeroDetailComponent` overhaul.

### Revise the `HeroDetailComponent`

Before we rewrite the `HeroDetailComponent`, let's review what it looks like now:

{% highlight java %}
{% raw %}
@Component(
		selector = "my-hero-detail",
		template = "<div *ngIf='hero'>"
				+ "<h2>{{hero.name}} details!</h2>"
				+ "<div><label>id: </label>{{hero.id}}</div>"
				+ "<div><label>name: </label><input [(ngModel)]='hero.name' placeholder='name'></div>"
				+ "</div>" )
@JsType
public class HeroDetailComponent
{
	@Input
	public Hero hero;
}
{% endraw %}
{% endhighlight %}

The template won't change. We'll display a hero the same way. The big changes are driven by how we get the hero.

We will no longer receive the hero in a parent component property binding. The new `HeroDetailComponent` should take the `id` parameter from the params observable in the `ActivatedRoute` service and use the `HeroService` to fetch the hero with that id.

Let's have the `ActivatedRoute` service and the `HeroService` injected into the constructor, saving their values in private fields:

{% highlight java %}
private HeroService heroService;
private ActivatedRoute route;

public HeroDetailComponent( HeroService heroService, ActivatedRoute route )
{
    this.heroService = heroService;
    this.route = route;
}
{% endhighlight %}

We tell the class that we want to implement the `OnInit` interface.

Inside the `ngOnInit` lifecycle hook, we use the params observable to extract the `id` parameter value from the `ActivateRoute` service and use the `HeroService` to fetch the hero with that `id`.

{% highlight java %}
@Override
public void ngOnInit()
{
    route.params.forEach( params -> {
        String value = params.get( "id" );
        if( value != null )
        {
            int id = Integer.parseInt( value );
            heroService.getHero( id ).then( hero -> this.hero = hero );
        }
    } );
}
{% endhighlight %}

Notice how we extract the `id` by calling the `forEach` method which will deliver our array of route parameters.

The hero `id` is an `int`. Route parameters are always `Strings`. So we convert the route parameter value to a number with the `Integer.parseInt()` method.

### Add HeroService.getHero

The problem with this bit of code is that `HeroService` doesn't have a `getHero` method! We better fix that quickly before someone notices that we broke the app.

Open `HeroService` and add a `getHero` method that filters the heroes list from `getHeroes` by `id`:

{% highlight java %}
public Promise<Hero> getHero( int id )
{
    return getHeroes().then( heroes -> heroes.find( hero -> hero.id == id ) );
}
{% endhighlight %}

Let's return to the `HeroDetailComponent` to clean up loose ends.

### Find our way back

We can navigate to the `HeroDetailComponent` in several ways. How do we navigate somewhere else when we're done?

The user could click one of the two links in the `ApplicationComponent`. Or click the browser's back button. We'll add a third option, a `goBack` method in `HeroDetailComponent` that navigates backward one step in the browser's history stack.

{% highlight java %}
public void goBack()
{
    JsTools.historyGoBack();
}
{% endhighlight %}

Then we wire this method with an event binding to a _Back_ button that we add to the bottom of the `HeroDetailComponent` component template.

{% highlight java %}
+ "<button (click)='goBack()'>Back</button>"
{% endhighlight %}

Modifing the template to add this button spurs us to take one more incremental improvement and migrate the template to its own file, called `hero-detail.component.html`:

{% highlight html %}
{% raw %}
<div *ngIf='hero'>
	<h2>{{hero.name}} details!</h2>
	<div>
		<label>id: </label>{{hero.id}}
	</div>
	<div>
		<label>name: </label><input [(ngModel)]='hero.name' placeholder='name'>
	</div>
</div>
<button (click)='goBack()'>Back</button>
{% endraw %}
{% endhighlight %}

We update the component metadata with a `templateUrl` pointing to the template file that we just created.

{% highlight java %}
templateUrl = "hero-detail.component.html"
{% endhighlight %}

Refresh the browser and see the results.

## Select a Dashboard Hero

When a user selects a hero in the dashboard, the app should navigate to the `HeroDetailComponent` to view and edit the selected hero.

In the dashboard template we bound each hero's click event to the `gotoDetail` method, passing along the selected `hero` entity.

We stubbed the `gotoDetail` method when we rewrote the `DashboardComponent`. Now we give it a real implementation.

{% highlight java %}
public void gotoDetail( Hero hero )
{
    JsArray<?> link = JsArray.of( "/detail", String.valueOf( hero.id ) );
    router.navigate( link );
}
{% endhighlight %}

The `gotoDetail` method navigates in two steps:

- Set a route _link_ parameters array
- Pass the array to the router's `navigate` method

For navigation, we wrote router links in the `ApplicationComponent` template. Those links had only one element, the path of the destination route.

This link parameters array has two elements, the **path** of the destination route and a route **parameter** set to the value of the selected hero's `id`.

The two array items align with the **path** and **:id** token in the parameterized hero detail route definition we added to the `Routes` class earlier in the chapter.

The `DashboardComponent` doesn't have the router yet. We obtain it in the usual way: inject the `router` in the constructor (along with the `HeroService`):

{% highlight java %}
private HeroService heroService;
private Router router;

public DashboardComponent( HeroService heroService, Router router )
{
    this.heroService = heroService;
    this.router = router;
}
{% endhighlight }

A last thing to do is to add the `HeroDetailComponent` class in the `entryComponents` field of the `NgModule` annotation of our root module (`ApplicationModule`).

Refresh the browser and select a hero from the dashboard; the app should navigate directly to that hero’s details.















































































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