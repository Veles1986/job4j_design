package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<E> implements Iterable<E> {
    private int size;
    private int modCount;
    private Node<E> head;

    public void add(E value) {
        if (size == 0) {
            head = new ForwardLinked.Node<>(value, null);
        } else {
            ForwardLinked.Node<E> newNode = head;
            while (newNode.next != null) {
                newNode = newNode.next;
            }
            newNode.next = new ForwardLinked.Node<>(value, null);
        }
        size++;
        modCount++;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        ForwardLinked.Node<E> newNode = head;
        for (int i = 1; i <= index; i++) {
            newNode = newNode.next;
        }
        return newNode.item;
    }

    public E deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        E obj = head.item;
        head.item = null;
        head = head.next;
        return obj;
    }

    @Override
    public Iterator<E> iterator() {
        final int modCountStatic = modCount;
        return new Iterator<E>() {
            ForwardLinked.Node<E> newNode = head;
            ForwardLinked.Node<E> helpNode;
            @Override
            public boolean hasNext() {
                if (modCount != modCountStatic) {
                    throw new ConcurrentModificationException();
                }
                helpNode = newNode;
                return helpNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                newNode = newNode.next;
                return helpNode.item;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private ForwardLinked.Node<E> next;

        Node(E element, ForwardLinked.Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}