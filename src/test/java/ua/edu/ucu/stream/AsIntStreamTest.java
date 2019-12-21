package ua.edu.ucu.stream;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AsIntStreamTest {

    private IntStream myStream;

    @Before
    public void setUp() throws Exception {
        myStream = AsIntStream.of(1,2,3,4);
    }

    @Test
    public void of() {
    }

    @Test (expected = IllegalArgumentException.class)
    public void averageEmpty() {
        IntStream newStream = AsIntStream.of();
        Double a = newStream.average();
    }

    @Test
    public void average() {
        Double a = myStream.average();
        assertEquals(2.5, a, 0.0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void averageIfTerminal() {
        Double a = myStream.average();
        assertEquals(2.5, a, 0.0);
        Double aa = myStream.average();
    }

    @Test
    public void max() {
    }

    @Test
    public void min() {
    }

    @Test
    public void count() {
    }

    @Test (expected = IllegalArgumentException.class)
    public void sumEmpty() {
        IntStream newStream = AsIntStream.of();
        int s = newStream.sum();
        assertEquals(0, s);
    }

    @Test
    public void sum() {
        int s = myStream.sum();
        assertEquals(10, s);
    }

    @Test (expected = IllegalArgumentException.class)
    public void sumIfTerminal() {
        int s = myStream.sum();
        assertEquals(10, s);
        int ss = myStream.sum();
    }

    @Test
    public void filter() {
    }

    @Test
    public void forEach() {
    }

    @Test
    public void map() {
    }

    @Test
    public void flatMap() {
    }

    @Test
    public void reduce() {
    }

    @Test
    public void toArray() {
        assertArrayEquals(new int[]{1,2,3,4}, myStream.toArray());
    }

    @Test
    public void toArrayEmpty() {
        IntStream newStream = AsIntStream.of();
        assertArrayEquals(new int[]{}, newStream.toArray());
    }
}