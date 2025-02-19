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

    @Test (expected = RuntimeException.class)
    public void averageIfTerminal() {
        Double a = myStream.average();
        assertEquals(2.5, a, 0.0);
        int[] s = myStream.toArray();
    }

    @Test (expected = IllegalArgumentException.class)
    public void maxEmpty() {
        IntStream newStream = AsIntStream.of();
        int m = newStream.max();
    }

    @Test
    public void max() {
        int m = myStream.max();
        assertEquals(4, m);
    }

    @Test (expected = RuntimeException.class)
    public void maxIfTerminal() {
        int m = myStream.max();
        assertEquals(4, m);
        int[] s = myStream.toArray();
    }

    @Test (expected = IllegalArgumentException.class)
    public void minEmpty() {
        IntStream newStream = AsIntStream.of();
        int m = newStream.min();
    }

    @Test
    public void min() {
        int m = myStream.min();
        assertEquals(1, m);
    }

    @Test (expected = RuntimeException.class)
    public void minIfTerminal() {
        int m = myStream.min();
        assertEquals(1, m);
        int[] s = myStream.toArray();
    }

    @Test
    public void countEmpty() {
        IntStream newStream = AsIntStream.of();
        long n = newStream.count();
        assertEquals(0, n);
    }

    @Test
    public void count() {
        long n = myStream.count();
        assertEquals(4, n);
    }

    @Test (expected = RuntimeException.class)
    public void countIfTerminal() {
        long n = myStream.count();
        assertEquals(4, n);
        int[] s = myStream.toArray();
    }

    @Test
    public void countAfterFilter() {
        long n = myStream.filter(x -> x > 2).count();
        assertEquals(2, n);

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

    @Test (expected = RuntimeException.class)
    public void sumIfTerminal() {
        int s = myStream.sum();
        assertEquals(10, s);
        int ss = myStream.sum();
    }

    @Test (expected = RuntimeException.class)
    public void sumAfterMap() {
        int s = myStream.map(x -> x * x).sum();
        assertEquals(1 + 4 + 9 + 16, s);
        int ss = myStream.sum();
    }

    @Test
    public void filter() {
        int[] s = myStream.filter(x -> x > 2).toArray();
        assertArrayEquals(new int[]{3,4}, s);
    }

    @Test
    public void map() {
        int[] s = myStream.map(x -> x*2).toArray();
        assertArrayEquals(new int[]{2,4,6,8}, s);
    }

    @Test
    public void flatMap() {
        int[] s = myStream.flatMap(x -> AsIntStream.of(x, x, x)).toArray();
        assertArrayEquals(new int[]{1,1,1,2,2,2,3,3,3,4,4,4}, s);
    }

    @Test
    public void reduce() {
        int res = myStream.reduce(1, (mul, x) -> mul *= x);
        assertEquals(24, res);
    }

    @Test (expected = RuntimeException.class)
    public void reduceAsTerminal() {
        int res = myStream.reduce(1, (mul, x) -> mul *= x);
        int[] s = myStream.toArray();
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

    @Test (expected = RuntimeException.class)
    public void testFilterMapReduceTerminal() {
        int res = myStream.filter(x -> x > -1).map(x -> x).reduce(0, (l, x) -> l = x);
        assertEquals(4, res);
        int[] a = myStream.toArray();
//        assertArrayEquals(new int[]{}, a);

    }
}