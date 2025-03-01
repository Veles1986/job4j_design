package ru.job4j.newcoll.fortaskavl;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class TreeAVLMapTest {

    @Test
    void whenEmptyTree() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertThat(tree.values()).isEmpty();
        assertThat(tree.keySet()).isEmpty();
    }

    @Test
    void whenAddOneElemThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertThat(tree.put(1, "11")).isTrue();
        assertThat(tree.values()).hasSize(1)
                .containsExactly("11");
        assertThat(tree.keySet()).hasSize(1)
                .containsExactly(1);
    }

    @Test
    void whenAddSevenElemThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertThat(tree.put(1, "11")).isTrue();
        assertThat(tree.put(2, "22")).isTrue();
        assertThat(tree.put(3, "33")).isTrue();
        assertThat(tree.put(4, "44")).isTrue();
        assertThat(tree.put(5, "55")).isTrue();
        assertThat(tree.put(6, "66")).isTrue();
        assertThat(tree.put(7, "77")).isTrue();
        assertThat(tree.values()).hasSize(7)
                .containsExactly("11", "22", "33", "44", "55", "66", "77");
        assertThat(tree.keySet()).hasSize(7)
                .containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenUpdateValueThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.put(1, "11");
        tree.put(2, "22");
        tree.put(3, "33");
        tree.put(4, "44");
        tree.put(5, "55");
        tree.put(6, "66");
        tree.put(7, "77");
        assertThat(tree.put(5, "555")).isTrue();
        assertThat(tree.values()).hasSize(7)
                .containsExactly("11", "22", "33", "44", "555", "66", "77");
        assertThat(tree.keySet()).hasSize(7)
                .containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenDeleteKeyThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.put(1, "11");
        tree.put(2, "22");
        tree.put(3, "33");
        tree.put(4, "44");
        tree.put(5, "55");
        tree.put(6, "66");
        tree.put(7, "77");
        assertThat(tree.remove(5)).isTrue();
        assertThat(tree.remove(0)).isFalse();
        assertThat(tree.values()).hasSize(6)
                .containsExactly("11", "22", "33", "44", "66", "77");
        assertThat(tree.keySet()).hasSize(6)
                .containsExactly(1, 2, 3, 4, 6, 7);
    }

    @Test
    void whenGetKeyThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.put(1, "11");
        tree.put(2, "22");
        tree.put(3, "33");
        tree.put(4, "44");
        tree.put(5, "55");
        tree.put(6, "66");
        tree.put(7, "77");
        assertThat(tree.get(5)).isEqualTo("55");
        assertThat(tree.get(0)).isNull();
    }

    @Test
    void checkLeftRotation() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.put(25, "25");
        tree.put(10, "10");
        tree.put(53, "53");
        tree.put(3, "3");
        tree.put(15, "15");
        tree.put(33, "33");
        tree.put(80, "80");
        tree.put(1, "1");
        tree.put(8, "8");
        tree.put(11, "11");
        tree.put(18, "18");
        tree.put(32, "32");
        tree.put(40, "40");
        tree.put(70, "70");
        tree.put(81, "81");
        tree.remove(1);
        tree.remove(8);
        tree.remove(3);
        Collection<String> set = tree.values();
        assertThat(set).containsExactly("10", "11", "15", "18", "25", "32", "33", "40", "53", "70", "80", "81");
    }

    @Test
    void checkRightRotation() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.put(25, "25");
        tree.put(10, "10");
        tree.put(53, "53");
        tree.put(3, "3");
        tree.put(15, "15");
        tree.put(33, "33");
        tree.put(80, "80");
        tree.put(1, "1");
        tree.put(8, "8");
        tree.put(11, "11");
        tree.put(18, "18");
        tree.put(32, "32");
        tree.put(40, "40");
        tree.put(70, "70");
        tree.put(81, "81");
        tree.remove(32);
        tree.remove(40);
        tree.remove(70);
        tree.remove(81);
        tree.remove(33);
        tree.remove(80);
        Collection<String> set = tree.values();
        assertThat(set).containsExactly("1", "3", "8", "10", "11", "15", "18", "25", "53");
    }
}