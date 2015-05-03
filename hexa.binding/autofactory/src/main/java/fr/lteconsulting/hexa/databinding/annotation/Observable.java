package fr.lteconsulting.hexa.databinding.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation trigger the generation of a child
 * class with all the required getters and setters to
 * be considered as "Observable" :
 * 
 * - there is a getter for each non-private field of the class
 * - there is a setter for each of the non-private field of the
 * class. Each setter should trigger an event on the
 * binding system.
 * 
 * The generated class will be generated in the same package
 * as the annotated class and will have the same name, prefixed
 * with "Observable".
 * 
 * So if this annotation is put on a fr.lteconsulting.Person class,
 * it will generate an observable implementation with the following
 * fqn : fr.lteconsulting.ObservablePerson
 * 
 * @author Arnaud Tournier
 */
@Target( ElementType.TYPE )
@Retention( RetentionPolicy.CLASS )
public @interface Observable
{
}
