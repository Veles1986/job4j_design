package ru.job4j.newcoll.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AvlTreeTest {

    private AvlTree<Integer> tree;

    @BeforeEach
    void init() {
        tree = new AvlTree<>();
        ArrayList<Integer> list = new ArrayList<>(List.of(25, 10, 53, 3, 15, 33, 80, 1, 8, 11, 18, 32, 40, 70, 81));
        for (Integer num : list) {
            tree.insert(num);
        }
    }

    @Test
    void simmetricalOrderIsOk() {
        List<Integer> list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 3, 8, 10, 11, 15, 18, 25, 32, 33, 40, 53, 70, 80, 81);
    }

    @Test
    void preOrderIsOk() {
        List<Integer> list = tree.inPreOrder();
        assertThat(list).containsExactly(25, 10, 3, 1, 8, 15, 11, 18, 53, 33, 32, 40, 80, 70, 81);
    }

    @Test
    void postOrderIsOk() {
        List<Integer> list = tree.inPostOrder();
        assertThat(list).containsExactly(1, 8, 3, 11, 18, 15, 10, 32, 40, 33, 70, 81, 80, 53, 25);
    }

    @Test
    void maxAndMinIsOk() {
        assertThat(tree.maximum()).isEqualTo(81);
        assertThat(tree.minimum()).isEqualTo(1);
    }

    @Test
    void checkLeftRotation() {
        tree.remove(1);
        tree.remove(8);
        tree.remove(3);
        List<Integer> list = tree.inPostOrder();
        assertThat(list).containsExactly(11, 10, 18, 15, 32, 40, 33, 70, 81, 80, 53, 25);
    }

    @Test
    void checkRightRotation() {
        tree.remove(32);
        tree.remove(40);
        tree.remove(70);
        tree.remove(81);
        tree.remove(33);
        tree.remove(80);
        List<Integer> list = tree.inPostOrder();
        assertThat(list).containsExactly(1, 8, 3, 11, 10, 18, 53, 25, 15);
    }
}
