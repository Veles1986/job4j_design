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
            head = new Node<>(value, null);
        } else {
            Node<E> newNode = head;
            while (newNode.next != null) {
                newNode = newNode.next;
            }
            newNode.next = new Node<>(value, null);
        }
        size++;
        modCount++;
    }

    public void addFirst(E value) {
        head = new Node<>(value, head);
        size++;
        modCount++;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> newNode = head;
        for (int i = 1; i <= index; i++) {
            newNode = newNode.next;
        }
        return newNode.item;
    }

    public E deleteFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        final E obj = head.item;
        final Node<E> newNode = head.next;
        head.next = null;
        head.item = null;
        head = newNode;
        size--;
        modCount++;
        return obj;
    }

    @Override
    public Iterator<E> iterator() {
        final int modCountStatic = modCount;
        return new Iterator<E>() {
            Node<E> newNode = head;
            Node<E> helpNode;
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
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}