package ru.job4j.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class SimpleQueue<T> implements Queue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private int inputCount;
    private int outputCount;

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public boolean offer(Object o) {
        return false;
    }

    @Override
    public T remove() {
        return null;
    }

    public T poll() {
        if (outputCount == 0) {
            if (inputCount == 0) {
                throw new NoSuchElementException("Queue is empty");
            }
            while (inputCount != 0) {
                output.push(input.pop());
                outputCount++;
                inputCount--;
            }
        }
        outputCount--;
        return output.pop();
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    public void push(T value) {
        input.push(value);
        inputCount++;
    }

    @Override
    public int size() {
        return inputCount + outputCount;
    }

    public boolean isEmpty() {
        return outputCount == 0 && inputCount == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}