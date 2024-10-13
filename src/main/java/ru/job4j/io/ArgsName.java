package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String argument : args) {
            if (checkForPattern(argument)) {
                String[] split = argument.split("=", 2);
                values.put(split[0].split("-", 2)[1], split[1]);
            }
        }
    }

    private boolean checkForPattern(String keyValue) {
        String[] splitFirst = keyValue.split("=", 2);
        if (splitFirst.length == 1) {
            throw new IllegalArgumentException("Error: This argument '" + keyValue + "' does not contain an equal sign");
        }
        String[] splitSecond = splitFirst[0].split("-", 2);
        if (!splitSecond[0].isEmpty()) {
            throw new IllegalArgumentException("Error: This argument '" + keyValue + "' does not start with a '-' character");
        }
        if (splitSecond[1].isEmpty()) {
            throw new IllegalArgumentException("Error: This argument '" + keyValue + "' does not contain a key");
        }
        if (splitFirst[1].isEmpty()) {
            throw new IllegalArgumentException("Error: This argument '" + keyValue + "' does not contain a value");
        }
        return true;
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}