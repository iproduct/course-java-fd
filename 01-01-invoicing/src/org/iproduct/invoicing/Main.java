package org.iproduct.invoicing;

import org.iproduct.invoicing.dao.*;
import org.iproduct.invoicing.exceptions.EntityAlreadyExistsException;
import org.iproduct.invoicing.exceptions.NonexistingEntityException;
import org.iproduct.invoicing.model.Client;
import org.iproduct.invoicing.model.Contragent;
import org.iproduct.invoicing.model.Issuer;
import org.iproduct.invoicing.model.Product;
import org.iproduct.invoicing.service.ContragentService;
import org.iproduct.invoicing.service.ContragentServiceImpl;
import org.iproduct.invoicing.service.ProductService;
import org.iproduct.invoicing.service.ProductServiceImpl;
import org.iproduct.invoicing.util.ProductPriceComparator;
import org.iproduct.invoicing.view.InputUtils;

import static org.iproduct.invoicing.view.Alignment.*;
import static org.iproduct.invoicing.view.FieldType.*;
import static org.iproduct.invoicing.view.PrintUtils.ColumnDescriptor;

import java.util.*;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;
import static org.iproduct.invoicing.view.PrintUtils.formatTable;

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
        ProductService productService = new ProductServiceImpl(productRepo);
        List<Product> sampleProducts = List.of(
                new Product("BK002", "UML Distilled", 28.7),
                new Product("BK001", "Thinking in Java", 35.5),
                new Product("BK003", "Head-first Java", 32.7),
                new Product("BK004", "Effective Java", 45.5)
                );
        for (Product p : sampleProducts) {
            try {
                productService.addProduct(p);
            } catch (EntityAlreadyExistsException e) {
                LOG.log(SEVERE, "Error creating product:", e);
            }
        }
        List<Product> products = productService.getAllProducts();
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
        Product p2 = productService.getProductById(2L);
        p2.setPrice(p2.getPrice() + 10);
        try {
            productService.updateProduct(p2);
        } catch (NonexistingEntityException e) {
            LOG.log(SEVERE, "Error updating product:", e);
        }
        System.out.println();
        printProducts(productService.getAllProducts());

        // Issuer tests
        System.out.println("\nIssuer Demos:\n-------------------------------------------------");
        List<Contragent> contragents = Arrays.asList(new Contragent[] {
                new Issuer(123456789L, "IT Bookstore Ltd.", "Sofia, Ivan Asen 25A",
                        "BGUNCR1234567890", "BGUNCR",
                        "+(359) 2 896123", "BG123456789"),
                new Issuer(567889432L, "Best Software AD", "Sofia, 1000",
                        "BGFIB123456ASD7890", "BGFIB",
                        "+(359) 2 567789", "BG567889432"),
                new Client(923456789L, "ABC Ltd.", "Sofia, Ivan Asen 25A",
                        "(+359) 2 896123", "BG123456789", "abc@abv.bg"),
                new Client(867889432L, "Dimitar Petrov", "Provdiv, ul. Centralna, 56",
                        "(+359) 32 34534", null,"dimitar@gmail.com", true),
        });
        ContragentRepository contragentRepo = new MockContragentRepository();
        contragents.forEach(c -> {
            try {
                contragentRepo.create(c);
            } catch (EntityAlreadyExistsException e) {
                e.printStackTrace();
            }
        });
        System.out.println(contragentRepo.findByName("ABC Ltd."));

        ContragentService contragentService = new ContragentServiceImpl(contragentRepo);
//
//        contragents.forEach(issuer -> {
//            try {
//                contragentService.addContragent(issuer);
//            } catch (EntityAlreadyExistsException e) {
//                LOG.log(SEVERE, "Error creating contragent:", e);
//            }
//        });
//        contragentService.getAllContragents().forEach(issuer -> System.out.println(issuer.toString()));

        // print products
        List<ColumnDescriptor> productDescriptors = List.of(
                new ColumnDescriptor("id", "ID", 4, RIGHT),
                new ColumnDescriptor("code", "Code", 7, CENTER),
                new ColumnDescriptor("name", "Name", 30, LEFT),
                new ColumnDescriptor("price", "Price", 10, RIGHT),
                new ColumnDescriptor("unit", "Unit", 4, CENTER)
        );
        System.out.println();
        System.out.println(formatTable(productDescriptors, productService.getAllProducts()));

        // print contragents
        List<ColumnDescriptor> contragentDescriptors = List.of(
                new ColumnDescriptor("id", "ID", 10, RIGHT),
                new ColumnDescriptor("name", "Name", 30, LEFT),
                new ColumnDescriptor("address", "Address", 30, LEFT),
                new ColumnDescriptor("telephone", "Phone", 18, CENTER),
                new ColumnDescriptor("vatNumber", "VAT #", 12, LEFT),
                new ColumnDescriptor("email", "Email", 20, LEFT)
        );
        System.out.println();
        System.out.println(formatTable(contragentDescriptors, contragentService.getAllContragents()));

        // test input utilities
        Product product = new Product();
        InputUtils.inputInstance(List.of(
                new FieldConfig("name", "Product Name"),
                new FieldConfig("code", "Product Code", null, "^[A-Z]{2}\\d{3}$" ),
                new FieldConfig("price", "Price", null, DECIMAL, 8, 2),
                new FieldConfig("unit", "Unit", "0", UNIT)
        ), product);
        try {
            productService.addProduct(product);
        } catch (EntityAlreadyExistsException e) {
            LOG.log(SEVERE, "Error creating product:", e);
        }
        System.out.println(formatTable(productDescriptors, productService.getAllProducts()));

//        Contragent client = new Client();
//        InputUtils.inputInstance(List.of(
//                new FieldConfig("id", "Client ID", null, LONG, 10),
//                new FieldConfig("name", "Client Name"),
//                new FieldConfig("address", "Address"),
//                new FieldConfig("telephone", "Phone", true),
//                new FieldConfig("vatNumber", "VAT Number", true, "^BG[1-9]\\d{8}$"),
//                new FieldConfig("email", "Email", true, "^[A-Za-z0-9+_.-]+@(.+)$")
//                ), client);
//        try {
//            contragentService.addContragent(client);
//        } catch (EntityAlreadyExistsException e) {
//            LOG.log(SEVERE, "Error creating contragent:", e);
//        }
//        System.out.println(printTable(contragentDescriptors, contragentService.getAllContragents()));
    }
}
