package invoicing;

import invoicing.dao.*;
import invoicing.model.Product;
import invoicing.model.Unit;
import invoicing.model.User;
import invoicing.util.ProductCodeComarator;
import invoicing.util.ProductPriceComarator;

import java.util.List;

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
        ProductRepository repo = new ProductRepositoryImpl(new LongKeyGenerator());
        repo.create(new Product("BK001", "Thinking in Java",
                "Classical introduction to Java by Bruce Eckel", 52));
        repo.create(new Product("BK002", "UML Distilled",
                "UML introduction by Martin Fowler", 32.5));
        repo.create(new Product("AC001", "Whiteboard Markers",
                "High-quality whiteboard markers in 3 colors set", 5.75));
        repo.create(new Product("SV001", "Mobile Internet",
                "On demand mobile internet package", 10.99, Unit.GB));
        repo.create(new Product("SV002", "Mobile Internet 2",
                "On demand mobile internet package", 12.99, Unit.GB));

        List<Product> allProducts = repo.findAll();
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

        // Try sorting
        System.out.println("\nProducts sorted by ID (default Comparable):");
        List<Product> productsSorted = repo.findAll();
        for(Product p: productsSorted){
            System.out.println(p);
        }

        System.out.println("\nProducts sorted by Code (custom Comparator):");
        List<Product> productsSortedByCode = repo.findAllSorted(new ProductCodeComarator());
        for(Product p: productsSortedByCode){
            System.out.println(p);
        }

        System.out.println("\nProducts sorted by Price (custom Comparator):");
        List<Product> productsSortedByPrice = repo.findAllSorted(new ProductPriceComarator().reversed());
        for(Product p: productsSortedByPrice){
            System.out.println(p);
        }

        // CRUD operation
        System.out.println("\nCRUD demos:");
        repo.create(new Product("AC002", "Cleaning Sponge",
                "High-quality cleaning sponge", 2.75));
        for(Product p : repo.findAll()) {
            System.out.println(p);
        }
        System.out.printf("Before: %d -> %s", 1, repo.findById(1L));

        Product p3 = repo.findById(1L);
        p3.setName("PROMOTION 50%: " + p3.getName());
        p3.setPrice(0.5 * p3.getPrice());
        repo.update(p3);
        System.out.printf("After update: %d -> %s", 1, repo.findById(1L));

        System.out.println("\nAfter Delete:");
        repo.deleteById(1L);
        for(Product p : repo.findAll()) {
            System.out.println(p);
        }

    }
}
