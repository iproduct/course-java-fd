package io;

import java.io.*;

public class TryWithResources {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(
                new FileReader("src/io/TryWithResources.java"));
            PrintWriter pw = new PrintWriter(
                new BufferedWriter(
                        new FileWriter("TryWithResources.out")))
        ) {
            String s;
            int i = 0;
            while ((s = br.readLine()) != null) {
                pw.printf("%3d: %s%n", ++i, s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
