# HexaCss

## HexaCss is a tool aiming at efficiently integrating any CSS framework with GWT. 

## From the developper's point of view, this is done easily by using the same paradigms as the usual GWT's CssResource.

### What benefits do you get using it ?

- You can use any CSS frameworks like [Sass](http://sass-lang.com/), [Less](http://lesscss.org/), [Google Stylesheets](https://code.google.com/p/closure-stylesheets/), [Susy](http://susy.oddbird.net/) and so on. You have type-safe use of them and they manage well global variables and mixins...
- You can generate multiple CSS files for the same application, so you get easy themes,
- The added bonus, you get a piece of code to dynamically switch your application's CSS.
- As with the usual GWT CssResource, unused rules are pruned and class names are shrinked for size and isolation,
- You use an API you are used to so you don't have to learn much to use it.


# Installation

Clone the hexa.tools repository and build the hexa.css and hexa.css-maven-plugin projects :

		git clone https://github.com/ltearno/hexa.tools.git
		cd hexa.tools
		mvn install

Then to build the samples :

		cd hexa.css.samples
		mvn install

Now to run the samples you just have to drop each `index.html` file in the target directories in a browser !

You can also directly try those demos here :

- [Demo 1](http://www.lteconsulting.fr/hexacss/demo/sample1/index.html), one application with multiple hand made css files loaded through different html pages.
- [Demo 2](http://www.lteconsulting.fr/hexacss/demo/sample2/index.html), one application in one html page loading different themes dynamically.
- [Demo 3](http://www.lteconsulting.fr/hexacss/demo/sample3/index.html), same as above except that the theme css files are generated through Less.


# Use

First, add this to your project's `pom.xml` file :

		<dependency>
			<groupId>fr.lteconsulting</groupId>
			<artifactId>hexacss</artifactId>
			<version>1.2</version>
		<dependency>

You also need to inherit the HexaCss module in your application's module :

		<inherits src="fr.lteconsulting.hexa.HexaCss"/>

Then you have to define the Java interface through which you'll get CSS class names.

		package my.application;

		public interface MyCss extends HexaCss
		{
			String mainWindow();
			String textInput();
			...
		}

Then you use the CSS classes as in this code:

		MyCss myCss = GWT.create( MyCss.class );
		getElement().addClassName( myCss.mainWindow() );
		// or
		widget.addStyleName( myCss.mainWindow() );

Now you need to write your own CSS files. The only thing to know is how method names in HexaCss interfaces are mapped to CSS class names. The CSS class name corresponding to a method is the fully qualified name of the interface appended with the method name where all the dots are replaced by hyphens. 

In this example, the `MyCss` class' package is `my.application` and the method names are `mainWindow` and `textInput`. The corresponding CSS class names are therefore `my-application-MyCss-mainWindow` and `my-application-MyCss-textInput`.

Here is an example CSS file that you could use. Of course, you would certainly prefer to generate it with Sass or Less :

		.my-application-MyCss-mainWindow {
			...

		.my-application-MyCss-textInput {
			...
		}

With Less you'd have written :

        .my-application-MyCss {
            &-mainWindow {
                ...
            }

            &-textInput {
                ...
            }
        }

### More conscise naming

Note that if the HexaCss interface name is `Css`, it will be simply and purely removed from the CSS class name. So if that was the case in the previous example, the CSS class names would have be : `my-application-mainWindow` and `my-application-textInput`. That leads to less verbose CSS.

## Integration with GWT

When you build your GWT application, Hexa CSS generates a file that helps during the maven build to obfuscate the CSS class names in your CSS files and filter out the rules that are not needed.

So here is the chunk you need to add in your pom.xml file to process your CSS file. In this example, it is assumed that the `application.css` and `application-blue.css` files are present in the `sourceDirectory` directory (they may have be generated before). Those files are processed and the result is written in the `outputDirectory` directory :

		<plugin>
			<groupId>fr.lteconsulting</groupId>
			<artifactId>hexacss-maven-plugin</artifactId>
			<version>1.2</version>

			<configuration>
				<sourceDirectory>${project.build.directory}/css</sourceDirectory>
				<mappingFile>war/${gwtmodule}/hexas-css.mapping</mappingFile>
				<outputDirectory>war</outputDirectory>

				<includes>
					<include>application.css</include>
					<include>application-blue.css</include>
				</includes>
			</configuration>

			<executions>
				<execution>
					<phase>package</phase>
					<goals>
						<goal>process</goal>
					</goals>
				</execution>
			</executions>
		</plugin>

The only last thing is to load the generated CSS file in your page, so the simplest way to do that is to add the `<link>` tag in the html page. Note that HexaCss has a CSS loader that can dynamically load and replace the current application's CSS, we'll see that later.

Here is what you add in your html file :

		<link type="text/css" rel="stylesheet" href="application.css">

Now your GWT application should work with your generated CSS files !


### Theme Switcher

As illustrated in sample 2 and 3, included with HexaCss is a little Java class that does dynamic theme switching.

You'll find it in the sources, its fqn is `fr.lteconsulting.hexa.client.css.ThemeManager`.

### Using Less, Sass or others

The real power comes when you use HexaCss together with a CSS edition tool like Less, Sass or GSS. The above sample 3 uses Less through its maven plugin. You might find plenty of other ways to include CSS generation in your build.









# Roadmap

As for now, this library has only served (well) several production application's needs.

There are few features that should be made available in the short-term future in order to satisfy a wider range of applications. Here is the list :

- An `@annotation` to override the standard naming mecanism. To put it simply, this will allow to integrate easily with existing CSS files. For example, integrating any Bootstrap theme will be easy. And it will still benefit from the name-obfuscation feature...
- An option to use in the application's gwt module file in order to disengage the name-obfuscation mecanism. It could even be possible to fully customize the obfuscation system if that is needed (with a plugin system).
- Make an attempt to use the annotation processing system (from standard Java) instead of the GWT Deferred Binding mecanism. This should only be done if it doesn't go in the way of optimizations that are made possible by the GWT generator and linker contexts (especially unused CSS rules pruning).
- Define a cool and easy way to prepare themes for external libraries. Say your application embeds several GWT libraries using HexaCss, how to combine several CSS files made for those libraries in a elegant way ? That is still something i am thinking about...

Of course, please feel free to ask for new features if you think that something is missing. All contributions will be welcome !







# Contact

This library has been made with love by [LTE Consulting](http://www.lteconsulting.fr).

You can send pull request on GitHub : [https://github.com/ltearno/hexa.tools](https://github.com/ltearno/hexa.tools).

And contact me by email : [ltearno@gmail.com](mailto:ltearno@gmail.com)
