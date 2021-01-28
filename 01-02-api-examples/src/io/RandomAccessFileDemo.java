package io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {

    static String file = "rtest.dat";

    static void display() throws IOException {
        RandomAccessFile rf = new RandomAccessFile(file, "r");
        for (int i = 0; i < 10; i++)
            System.out.println(
                    "Value " + i + ": " + rf.readDouble());
        System.out.println(rf.readUTF());
        rf.close();
    }

    public static void main(String[] args)
            throws IOException {
        RandomAccessFile rf = new RandomAccessFile(file, "rw");
        for (int i = 0; i < 10; i++)
            rf.writeDouble(i * 3.14);
        rf.writeUTF("The end of the file");
        rf.close();
        display();
        rf = new RandomAccessFile(file, "rw");
        rf.seek(3*8);
        System.out.printf("Position: %d%n", rf.getChannel().position());
        rf.writeDouble( 42);
        rf.close();
        display();
    }
}
