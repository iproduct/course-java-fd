package invoicing;

import invoicing.control.InvoiceController;
import invoicing.dao.*;
import invoicing.exception.InvalidClientDataException;
import invoicing.model.*;
import invoicing.util.ProductCodeComarator;
import invoicing.util.ProductPriceComarator;
import jdbcsimple.JdbcSimpleDemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static final String DATABASE_FILE = "invoices.db";
    private static final Logger LOG = Logger.getLogger("invoicing.Main");
    public static final String DB_DRIVER = "org.postgresql.Driver";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/invoicing";
    public static final String DB_USER = "postgres";
    public static final String DB_PASSWORD = "root";
    public static final String DB_CONFIG_PATH = "db.properties";

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
//        List<Product> productsSortedByCode = repo.findAllSorted(new ProductCodeComarator());
        List<Product> productsSortedByCode = repo.findAllSorted(
                (Product prod1, Product prod2)-> prod1.getCode().compareTo(prod2.getCode())
        );
        for(Product p: productsSortedByCode){
            System.out.println(p);
        }

        System.out.println("\nProducts sorted by Price (custom Comparator):");
//        List<Product> productsSortedByPrice = repo.findAllSorted(new ProductPriceComarator().reversed());
        List<Product> productsSortedByPrice = repo.findAllSorted(
                (prod1, prod2) -> -Double.compare(prod1.getPrice(), prod2.getPrice())
        );
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

        // Contragents demo
        Supplier s1 = new Supplier("Software AD", "Bucharest, Victory 23",
                "RO123456789", "RO47BCAX", "RO47BCAX1234567890");
        System.out.printf("Supplier 1: %s\n", s1);
        Client c1 = new Client("ABC Ltd.", "Sofia, 1000",
                "BG789123456", "office@abc.com");
        System.out.printf("Client 1: %s\n", c1);

        // Formatting demo + visibility
        Contragent c2 = new Contragent("Best Widgets Ltd.", "Plovdiv, 25A",
                "BG111111111", "(+359)32 1234566");
        Contragent c3 = new Client("John Smith", "Plovdiv, 25A",
                "1234567890", "john@gmail.com");

        Contragent c4 = new Client("Mary", "Plovdiv, 25A",
                "2222222222", "mary@gmail.com");

        KeyGenerator<Long> keyGenerator = new LongKeyGenerator();
        ContragentRepository contragentRepo = new ContragentRepositoryImpl(keyGenerator);

//        contragentRepo.create(s1);
//        contragentRepo.create(c1);
//        contragentRepo.create(c2);
//        contragentRepo.create(c3);
//        contragentRepo.create(new Person("Ana Nikolova", "Sofia, Graf Ignatiev 12",
//                "72121234567", "ana@mail.com"));

//        List<Contragent> contragents = contragentRepo.findAll();
//        for(Contragent c : contragents) {
//            System.out.println(c.format());
//        }
//
//        // Polymorphism demo
//        for(Contragent c : contragents) {
//            System.out.println(c.toString()); // polymorphism = dynamic binding = runtime binding
//            if(c instanceof Supplier) {
//                ((Supplier) c).login();
//            }
//        }

        InvoiceController invoiceController = InvoiceController.getInstance();
//        try {
//            invoiceController.load(DATABASE_FILE);
//        } catch (IOException e) {
//            LOG.log(Level.SEVERE, "Problem loading invoices from database file: " + DATABASE_FILE, e);
//        }
        try {
//            invoiceController.addContragent(s1);
//            invoiceController.addContragent(c1);
//            invoiceController.addContragent(c2);
//            invoiceController.addContragent(c3);
//            invoiceController.addContragent(new Person("Ana Nikolova",
//                    "Sofia, Graf Ignatiev 12",
//                    "72121234567", "ana@mail.com"));
            invoiceController.addContragent(c4);
        } catch (InvalidClientDataException ex) {
            LOG.log(Level.WARNING,
                    String.format("Error adding contragent %s: ", ex.getInvalidEntity()),
                    ex);
            System.out.printf("User error: %s\n\n", ex.getMessage());
        }
        System.out.println(invoiceController.reportContragents());

        // Streaming API
        allProducts.stream()
                .sorted(Comparator.comparingDouble(p -> - p.getPrice()))
                .forEach(System.out::println);

        DoubleSummaryStatistics stat = allProducts.stream()
                .mapToDouble(Product::getPrice)
                .summaryStatistics();
        System.out.printf("Product Statistics:\n%s\n", stat);

        // FIleIO serialization and deserialization demo
//        try {
//            invoiceController.save(DATABASE_FILE);
//        } catch (IOException e) {
//            LOG.log(Level.SEVERE, "Problem saving invoices to database file: " + DATABASE_FILE, e);
//        }

        // JDBC demo
        Properties props = new Properties();
        try {
            String dbConfigPath =
                    JdbcSimpleDemo.class.getClassLoader().getResource(DB_CONFIG_PATH).getPath();
            props.load(new FileInputStream(dbConfigPath));
        } catch (IOException ioException) {
            LOG.warning("Can not load DB properties from db.properties file.");
            props.setProperty("driver", DB_DRIVER);
            props.setProperty("url", DB_URL);
            props.setProperty("user", DB_USER);
            props.setProperty("password", DB_PASSWORD);
        }
        ProductRepository productsRepo = new ProductRepositoryJdbcImpl(props);

        // demonstrate product create and findAll
        Product created = productsRepo.create(new Product("BK-NEW","New Book", "Novelties in Java ...", 35.99));
        List<Product> results = productsRepo.findAll();
        results.forEach(System.out::println);
        System.out.println();

        // demonstrate product findById
        System.out.printf("findById(%d) -> %s%n%n", created.getId(), productsRepo.findById(created.getId()));

        // demonstrate product update
        created.setName("MODIFIED JAVA BOOK");
        created.setPrice(29.99);
        productsRepo.update(created);
        results = productsRepo.findAll();
        results.forEach(System.out::println);
        System.out.println();

        // demonstrate product delete
        Product deleted = productsRepo.deleteById(created.getId());
        System.out.printf("Successfully deleted: %s\n\n", deleted);
        results = productsRepo.findAll();
        results.forEach(System.out::println);
        System.out.println();

        // demonstrate count
        System.out.printf("count() -> %d%n%n", productsRepo.count());

    }
}
