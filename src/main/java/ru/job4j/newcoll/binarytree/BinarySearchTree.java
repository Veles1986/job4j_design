package ru.job4j.newcoll.binarytree;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node root;

    public boolean put(T key) {
        boolean result;
        if (Objects.isNull(root)) {
            root = new Node(key);
            result = true;
        } else {
            result = put(root, key);
        }
        return result;
    }

    private boolean put(Node node, T key) {
        Node previous;
        Node thisNode = node;
        Node newNode = new Node(key);
        while (true) {
            previous = thisNode;
            if (newNode.key.compareTo(thisNode.key) < 0) {
                thisNode = thisNode.left;
                if (thisNode == null) {
                    previous.left = newNode;
                    break;
                }
            } else if (newNode.key.compareTo(thisNode.key) > 0) {
                thisNode = thisNode.right;
                if (thisNode == null) {
                    previous.right = newNode;
                    break;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean contains(T key) {
        return !(find(root, key) == null);
    }

    private Node find(Node node, T key) {
        Node previous;
        Node thisNode = node;
        Node newNode = new Node(key);
        while (true) {
            previous = thisNode;
            if (newNode.key.compareTo(thisNode.key) < 0) {
                thisNode = thisNode.left;
                if (thisNode == null) {
                    break;
                }
            } else if (newNode.key.compareTo(thisNode.key) > 0) {
                thisNode = thisNode.right;
                if (thisNode == null) {
                    break;
                }
            } else {
                return newNode;
            }
        }
        return null;
    }

    public boolean remove(T key) {
        return false;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        Node node = root;
        return inSymmetricalOrder(node, result);
    }

    private List<T> inSymmetricalOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inSymmetricalOrder(localRoot.left, list);
            list.add(localRoot.key);
            inSymmetricalOrder(localRoot.right, list);
        }
        return list;
    }

    public List<T> inPreOrder() {
        List<T> result = new ArrayList<>();
        Node node = root;
        return inPreOrder(node, result);
    }

    private List<T> inPreOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            list.add(localRoot.key);
            inPreOrder(localRoot.left, list);
            inPreOrder(localRoot.right, list);
        }
        return list;
    }

    public List<T> inPostOrder() {
        List<T> result = new ArrayList<>();
        Node node = root;
        return inPostOrder(node, result);
    }

    private List<T> inPostOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inPostOrder(localRoot.left, list);
            inPostOrder(localRoot.right, list);
            list.add(localRoot.key);
        }
        return list;
    }

    public T minimum() {
        return Objects.nonNull(root) ? minimum(root).key : null;
    }

    private Node minimum(Node node) {
        Node previous;
        Node thisNode = node;
        while (true) {
            previous = thisNode;
            thisNode = thisNode.left;
            if (thisNode == null) {
                return previous;
            }
        }
    }

    public T maximum() {
        return Objects.nonNull(root) ? maximum(root).key : null;
    }

    private Node maximum(Node node) {
        Node previous;
        Node thisNode = node;
        while (true) {
            previous = thisNode;
            thisNode = thisNode.right;
            if (thisNode == null) {
                return previous;
            }
        }
    }

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }

    private class Node implements VisualNode {
        private T key;
        private Node left;
        private Node right;

        public Node(T key) {
            this.key = key;
        }

        @Override
        public VisualNode getLeft() {
            return left;
        }

        @Override
        public VisualNode getRight() {
            return right;
        }

        @Override
        public String getText() {
            return key.toString();
        }
    }
}