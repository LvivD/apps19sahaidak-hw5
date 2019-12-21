package ua.edu.ucu.stream.iterator;

import java.util.Iterator;

public class NullIterator implements Iterator<Integer> {
    public NullIterator() {

    }

    public boolean hasNext() {
        throw new IllegalArgumentException();
    }

    @Override
    public Integer next() {
        return null;
    }
}
