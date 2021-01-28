package io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Nio2FileDemo {
    static int i = 0;
    public static void main(String[] args) {
        Path p1 = Paths.get("./src/io/Nio2FileDemo.java");
        Path p2 = p1.toAbsolutePath();
        System.out.println(p2.normalize().toString());
        System.out.println(p2.isAbsolute());
        System.out.println(Files.exists(p2));

        try {
            System.out.println(
                Files.lines(p2)//.parallel()
                    .filter(l -> l.trim().length() > 0)
                    .map(l -> (++i) + ": " + l)
                    .collect(Collectors.joining("\n"))
            );
        } catch (FileNotFoundException ex) {
            System.out.printf("File '%s' not found.", p2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
