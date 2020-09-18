package io;

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
        System.out.println(p2.isAbsolute());
        System.out.println(Files.exists(p2));
        System.out.println(p2);

        try {
            System.out.println(
                    Files.lines(p2)
                    .map(l -> (++i) + ": " + l)
                            .collect(Collectors.joining("\n"))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
