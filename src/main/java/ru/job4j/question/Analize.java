package ru.job4j.question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, User> previousMap = new HashMap<>();
        Map<Integer, User> currentMap = new HashMap<>();
        for (User user : previous) {
            previousMap.put(user.getId(), user);
        }
        for (User user : current) {
            currentMap.put(user.getId(), user);
        }
        Set<User> addedUsers = new HashSet<>();
        Set<User> deletedUsers = new HashSet<>();
        Set<User> changedUsers = new HashSet<>();
        for (User user : current) {
            if (!previousMap.containsKey(user.getId())) {
                addedUsers.add(user);
            }
        }
        for (User user : previous) {
            if (!currentMap.containsKey(user.getId())) {
                deletedUsers.add(user);
            }
        }
        for (User user : previous) {
            User currentUser = currentMap.get(user.getId());
            if (currentUser != null && !user.getName().equals(currentUser.getName())) {
                changedUsers.add(currentUser);
            }
        }
        return new Info(addedUsers.size(), changedUsers.size(), deletedUsers.size());
    }

}