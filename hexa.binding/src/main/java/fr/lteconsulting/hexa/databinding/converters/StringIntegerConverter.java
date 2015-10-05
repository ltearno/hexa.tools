package fr.lteconsulting.hexa.databinding.converters;

import fr.lteconsulting.hexa.databinding.AbstractConverter;

public class StringIntegerConverter extends AbstractConverter<String, Integer> {

    public StringIntegerConverter() {
        super(String.class, Integer.class);
    }

    @Override
    public Integer convert(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String convertBack(Integer value) {
        try {
            return String.valueOf(value);
        } catch (Exception e) {
            return null;
        }
    }
}
