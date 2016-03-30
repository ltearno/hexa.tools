package fr.lteconsulting.hexa.databinding.converters;

import fr.lteconsulting.hexa.databinding.Converter;

/**
 * Converter for enum types.
 * @author Ben Dol
 */
public class EnumConverter implements Converter {

    private Enum<?>[] values;

    private boolean toOrdinal;

    /**
     * Default constructor, won't use the enums ordinal value by default.
     * @see #EnumConverter(Enum[] values, boolean toOrdinal)
     */
    public EnumConverter(Enum<?>[] values) {
        this(values, false);
    }

    /**
     * Constructor to define whether or not the enum should be used as its
     * ordinal value, otherwise it will simply call toString on the enum.
     */
    public EnumConverter(Enum<?>[] values, boolean toOrdinal) {
        this.values = values;
        this.toOrdinal = toOrdinal;
    }

    @Override
    public Object convert(Object value) {
        if(toOrdinal) {
            for(int i = 0; i < values.length; ++i) {
                if(values[i].equals(value))
                    return i;
            }
        }
        return value.toString();
    }

    @Override
    public Object convertBack(Object value) {
        Enum<?> e = null;
        try {
            if (value instanceof Integer) {
                e = values[(Integer) value];
            }
            else if (value instanceof String) {
                try {
                    // Find by ordinal value first
                    e = values[Integer.parseInt((String)value)];
                }
                catch(NumberFormatException | IndexOutOfBoundsException ex) {
                    // Failed to find by ordinal value
                    // Try find by enum name string
                    for(Enum<?> val : values) {
                        if(val.name().equals(value)) {
                            e = val;
                            break;
                        }
                    }
                    assert e != null;
                }
            }
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Attempted to convert an " +
                "enum using an invalid value: ", ex);
        }
        return e;
    }
}