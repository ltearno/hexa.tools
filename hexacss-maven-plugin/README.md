# HexaCss #

Another way integrating CSS to GWT...

## What ##

HexaCss is a light tool for GWT helping to integrate your Java program to any other CSS tools like Sass, Less, Stylus, Google Style Sheets, or even vanilla CSS.

It allows you to build several different valid CSS files for your application, in order to theme it. You can even generate them on the fly if needed !

Still you get benefits from the GWT integration like pruning unused CSS rules and obfusting class names to reduce the CSS file size.

HexaCss manages the CSS class binding to your code and you create your style sheets in total freedom !

## How ? ##

On the Java side, you write some boilerplate code, here is an example :

	package fr.lteconsulting.application;

	// interface through which one is able
	// to access to the css class names
	public interface MyCssClasses extends HexaCss
	{
		// singleton instance created through GWT's deferred binding
		public static MyCssClasses INSTANCE = GWT.create(MyCssClasses.class);
		
		// mapped to class .fr-lteconsulting-application-MyCssClasses-main
		// will return something like "h3ACF9"
		String main();

		// mapped to class .fr-lteconsulting-application-MyCssClasses-lighter
		String lighter();

		// mapped to class .fr-lteconsulting-application-MyCssClasses-darker
		String darker();
	}

As you see, it is quite the same as with GWT's CssResource, except you don't have to use the ClientBundle interface.

One thing to remember is that the css class name corresponding to each method is composed of the Java interface's full qualified name and the method name. Dots are replaced by hyphens. An exception is that is the interface name is `Css`, the `-Css` suffix is not added to the css class name - this allows to save some space.

The idea with HexaCss is that you must provide one CSS file containing all the rules for the entire application. This CSS file needs to be then processed by HexaCss to be optimized (long names like the ones above are replaced with cryptic short ones) (css rules from your file which are not effectively used in your application are pruned).

### Different approaches to create your CSS files ###

You can invent your own style, but here are some examples :

1. Using a framework like Sass or Less

Writing that :

	.fr-lteconsulting-application-MyCssClasses
	{
		// the '&' character repeats the parent class name
		&-main
		{
			background-color: #e65;
			color: #182;
		}

		&-lighter
		{
			...
		}

		&-darker { ...
	}

will allow you not to repeat yourself too much. You can then create a file for each Java interface and include them in a "big" less/sass file containing your theme parameters.

2. writing everything by hand

