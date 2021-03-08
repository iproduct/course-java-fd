package invoicing;

import invoicing.model.Product;

public class Main {
    public static void main(String[] args) {
        Product p1 = new Product("BK001", "Thinking in Java",
                "Good introduction to Java ...", 35.99);
        System.out.println(p1);
        Product p2 = new Product("BK002", "UML Distilled",
                "UML described briefly ...", 25.50);
        System.out.println(p2);
    }
}
