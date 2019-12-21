package ua.edu.ucu.stream.iterator;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;

public class FilterIterator implements Iterator<Integer> {

    private Iterator<Integer> prevIterator;
    private IntPredicate predicate;
    private int next;
    private boolean hasNext;
    private boolean isUsed = false;

    public FilterIterator(Iterator<Integer> prevIterator, IntPredicate predicate) {
        prevIterator.hasNext();
        this.prevIterator = prevIterator;
        this.predicate = predicate;
        if (prevIterator.hasNext()) {
            hasNext = true;
            next = prevIterator.next();
            while (!predicate.test(next) && prevIterator.hasNext()) {
                next = prevIterator.next();
            }
        } else {
            hasNext = false;
        }
    }

    @Override
    public boolean hasNext() {
        if (isUsed) {
            throw new RuntimeException("Stream is closed");
        }
        boolean res = hasNext && predicate.test(next);
        if (!res) {
            isUsed = false;
        }
        return res;
    }

    @Override
    public Integer next() {

        int realNext = next;
        if (prevIterator.hasNext()) {
            next = prevIterator.next();
            while (!predicate.test(next) && prevIterator.hasNext()) {
                next = prevIterator.next();
            }
        } else {
            hasNext = false;
        }

        return realNext;
    }
}
