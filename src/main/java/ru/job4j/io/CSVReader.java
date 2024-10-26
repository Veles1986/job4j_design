package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        Path source = Path.of(argsName.get("path"));
        String delimiter = argsName.get("delimiter");
        Path destination = argsName.get("out").equals("stdout") ? null : Path.of(argsName.get("out"));
        List<String> column = List.of(argsName.get("filter").split(","));
        List<List<String>> result = new ArrayList<>();
        try (Scanner input = new Scanner(source).useDelimiter("\r")) {
            String[] line = input.next().split(delimiter);
            List<Integer> numbers = new LinkedList<>();
            List<String> helpList = List.of(line);
            for (String col : column) {
                if (helpList.contains(col)) {
                    numbers.add(helpList.indexOf(col));
                }
            }
            result.add(getList(line, numbers));
            while (input.hasNext()) {
                line = input.next().split(delimiter);
                result.add(getList(line, numbers));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (destination == null) {
            for (List<String> strings : result) {
                System.out.println(getString(strings, delimiter));
            }
        } else {
            try (PrintWriter writer = new PrintWriter(destination.toString())) {
                for (List<String> strings : result) {
                    writer.println(getString(strings, delimiter));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<String> getList(String[] line, List<Integer> numbers) {
        List<String> result = new ArrayList<>();
        for (Integer integer : numbers) {
            result.add(line[integer]);
        }
        return result;
    }

    private static String getString(List<String> string, String delimiter) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < string.size(); i++) {
            stringBuilder.append(string.get(i).trim());
            if (i != string.size() - 1) {
                stringBuilder.append(delimiter);
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        if (!argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("Source file must be in .csv format");
        }
        if (argsName.get("delimiter").length() != 1) {
            throw new IllegalArgumentException("The delimiter must be a character");
        }
        if (!(argsName.get("out").equals("stdout") || Path.of(argsName.get("out")).isAbsolute())) {
            throw new IllegalArgumentException("Illegal argument of parameter \"out\"");
        }
        handle(argsName);
    }
}