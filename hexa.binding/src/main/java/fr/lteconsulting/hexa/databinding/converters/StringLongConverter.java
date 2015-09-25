package fr.lteconsulting.hexa.databinding.converters;

import fr.lteconsulting.hexa.databinding.AbstractConverter;

public class StringLongConverter extends AbstractConverter<String, Long> {

    public StringLongConverter() {
        super(String.class, Long.class);
    }

    @Override
    public Long convert(String value) {
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String convertBack(Long value) {
        try {
            return String.valueOf(value);
        } catch (Exception e) {
            return null;
        }
    }
}
