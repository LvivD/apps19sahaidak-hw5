package ua.edu.ucu.stream.iterator;

import ua.edu.ucu.function.IntUnaryOperator;

import java.util.Iterator;

public class MapIterator implements Iterator<Integer> {

    private Iterator<Integer> prevIterator;
    private IntUnaryOperator mapper;
    private boolean isUsed = false;

    public MapIterator(Iterator<Integer> prevIterator, IntUnaryOperator mapper) {
        this.prevIterator = prevIterator;
        this.mapper = mapper;
    }

    @Override
    public boolean hasNext() {
        if (isUsed) {
            throw new RuntimeException("Stream is closed");
        }
        boolean res = this.prevIterator.hasNext();
        if (!res) {
            isUsed = true;
        }
        return res;
    }

    @Override
    public Integer next() {

        return mapper.apply(prevIterator.next());
    }
}
