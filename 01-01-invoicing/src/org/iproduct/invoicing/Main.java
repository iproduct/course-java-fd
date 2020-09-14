package org.iproduct.invoicing;

import org.iproduct.invoicing.dao.*;
import org.iproduct.invoicing.model.Client;
import org.iproduct.invoicing.model.Contragent;
import org.iproduct.invoicing.model.Issuer;
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
        KeyGenerator<Long> longKeyGenerator = new LongKeyGenerator();
        Repository<Long, Product> productRepo = new MockRepository<>(longKeyGenerator);
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

        // Issuer tests
        System.out.println("\nIssuer Demos:\n-------------------------------------------------");
        List<Contragent> issuers = Arrays.asList(new Contragent[] {
                new Issuer(123456789L, "IT Bookstore Ltd.", "Sofia, Ivan Asen 25A",
                        "BGUNCR1234567890", "BGUNCR",
                        "+(359) 2 896123", "BG123456789"),
                new Issuer(567889432L, "Best Software AD", "Sofia, 1000",
                        "BGFIB123456ASD7890", "BGFIB",
                        "+(359) 2 567789", "BG567889432"),
                new Client(123456789L, "ABC Ltd.", "Sofia, Ivan Asen 25A",
                        "(+359) 2 896123", "BG123456789", "abc@abv.bg"),
                new Client(567889432L, "Dimitar Petrov", "Provdiv, ul. Centralna, 56",
                        "(+359) 32 34534", null,"dimitar@gmail.com", true),
        });
        KeyGenerator<Long> issuerKeyGenerator = new LongKeyGenerator();
        Repository<Long, Contragent> issuerRepo = new MockRepository<>(issuerKeyGenerator);
        issuers.forEach(issuer -> issuerRepo.create(issuer));
        issuerRepo.findAll().forEach(issuer -> System.out.println(issuer.toString()));

    }
}
