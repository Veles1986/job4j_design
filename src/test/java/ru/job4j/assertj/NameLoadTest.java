package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkNotContainsEqual() {
        NameLoad nameLoad = new NameLoad();
        String word = "senyaarseniy";
        assertThatThrownBy(() -> nameLoad.parse(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(word)
                .hasMessageContaining("the symbol '='");
    }

    @Test
    void checkStartsWithEqual() {
        NameLoad nameLoad = new NameLoad();
        String word = "=arseniy";
        assertThatThrownBy(() -> nameLoad.parse(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(word)
                .hasMessageContaining("a key");
    }

    @Test
    void checkEndsWithEqual() {
        NameLoad nameLoad = new NameLoad();
        String word = "senya=";
        assertThatThrownBy(() -> nameLoad.parse(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(word)
                .hasMessageContaining("a value");
    }

    @Test
    void checkLengthIs0() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }
}