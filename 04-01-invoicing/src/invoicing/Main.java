package invoicing;

import invoicing.dao.ProductRepository;
import invoicing.dao.Repository;
import invoicing.dao.impl.*;
import invoicing.model.Product;
import invoicing.model.Role;
import invoicing.model.Unit;
import invoicing.model.User;
import invoicing.util.ProductByPriceComparator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static invoicing.model.Product.formatAsTableRow;

public class Main {
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
        for(Product p: products) {
            productRepo.create(p);
        }
        // print all products in repo
        for (Product p: productRepo.findAll()) {
            System.out.println(formatAsTableRow(p));
        }
        Product p6 = productRepo.findById(6L).get();
        System.out.printf("Serching by ID=%d: %s\n", p6.getId(), p6);
        // update product
        p6.setPrice(12.8);
        productRepo.update(p6);
        // delete product
        productRepo.deleteById(1L);
        // print products sorted by price descending
        for (Product p: productRepo.findAllSorted(new ProductByPriceComparator().reversed())) {
            System.out.println(formatAsTableRow(p));
        }

        // Test users
        List<User> users = List.of(
                new User("Default", "Admin", "admin", "admin", Role.ADMIN),
                new User("Ivan", "Petrov", "ivan", "ivan"),
                new User("Hristina", "Dimitrova", "hrisi", "hrisi", Role.ADMIN),
                new User("Nadia", "Nikolova", "nadia", "nadia")
        );
        Repository<Long, User> userRepo = new RepositoryMapImpl<>(new LongIdGenerator());
        Iterator<User> iter = users.iterator();
        while(iter.hasNext()) {
            userRepo.create(iter.next());
        }
        ListIterator<User> listIter = userRepo.findAll().listIterator();
        while(listIter.hasNext()){
            System.out.println(listIter.next());
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
