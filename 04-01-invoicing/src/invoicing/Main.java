package invoicing;

import invoicing.dao.Repository;
import invoicing.exception.EntityNotFoundException;
import invoicing.dao.impl.*;
import invoicing.model.*;
import invoicing.util.PrintUtil;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static invoicing.model.Product.formatAsTableRow;
import static invoicing.util.Alignment.*;
import static java.util.logging.Level.SEVERE;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    static {
        LOG.setLevel(Level.FINEST);
    }

    // or -Djava.util.logging.config.file=logging.properties
    public static void main(String[] args) {
        Product p1 = new Product("BK001", "Thinking in Java",
                "Good introduction to Java ...", 35.99);
        Product p2 = new Product("BK002", "UML Distilled",
                "UML described briefly ...", 25.50);
        Product[] products = {p1, p2,
                new Product("AC001", "Whiteboard Markers", "5 colors set", 7.8),
                new Product("SV001", "Mobile Internet", "On-demand mobile internet package",
                        10.99, Unit.GB),
                new Product("DV001", "2 Band Router", "Supports 2.4 GHz and 5.7 GHz bands",
                        45.5),
                new Product("CB001", "Network Cable Cat. 6E", "Gbit Eternet cable UTP",
                        0.72, Unit.M),
        };

        // create product repository and add products
        Repository<Long, Product> productRepo = new RepositoryMapImpl<>(new LongIdGenerator());
        for (Product p : products) {
            productRepo.create(p);
        }
        // print all products in repo
        for (Product p : productRepo.findAll()) {
//            System.out.println(formatAsTableRow(p));
            System.out.println(p);
        }
        Product p6 = productRepo.findById(6L).get();
        System.out.printf("Serching by ID=%d: %s\n", p6.getId(), p6);
        // update product
        p6.setPrice(12.8);
        p6.setId(8L);
        try {
            productRepo.update(p6);
        } catch (EntityNotFoundException e) {
            LOG.log(SEVERE, "Error updating product:", e);
        } finally {
            LOG.fine("Finally executed.");
        }
        // delete product
        productRepo.deleteById(1L);
        // print products sorted by price descending
        for (Product p : productRepo.findAllSorted(
                (Product o1, Product o2) -> (int) Math.signum(o2.getPrice() - o1.getPrice())
        )) {
            System.out.println(formatAsTableRow(p));
        }

        // Common entity metadata column descriptors
        List<PrintUtil.ColumnDescriptor> metadataColumns = List.of(
                new PrintUtil.ColumnDescriptor("created", "Ctreated", 19, CENTER),
                new PrintUtil.ColumnDescriptor("updated", "Updated", 19, CENTER),
                new PrintUtil.ColumnDescriptor("createdById", "Created By", 11, CENTER),
                new PrintUtil.ColumnDescriptor("updatedById", "Updated By", 11, CENTER)
        );

        // Print formatted report as table
        List<PrintUtil.ColumnDescriptor> productColumns = new ArrayList<>(List.of(
                new PrintUtil.ColumnDescriptor("id", "ID", 5, RIGHT),
                new PrintUtil.ColumnDescriptor("code", "Code", 5, LEFT),
                new PrintUtil.ColumnDescriptor("name", "Name", 12, LEFT),
                new PrintUtil.ColumnDescriptor("description", "Description", 12, LEFT),
                new PrintUtil.ColumnDescriptor("price", "Price", 8, RIGHT, 2),
                new PrintUtil.ColumnDescriptor("unit", "Unit", 5, CENTER)
        ));
        productColumns.addAll(metadataColumns);
        String productReport = PrintUtil.formatTable(productColumns, productRepo.findAll(), "Products List:");
        System.out.println(productReport);

        // Test users
        List<User> users = List.of(
                new User("Default", "Admin", "admin", "admin", Role.ADMIN),
                new User("Ivan", "Petrov", "ivan", "ivan"),
                new User("Hristina", "Dimitrova", "hrisi", "hrisi", Role.ADMIN),
                new User("Nadia", "Nikolova", "nadia", "nadia")
        );
        Repository<Long, User> userRepo = new RepositoryMapImpl<>(new LongIdGenerator());
        Iterator<User> iter = users.iterator();
        while (iter.hasNext()) {
            userRepo.create(iter.next());
        }
        ListIterator<User> listIter = userRepo.findAll().listIterator();
        while (listIter.hasNext()) {
            System.out.println(listIter.next());
        }

        // Print formatted report as table
        List<PrintUtil.ColumnDescriptor> userColumns = new ArrayList<>(List.of(
                new PrintUtil.ColumnDescriptor("id", "ID", 5, RIGHT),
                new PrintUtil.ColumnDescriptor("firstName", "First Name", 12, LEFT),
                new PrintUtil.ColumnDescriptor("lastName", "Last Name", 12, LEFT),
                new PrintUtil.ColumnDescriptor("username", "Username", 12, LEFT),
                new PrintUtil.ColumnDescriptor("password", "Password", 12, LEFT),
                new PrintUtil.ColumnDescriptor("role", "Role", 5, LEFT)
        ));
        userColumns.addAll(metadataColumns);
        String userReport = PrintUtil.formatTable(userColumns, userRepo.findAll(), "Users List:");
        System.out.println(userReport);

        // Contragents demo
        Repository<Long, Contragent> contragentRepo = new RepositoryMapImpl<>(new LongIdGenerator());
        contragentRepo.create(new Supplier("Software AD", "Sofia 1000",
                "123456789", "BG", "+(3592) 887123",
                "UNCR1234556789012", "UNCR"));
        contragentRepo.create(new Client("ABC Ltd.", "Sofia, Ivan Asen 32", "222333445",
                "BG", "office@abc.com"));
        contragentRepo.create(new Client("Krum Petrov", "Plovdiv, Hristo Botev 12",
                "7811034569", "office@abc.com"));

        for (Contragent c : contragentRepo.findAll()) {
            System.out.println(c.toString()); // polymorphism
            if (c instanceof Supplier) {
                System.out.printf("IBAN: %s\n", ((Supplier) c).getIban());
            }
        }
//        while(listIter.hasPrevious()){
//            System.out.println(listIter.previous());
//        }


        // array iteration by index
//        for (int i = 0; i < products.length; i++) {
//            System.out.printf("%d => %s\n", i + 1, formatAsTableRow(products[i]));
//        }

//        for (Product p: products) {
//            if(p.getCode().startsWith("BK")) {
//                p = null;
//            }
//        }
//        int len = products.length;
//        int i = 0;
//        while(i < len) {
////        for (int i = 0; i < len; i++) {
//            if (products[i].getCode().startsWith("BK")) {
//                for(int j = i; j < len - 1; j++) {
//                    products[j] = products[j + 1];
//                }
//                len --;
//            } else {
//                i++;
//            }
//        }
//        products = Arrays.copyOfRange(products, 0, len);

        // array iteration using foreach
//        for (Product p : products) {
//            p.setPrice(p.getPrice() / 1.1); // promotion 10%
//
//        }

    }
}
