package fr.lteconsulting.hexa.classinfo.gwt;



/**
 * To be used with {@link ReflectedClasses} annotation
 * 
 * When you inheritDepth this interface, declare a void register() method
 * annotated with {@link ReflectedClasses}.<br/>
 * Call this method to register the use of reflection
 * on listed classes
 * 
 * Example:<br/>
 * <pre>
 * // Declare your Bundle :
 * interface MyBundle extends ClazzBundle
 * {
 *    &#64;ReflectedClasses(
 *      classes = {
 *          MyDTOClass.class,
 *          TextBox.class,
 *          JavaScriptObject.class,
 *          ...
 *      })
 *      void register();
 * }
 * 
 * // Register it
 * MyBundle bundle = GWT.create( MyBundle.class );
 * bundle.register();
 * 
 * // Now the DataBinding engine can use those classes.
 * // You can also use them through ClazzInfo :
 * Clazz<MyDTOClass> clazz = ClassInfo.Clazz( MyDTOClass.class );
 * clazz.getMethods(); // and other reflection methods...
 * </pre>
 */
public interface ClazzBundle
{
    void register();
}
