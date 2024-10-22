package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(output.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArgsName validate(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        if (!Path.of(argsName.get("d")).isAbsolute()) {
            throw new IllegalArgumentException("Invalid path");
        }
        if (!argsName.get("e").startsWith(".")) {
            throw new IllegalArgumentException("Illegal format of files that are not archived");
        }
        if (!"zip".equals(argsName.get("o").split("\\.")[1])) {
            throw new IllegalArgumentException("The destination file must be in zip format");
        }
        return argsName;
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        ArgsName editedArgs = zip.validate(args);
        zip.packFiles(
                Search.search(
                        Path.of(editedArgs.get("d")),
                        path -> !path.toFile().getName().endsWith(editedArgs.get("e"))
                ),
                new File(editedArgs.get("o"))
        );
    }
}
