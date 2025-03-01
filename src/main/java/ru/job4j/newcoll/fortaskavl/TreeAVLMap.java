package ru.job4j.newcoll.fortaskavl;

import java.util.*;

public class TreeAVLMap<T extends Comparable<T>, V> {
    private Node root;

    public boolean put(T key, V value) {
        boolean result = false;
        if (Objects.nonNull(key) && Objects.nonNull(value)) {
            root = put(key, value, root);
            result = true;
        }
        return result;
    }

    private Node put(T key, V value, Node node) {
        Node result = new Node(key, value);
        if (Objects.nonNull(node)) {
            int comparisonResult = key.compareTo(node.key);
            if (comparisonResult < 0) {
                node.left = put(key, value, node.left);
            } else if (comparisonResult > 0) {
                node.right = put(key, value, node.right);
            } else {
                node = result;
            }
            updateHeight(node);
            result = balance(node);
        } else {
            node = result;
        }
        return result;
    }

    public boolean remove(T key) {
        boolean result = false;
        if (Objects.nonNull(key) && Objects.nonNull(root) && contains(key)) {
            root = remove(root, key);
            result = true;
        }
        return result;
    }

    private Node remove(Node node, T key) {
        if (node == null) {
            return null;
        }
        int comparisonResult = key.compareTo(node.key);
        if (comparisonResult < 0) {
            node.left = remove(node.left, key);
        } else if (comparisonResult > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                if (node.left.height > node.right.height) {
                    T heir = maximum(node.left).key;
                    node.key = heir;
                    node.left = remove(node.left, heir);
                } else {
                    T heir = minimum(node.right).key;
                    node.key = heir;
                    node.right = remove(node.right, heir);
                }
            }
        }
        updateHeight(node);
        return balance(node);
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

    public boolean contains(T key) {
        if (root == null) {
            return false;
        }
        return !(find(key) == null);
    }

    private Node find(T key) {
        Node previous;
        Node thisNode = root;
        Node newNode = new Node(key, null);
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
                return thisNode;
            }
        }
        return null;
    }

    public V get(T key) {
        Node node = find(key);
        if (Objects.nonNull(node)) {
            return node.value;
        }
        return null;
    }

    public Set<T> keySet() {
        Set<T> result = new HashSet<>();
        Node node = root;
        return keySet(node, result);
    }

    private Set<T> keySet(Node localRoot, Set<T> set) {
        if (localRoot != null) {
            keySet(localRoot.left, set);
            set.add(localRoot.key);
            keySet(localRoot.right, set);
        }
        return set;
    }

    public Collection<V> values() {
        Collection<V> result = new ArrayList<>();
        Node node = root;
        return values(node, result);
    }

    private Collection<V> values(Node localRoot, Collection<V> list) {
        if (localRoot != null) {
            values(localRoot.left, list);
            list.add(localRoot.value);
            values(localRoot.right, list);
        }
        return list;
    }

    private void updateHeight(Node node) {
        int leftNodeHeight = Objects.isNull(node.left) ? -1 : node.left.height;
        int rightNodeHeight = Objects.isNull(node.right) ? -1 : node.right.height;
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        node.balanceFactor = rightNodeHeight - leftNodeHeight;
    }

    private Node balance(Node node) {
        Node result = node;
        if (node.balanceFactor < -1) {
            if (node.left.balanceFactor >= 0) {
                result = leftRightCase(node);
            } else {
                result = rightRotation(node);
            }
        } else if (node.balanceFactor > 1) {
            if (node.right.balanceFactor >= 0) {
                result = leftRotation(node);
            } else {
                result = rightLeftCase(node);
            }
        }
        return result;
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    private Node leftRotation(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    private Node rightRotation(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    private class Node {
        private int balanceFactor;
        private T key;
        private V value;
        private int height;
        private Node left;
        private Node right;

        Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
