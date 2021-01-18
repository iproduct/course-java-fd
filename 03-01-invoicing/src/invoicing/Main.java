package invoicing;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Product[] products = {
                new Product("BK001", "Thinking in Java",
                        "Classical introduction to Java by Bruce Eckel", 52),
                new Product("BK002", "UML Distilled",
                        "UML introduction by Martin Fowler", 32.5),
                new Product("AC001", "Whiteboard Markers",
                        "High-quality whiteboard markers in 3 colors set", 5.75),
        };
        for(Product p : products){
            System.out.println(p);
        }

    }
}
