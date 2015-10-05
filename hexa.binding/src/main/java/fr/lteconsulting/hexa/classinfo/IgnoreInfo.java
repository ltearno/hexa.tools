package fr.lteconsulting.hexa.classinfo;

import java.lang.annotation.*;

/**
 * Ignore this field or method from the class info generation.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD, ElementType.METHOD})
public @interface IgnoreInfo {
}
