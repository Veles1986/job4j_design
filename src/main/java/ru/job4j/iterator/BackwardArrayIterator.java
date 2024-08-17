package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardArrayIterator implements Iterator<Integer> {
    private final int[] data;
    private int point;

    public BackwardArrayIterator(int[] data) {
        point = data.length - 1;
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return point > -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point--];
    }
}