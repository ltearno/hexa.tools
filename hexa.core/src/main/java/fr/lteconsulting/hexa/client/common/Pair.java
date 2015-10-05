package fr.lteconsulting.hexa.client.common;

public class Pair<T, U> {
    public T first;
    public U last;
    public Pair(T first, U last) {
        this.first = first;
        this.last = last;
    }

    public static <T, U> Pair<T, U> create(T first, U last) {
        return new Pair<T, U>(first, last);
    }

    @Override
    public String toString() {
        return "Pair[" + first + "," + last + "]";
    }
}
