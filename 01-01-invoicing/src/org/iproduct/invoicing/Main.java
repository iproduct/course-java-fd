package org.iproduct.invoicing;

import org.iproduct.invoicing.dao.MockProductDao;
import org.iproduct.invoicing.dao.MockProductDaoArrays;
import org.iproduct.invoicing.dao.ProductDao;
import org.iproduct.invoicing.model.Product;
import org.iproduct.invoicing.util.ProductNameComparator;
import org.iproduct.invoicing.util.ProductPriceComparator;

import java.util.*;

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
        ProductDao productRepo = new MockProductDaoArrays();
        Product[] sampleProducts = {
                new Product("BK002", "UML Distilled", 28.7),
                new Product("BK001", "Thinking in Java", 35.5),
                new Product("BK003", "Head-first Java", 32.7),
                new Product("BK004", "Effective Java", 45.5),
        };
        for (Product p : sampleProducts) {
            productRepo.create(p);
        }
        List<Product> products = new LinkedList<>(productRepo.findAll());
        Collections.sort(products, new ProductPriceComparator().reversed());
        printProducts(products);

        // delete product
        productRepo.deleteById(3L);
        System.out.println();
        printProducts(productRepo.findAll());

        // update product
        Product p2 = productRepo.findById(2L);
        p2.setPrice(p2.getPrice() + 10);
        productRepo.update(p2);
        System.out.println();
        printProducts(productRepo.findAll());
    }
}
