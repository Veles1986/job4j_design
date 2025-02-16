package ru.job4j.newcoll.tree;

import ru.job4j.collection.SimpleQueue;

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
}