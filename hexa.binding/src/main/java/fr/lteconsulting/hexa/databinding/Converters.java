package fr.lteconsulting.hexa.databinding;

import fr.lteconsulting.hexa.databinding.converters.StringDoubleConverter;
import fr.lteconsulting.hexa.databinding.converters.StringFloatConverter;
import fr.lteconsulting.hexa.databinding.converters.StringIntegerConverter;
import fr.lteconsulting.hexa.databinding.converters.StringLongConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of implementations of standard converters.
 *
 * @author Arnaud Tournier
 *         (c) LTE Consulting - 2015
 *         http://www.lteconsulting.fr
 */
public class Converters {

    private static List<AbstractConverter<?, ?>> converters = new ArrayList<>();

    static {
        converters.add(new StringIntegerConverter());
        converters.add(new StringDoubleConverter());
        converters.add(new StringFloatConverter());
        converters.add(new StringLongConverter());
    }

    /**
     * Dynamically finds the appropriate converter to use between two objects
     * of different classes.<br/>
     * Returns <code>null</code> if no appropriate converter is found.
     *
     * @param from The input class type
     * @param to   The output class type
     */
    public static Converter findConverter(Class<?> from, Class<?> to) {
        from = getBoxedType(from);
        to = getBoxedType(to);

        Converter result;
        for(AbstractConverter<?, ?> converter : converters) {
            result = converter.determine(from, to);
            if(result != null) {
                return result;
            }
        }
        return null;
    }

    private static Class<?> getBoxedType(Class<?> c) {
        if (c == int.class)
            return Integer.class;
        if (c == char.class)
            return Character.class;
        if (c == double.class)
            return Double.class;
        if (c == float.class)
            return Float.class;
        if (c == long.class)
            return Long.class;
        return c;
    }
}
