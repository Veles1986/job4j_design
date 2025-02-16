package ru.job4j.newcoll.tree;

import ru.job4j.collection.SimpleQueue;
import ru.job4j.collection.SimpleStack;

import java.util.*;

public class TreeUtils<T> {
    public int countNode(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("Root must be not null.");
        }
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        int result = 0;
        queue.push(root);
        while (!queue.isEmpty()) {
            result++;
            for (Node<T> node : queue.poll().getChildren()) {
                queue.push(node);
            }
        }
        return result;
    }

    public Iterable<T> findAll(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("Root must be not null.");
        }
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        List<T> result = new LinkedList<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            Node<T> subNode = queue.poll();
            result.add(subNode.getValue());
            for (Node<T> node : subNode.getChildren()) {
                queue.push(node);
            }
        }
        return result;
    }

    public boolean add(Node<T> root, T parent, T child) {
        Optional<Node<T>> parentNode = findByKey(root, parent);
        Optional<Node<T>> childNode = findByKey(root, child);
        if (parentNode.isPresent() && childNode.isEmpty()) {
            parentNode.get().getChildren().add(new Node<>(child));
            return true;
        }
        return false;
    }

    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException("Root must be not null.");
        }
        if (key == null) {
            throw new IllegalArgumentException("Key must be not null.");
        }
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> subNode = stack.pop();
            if (subNode.getValue().equals(key)) {
                return Optional.of(subNode);
            }
            for (Node<T> node : subNode.getChildren()) {
                stack.push(node);
            }
        }
        return Optional.empty();
    }

    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException("Root must be not null.");
        }
        if (key == null) {
            throw new IllegalArgumentException("Key must be not null.");
        }
        if (root.getValue().equals(key)) {
            return Optional.of(root);
        }
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);
        Optional<Node<T>> result = Optional.empty();
        while (!stack.isEmpty()) {
            Node<T> subNode = stack.pop();
            Iterator<Node<T>> iterator = subNode.getChildren().iterator();
            while (iterator.hasNext()) {
                Node<T> child = iterator.next();
                if (child.getValue().equals(key)) {
                    iterator.remove();
                    return Optional.of(child);
                }
                stack.push(child);
            }
        }
        return result;
    }
}