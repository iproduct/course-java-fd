package org.iproduct.invoicing;

import org.iproduct.invoicing.dao.*;
import org.iproduct.invoicing.exceptions.NonexistingEntityException;
import org.iproduct.invoicing.model.Client;
import org.iproduct.invoicing.model.Contragent;
import org.iproduct.invoicing.model.Issuer;
import org.iproduct.invoicing.model.Product;
import org.iproduct.invoicing.util.ProductPriceComparator;
import org.iproduct.invoicing.view.InputUtils;

import static org.iproduct.invoicing.view.Alignment.*;
import static org.iproduct.invoicing.view.FieldType.DECIMAL;
import static org.iproduct.invoicing.view.PrintUtils.ColumnDescriptor;

import java.util.*;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;
import static org.iproduct.invoicing.view.PrintUtils.printTable;

import org.iproduct.invoicing.view.FieldConfig;

public class Main {
    private static final Logger LOG = Logger.getLogger("org.iproduct.invoicing.Main");
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
        List<Product> sampleProducts = List.of(
                new Product("BK002", "UML Distilled", 28.7),
                new Product("BK001", "Thinking in Java", 35.5),
                new Product("BK003", "Head-first Java", 32.7),
                new Product("BK004", "Effective Java", 45.5)
                );
        for (Product p : sampleProducts) {
            productRepo.create(p);
        }
        List<Product> products = new LinkedList<>(productRepo.findAll());
        Collections.sort(products, new ProductPriceComparator().reversed());
        printProducts(products);

        // delete product
//        try {
//            productRepo.deleteById(6L);
//        } catch (NonexistingEntityException e) {
//            LOG.log(SEVERE, "Error deleting product:", e);
//        }
//        System.out.println();
//        printProducts(productRepo.findAll());

        // update product
        Product p2 = productRepo.findById(2L);
        p2.setPrice(p2.getPrice() + 10);
        try {
            productRepo.update(p2);
        } catch (NonexistingEntityException e) {
            LOG.log(SEVERE, "Error updating product:", e);
        }
        System.out.println();
        printProducts(productRepo.findAll());

        // Issuer tests
        System.out.println("\nIssuer Demos:\n-------------------------------------------------");
        List<Contragent> contragents = Arrays.asList(new Contragent[] {
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
        contragents.forEach(issuer -> issuerRepo.create(issuer));
        issuerRepo.findAll().forEach(issuer -> System.out.println(issuer.toString()));

        // print products
        List<ColumnDescriptor> productDescriptors = List.of(
                new ColumnDescriptor("id", "ID", 4, RIGHT),
                new ColumnDescriptor("code", "Code", 7, CENTER),
                new ColumnDescriptor("name", "Name", 30, LEFT),
                new ColumnDescriptor("price", "Price", 10, RIGHT),
                new ColumnDescriptor("unit", "Unit", 4, CENTER)
        );
        System.out.println();
        System.out.println(printTable(productDescriptors, sampleProducts));

        // print contragents
        List<ColumnDescriptor> contragentDescriptors = List.of(
                new ColumnDescriptor("id", "ID", 4, RIGHT),
                new ColumnDescriptor("name", "Name", 30, LEFT),
                new ColumnDescriptor("address", "Address", 30, LEFT),
                new ColumnDescriptor("telephone", "Phone", 18, CENTER),
                new ColumnDescriptor("vatNumber", "VAT #", 12, LEFT),
                new ColumnDescriptor("email", "Email", 20, LEFT)
        );
        System.out.println();
        System.out.println(printTable(contragentDescriptors, contragents));

        // test input utilities
        InputUtils.inputInstance(List.of(
                new FieldConfig("name", "Product Name"),
                new FieldConfig("code", "Product Code", null, "^[A-Z]{3}\\d{3}$" ),
                new FieldConfig("price", "Price", null, DECIMAL, 8, 2)
        ), null);
    }
}
