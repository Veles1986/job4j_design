package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Необходимо указать два параметра: начальную папку и расширение файла.");
        }

        Path start = Paths.get(args[0]);
        if (!Files.exists(start) || !Files.isDirectory(start)) {
            throw new IllegalArgumentException("Указанная папка не существует или не является директорией: " + args[0]);
        }

        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Расширение файла должно начинаться с точки. Например: .txt");
        }
    }
}