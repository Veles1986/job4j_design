package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles extends SimpleFileVisitor<Path> {

    Predicate predicate;
    List<Path> listOfPaths = new ArrayList<>();

    SearchFiles(Predicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!predicate.test(file)) {
            return FileVisitResult.CONTINUE;
        }
        listOfPaths.add(file.getFileName());
        return FileVisitResult.CONTINUE;
    }

    public List<Path> getPaths() {
        return listOfPaths;
    }
}
