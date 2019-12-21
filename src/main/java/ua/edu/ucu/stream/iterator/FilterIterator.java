package ua.edu.ucu.stream.iterator;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;

public class FilterIterator implements Iterator<Integer> {

    private Iterator<Integer> prevIterator;
    private IntPredicate predicate;
    int next;
    boolean hasNext;

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
        }
        else {
            hasNext = false;
        }
    }

    @Override
    public boolean hasNext() {
        return hasNext && predicate.test(next);
    }

    @Override
    public Integer next() {
        int realNext = next;
        if (prevIterator.hasNext()) {
            next = prevIterator.next();
            while (!predicate.test(next) && prevIterator.hasNext()) {
                next = prevIterator.next();
            }
        }
        else {
            hasNext = false;
        }

        return realNext;
    }
}
