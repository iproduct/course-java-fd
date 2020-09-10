package org.iproduct.invoicing;

import org.iproduct.invoicing.dao.MockProductDao;
import org.iproduct.invoicing.dao.ProductDao;
import org.iproduct.invoicing.model.Product;

import java.util.Collection;
import java.util.Iterator;

import static org.iproduct.invoicing.model.Unit.PCS;

public class Main {
    public static void printProducts(Collection<Product> products) {
//        Iterator<Product> productIterator = products.iterator();
//        while(productIterator.hasNext()) {
//            System.out.println(productIterator.next());
//        }
        for(Product p: products) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        ProductDao productRepo = new MockProductDao();
        Product[] sampleProducts = {
                new Product("BK002", "UML Distilled", 28.7),
                new Product("BK001", "Thinking in Java", 35.5),
                new Product("BK003", "Head-first Java", 32.7),
                new Product("BK004", "Effective Java", 45.5),
        };
        for (Product p : sampleProducts) {
            productRepo.create(p);
        }
        printProducts(productRepo.findAll());
        productRepo.deleteById(3L);
        System.out.println();
        printProducts(productRepo.findAll());

    }
}
