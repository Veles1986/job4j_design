package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {
    @Test
    void whenPairWithoutComment() {
        String path1 = "./data/pair_without_comment.properties";
        String path2 = "./data/app.properties";
        Config config1 = new Config(path1);
        Config config2 = new Config(path2);
        config1.load();
        config2.load();
        assertThat(config1.value("name")).isEqualTo("Petr Arsentev");
        assertThat(config2.value("hibernate.connection.driver_class")).isEqualTo("org.postgresql.Driver");
    }

    @Test
    void whenPairWithoutValue() {
        String path = "./data/app1.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairWithoutEqual() {
        String path = "./data/app2.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairWithTwoEqual() {
        String path = "./data/app3.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username")).isEqualTo("postgres=name");
    }

    @Test
    void whenPairWithEmptyLines() {
        String path = "./data/app4.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username")).isEqualTo("postgres");
    }
}