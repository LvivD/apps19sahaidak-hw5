package ua.edu.ucu.stream.iterator;

import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.function.IntUnaryOperator;
import ua.edu.ucu.stream.IntStream;

import java.util.Iterator;

public class FlatMapIterator implements Iterator<Integer> {

    private Iterator<Integer> prevIterator;
    private IntToIntStreamFunction func;
    int[] nextArr;
    int i = 0;
    int next;

    public FlatMapIterator(Iterator<Integer> prevIterator, IntToIntStreamFunction func) {
        prevIterator.hasNext();
        this.prevIterator = prevIterator;
        this.func = func;
        nextArr = func.applyAsIntStream(prevIterator.next()).toArray();
    }

    @Override
    public boolean hasNext() {
        return this.prevIterator.hasNext() || i < nextArr.length;
    }

    @Override
    public Integer next() {
        if (i < nextArr.length) {
            next = nextArr[i];
            i++;
        }
        else {
            nextArr = func.applyAsIntStream(prevIterator.next()).toArray();
            i = 0;
            next = nextArr[i];
            i++;
        }
        return next;
    }
}
