package ru.job4j.io.duplicates;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, ArrayList<Path>> mapOfFiles = new HashMap<>();

    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), duplicatesVisitor);
        duplicatesVisitor.printDuplicates();
    }

    public void printDuplicates() {
        for (FileProperty fileProperty : mapOfFiles.keySet()) {
            if (mapOfFiles.get(fileProperty).size() > 1) {
                for (Path path : mapOfFiles.get(fileProperty)) {
                    System.out.println(path);
                }
            }
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        mapOfFiles.computeIfAbsent(new FileProperty(file.toFile().length(), file.getFileName().toString()), initialize -> new ArrayList<>()).add(file.toAbsolutePath());
        return FileVisitResult.CONTINUE;
    }
}