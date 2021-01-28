package io;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileDemo {
    public static final String  DIR = "."; // + File.separator + "src"; //+ File.separator + "io";
    public List<File> listAllFiles(String dirName) {
        File dir = new File(dirName); // directory
        if(dir.isDirectory()) {
            return Arrays.asList(dir.listFiles());
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        FileDemo demo = new FileDemo();
        demo.listAllFiles(DIR).forEach(file -> {
            System.out.printf("%s [%d bytes]- %s%n", file, file.length(), file.isDirectory()? "Dir" : "File");
        });
    }
}
