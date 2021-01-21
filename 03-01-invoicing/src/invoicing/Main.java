package invoicing;

import invoicing.dao.ProductRepositoryArray;
import invoicing.model.Product;
import invoicing.model.Unit;
import invoicing.model.User;

public class Main {
    public static int MAX_PRODUCTS = 10;

    public static void main(String[] args) {
//        Product[] products = new Product[MAX_PRODUCTS];
//        products[0] = new Product("BK001", "Thinking in Java",
//                "Classical introduction to Java by Bruce Eckel", 52);
//        products[1] = new Product("BK002", "UML Distilled",
//                "UML introduction by Martin Fowler", 32.5);
//        products[2] = new Product("AC001", "Whiteboard Markers",
//                "High-quality whiteboard markers in 3 colors set", 5.75);
//        products[3] = new Product("SV001", "Mobile Internet",
//                "On demand mobile internet package", 10.99, Unit.GB);
//        products[4] = new Product("SV002", "Mobile Internet 2",
//                "On demand mobile internet package", 12.99, Unit.GB);
//
//        for (Product p : products) {
//            p.setPrice(1.2 * p.getPrice());
//            System.out.println(p);
//        }
//        for (int i = 0; i < products.length; i++) {
//            Product p = products[i];
//            p.setPrice(p.getPrice() / 1.2);
//            System.out.printf("%d -> %s\n", i + 1, products[i]);
//        }

        //        String[] usernames = {"john", "george", "anna"};
        String[] usernames = new String[4];
        usernames[0] = "john";
        usernames[1] = "mike";
        usernames[2] = "peter";
        usernames[3] = "ane";

        for (
                String u : usernames) {
            u = u + "_123";
        }
        for (
                int i = 0;
                i < usernames.length; i++) {
            usernames[i] = usernames[i] + "_123";
        }
        for (
                String u : usernames) {
            System.out.println(u);
        }

        // Construct a user
        User john = new User("John", "Smith", "john", "john123");
//        User mike = new User();
        System.out.println(john);

        // Create products using repository
        ProductRepositoryArray repo = new ProductRepositoryArray();
        repo.add(new Product("BK001", "Thinking in Java",
                "Classical introduction to Java by Bruce Eckel", 52));
        repo.add(new Product("BK002", "UML Distilled",
                "UML introduction by Martin Fowler", 32.5));
        repo.add(new Product("AC001", "Whiteboard Markers",
                "High-quality whiteboard markers in 3 colors set", 5.75));
        repo.add(new Product("SV001", "Mobile Internet",
                "On demand mobile internet package", 10.99, Unit.GB));
        repo.add(new Product("SV002", "Mobile Internet 2",
                "On demand mobile internet package", 12.99, Unit.GB));

        Product[] allProducts = repo.findAll();
        for(Product p: allProducts){
            System.out.println(p);
        }

        Product p1 = new Product(1L, "BK001", "Thinking in Java",
                "Classical introduction to Java by Bruce Eckel", 52, Unit.PCS);
        Product p2 = new Product(1L,"BK001", "Thinking in Java",
                "Classical introduction to Java by Bruce Eckel", 64, Unit.PCS);
        System.out.printf("Equal == : %b\n", p1 == p2);
        System.out.println(p1);
        System.out.println(p2);
        System.out.printf("Equal equals() : %b\n", p1.equals(p2));

    }
}
