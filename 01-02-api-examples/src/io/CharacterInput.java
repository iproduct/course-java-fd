package io;

import java.io.*;

public class CharacterInput {
    public static void main(String[] args) {
        try (FileReader br = new FileReader("src/io/CharacterInput.java")) {
            int c;
            while ((c = br.read()) != -1) {
                System.out.write(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
