package ua.edu.ucu.stream.iterator;

import java.util.Iterator;

public class StreamIterator implements Iterator<Integer> {

    private int[] values;
    private int i;
    private boolean isUsed = false;

    public StreamIterator(int[] values) {
        this.values = values;
        this.i = 0;
    }

    @Override
    public boolean hasNext() {
        if (isUsed) {
            throw new RuntimeException("Stream is closed");
        }
        boolean res = i < values.length;
        if (!res) {
            isUsed = true;
        }
        return res;
    }

    @Override
    public Integer next() {

        i += 1;
        return values[i - 1];
    }
}
