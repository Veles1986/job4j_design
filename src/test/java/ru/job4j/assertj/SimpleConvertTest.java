package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("Thor", "Iron Man", "Black Widow", "Captain America", "Hawkeye", "Hulk");
        assertThat(list).hasSize(6)
                .contains("Iron Man")
                .contains("Black Widow", Index.atIndex(2))
                .containsAnyOf("Spider Man", "Ant Man", "Thor")
                .containsOnly("Thor", "Iron Man", "Black Widow", "Captain America", "Hawkeye", "Hulk");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet(
                "Petr",
                "Alexander",
                "Michael",
                "Igor",
                "Dmitriy",
                "Marina",
                "Darya",
                "Artyom",
                "Igor",
                "Michael");
        assertThat(set).hasSize(8)
                .containsAnyOf("Vladislav", "Konstantin", "Igor")
                .contains("Marina", "Artyom", "Alexander")
                .contains("Igor", "Dmitriy", "Darya")
                .doesNotContain("Boris");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("Thor", "Iron Man", "Black Widow", "Captain America", "Hawkeye", "Hulk");
        assertThat(map).hasSize(6)
                .containsEntry("Iron Man", 1)
                .containsKeys("Thor", "Iron Man")
                .containsValues(3, 5, 1)
                .doesNotContainEntry("Thor", 5);
    }
}