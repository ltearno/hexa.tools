package fr.lteconsulting.hexa.databinding;

public abstract class AbstractConverter<A, B> implements BidirectionalConverter<A, B> {

    private final Class<A> aClass;
    private final Class<B> bClass;

    public AbstractConverter(Class<A> aClass, Class<B> bClass) {
        this.aClass = aClass;
        this.bClass = bClass;
    }

    /**
     * Try determine if this converter can convert the given types
     * and return the appropriate converter (in the case of reversals).
     */
    public Converter determine(Class<?> aType, Class<?> bType) {
        if(aType.equals(aClass) && bType.equals(bClass)) {
            return this;
        } else if(aType.equals(bClass) && bType.equals(aClass)) {
            return reverse();
        }
        return null;
    }

    @Override
    public Converter<B, A> reverse() {
        return new AbstractConverter<B, A>(bClass, aClass) {
            @Override
            public A convert(B value) {
                return AbstractConverter.this.convertBack(value);
            }
            @Override
            public B convertBack(A value) {
                return AbstractConverter.this.convert(value);
            }
        };
    }
}
