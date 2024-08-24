package ru.job4j.generics;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenUsernameIsBradPitt() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Brad Pitt"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Brad Pitt");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Brad Pitt"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUsernameIsBradPitt() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Brad Pitt"));
        store.add(new Role("1", "Angelina Jolie"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Brad Pitt");
    }

    @Test
    void whenReplaceThenUsernameIsAngelinaJolie() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Brad Pitt"));
        store.replace("1", new Role("1", "Angelina Jolie"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Angelina Jolie");
    }

    @Test
    void whenNoReplaceUserThenNoChangeUsername() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Brad Pitt"));
        store.replace("10", new Role("10", "Angelina Jolie"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Brad Pitt");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Brad Pitt"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenUsernameIsBradPitt() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Brad Pitt"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Brad Pitt");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Brad Pitt"));
        boolean result = store.replace("1", new Role("1", "Angelina Jolie"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Brad Pitt"));
        boolean result = store.replace("10", new Role("10", "Angelina Jolie"));
        assertThat(result).isFalse();
    }

}