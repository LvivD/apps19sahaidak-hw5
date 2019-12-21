package ua.edu.ucu.stream.iterator;

import ua.edu.ucu.function.IntUnaryOperator;

import java.util.Iterator;

public class MapIterator implements Iterator<Integer> {

    private Iterator<Integer> prevIterator;
    private IntUnaryOperator mapper;
    int next;

    public MapIterator(Iterator<Integer> prevIterator, IntUnaryOperator mapper) {
        prevIterator.hasNext();
        this.prevIterator = prevIterator;
        this.mapper = mapper;
    }

    @Override
    public boolean hasNext() {
        return this.prevIterator.hasNext();
    }

    @Override
    public Integer next() {
        return mapper.apply(prevIterator.next());
    }
}
