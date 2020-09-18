package org.iproduct.invoicing.service;

import org.iproduct.invoicing.dao.KeyGenerator;
import org.iproduct.invoicing.dao.LongKeyGenerator;
import org.iproduct.invoicing.dao.MockRepository;
import org.iproduct.invoicing.dao.Repository;
import org.iproduct.invoicing.exceptions.EntityAlreadyExistsException;
import org.iproduct.invoicing.model.Client;
import org.iproduct.invoicing.model.Contragent;
import org.iproduct.invoicing.model.Issuer;
import org.iproduct.invoicing.model.Product;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

public class InvoiceRegister {
    private static final Logger LOG = Logger.getLogger("o.i.i.s.InvoiceRegister");

    private Repository<Long, Product> productRepo;
    private ProductService productService;
    private Repository<Long, Contragent> contragentRepo;
    private ContragentService contragentService;

    public void init() {
        // Initialize products
        KeyGenerator<Long> longKeyGenerator = new LongKeyGenerator();
        productRepo = new MockRepository<>(longKeyGenerator);
        productService = new ProductServiceImpl(productRepo);
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

        // Initialize contragents
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
        contragentRepo = new MockRepository<>();
        contragentService = new ContragentServiceImpl(contragentRepo);

        contragents.forEach(issuer -> {
            try {
                contragentService.addContragent(issuer);
            } catch (EntityAlreadyExistsException e) {
                LOG.log(SEVERE, "Error creating contragent:", e);
            }
        });
    }
}
