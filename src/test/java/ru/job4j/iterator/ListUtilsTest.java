package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterLast() {
        ListUtils.addAfter(input, 1, 7);
        assertThat(input).hasSize(3).containsSequence(1, 3, 7);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveWithPredicateIsTrue() {
        ListUtils.removeIf(input, i -> i < 2);
        assertThat(input).hasSize(1).containsExactly(3);
    }

    @Test
    void whenRemoveWithPredicateIsFalse() {
        ListUtils.removeIf(input, i -> i > 5);
        assertThat(input).hasSize(2).containsExactly(1, 3);
    }

    @Test
    void whenReplaceWithPredicateIsTrue() {
        ListUtils.replaceIf(input, i -> i < 2, 4);
        assertThat(input).hasSize(2).containsExactly(4, 3);
    }

    @Test
    void whenReplaceWithPredicateIsFalse() {
        ListUtils.replaceIf(input, i -> i > 5, 4);
        assertThat(input).hasSize(2).containsExactly(1, 3);
    }

    @Test
    void whenRemoveAllThenTrue() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addAfter(input, 3, 5);
        ListUtils.removeAll(input, Arrays.asList(1, 2, 3));
        assertThat(input).hasSize(2).containsExactly(4, 5);
    }

    @Test
    void whenRemoveAllThenFalse() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addAfter(input, 3, 5);
        ListUtils.removeAll(input, Arrays.asList(6, 7, 8));
        assertThat(input).hasSize(5).containsExactly(1, 2, 3, 4, 5);
    }
}