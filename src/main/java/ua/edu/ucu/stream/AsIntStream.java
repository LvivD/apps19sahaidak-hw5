package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import ua.edu.ucu.stream.iterator.*;

import java.util.ArrayList;
import java.util.Iterator;

public class AsIntStream implements IntStream {

    private Iterator iterator;

    private AsIntStream(int[] values) {
        this.iterator = new StreamIterator(values);
    }

    private AsIntStream(Iterator iterator) {
        this.iterator = iterator;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    private Iterable<Integer> toIterable(){
        return ()->iterator;
    }

    private void ifEmpty() {
        if (!this.iterator.hasNext()) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Double average() {
        ifEmpty();
        int sum = 0;
        int len = 0;
        for (int i: toIterable()) {
            sum += i;
            len += 1;
        }
        this.iterator = new NullIterator();
        return (double) sum/len;
    }

    @Override
    public Integer max() {
        ifEmpty();
        int max = Integer.MIN_VALUE;
        for (int i: toIterable()) {
            if (i > max) {
                max = i;
            }
        }
        this.iterator = new NullIterator();
        return max;
    }

    @Override
    public Integer min() {
        int min = Integer.MAX_VALUE;
        for (int i: toIterable()) {
            if (i < min) {
                min = i;
            }
        }
        this.iterator = new NullIterator();
        return min;
    }

    @Override
    public long count() {
        ifEmpty();
        int len = 0;
        for (int i: toIterable()) {
            len += 1;
        }
        this.iterator = new NullIterator();
        return len;
    }

    @Override
    public Integer sum() {
        ifEmpty();
        int sum = 0;
        for (int i: toIterable()) {
            sum += i;
        }
        this.iterator = new NullIterator();
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterIterator(this.iterator, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int i: toIterable()) {
            action.accept(i);
        }
        this.iterator = new NullIterator();
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapIterator(this.iterator, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatMapIterator(this.iterator, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int res = identity;
        for (int i: toIterable()) {
            res = op.apply(res, i);
        }
        this.iterator = new NullIterator();
        return res;
    }

    @Override
    public int[] toArray() {
        ArrayList<Integer> newList = new ArrayList<>();
        for (int i: toIterable()) {
            newList.add(i);
        }
        int[] newArray = new int[newList.size()];
        for (int i = 0; i < newList.size(); i++) {
            newArray[i] = newList.get(i);
        }
        this.iterator = new NullIterator();
        return newArray;
    }

}