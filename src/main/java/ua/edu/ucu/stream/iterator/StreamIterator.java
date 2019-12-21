package ua.edu.ucu.stream.iterator;

import java.util.Iterator;

public class StreamIterator implements Iterator<Integer> {

    private int[] values;
    private int i;

    public StreamIterator(int[] values) {
        this.values = values;
        this.i = 0;
    }

    @Override
    public boolean hasNext() {
        return i < values.length;
    }

    @Override
    public Integer next() {
        i += 1;
        return values[i - 1];
    }

    public static Iterable<Integer> StreamIterable(int[] values) {
        return () -> new StreamIterator(values);
    }
}
