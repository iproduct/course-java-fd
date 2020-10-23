package course.java.invoicing;

import course.java.invoicing.dao.*;
import course.java.invoicing.exception.NonexistingProductException;
import course.java.invoicing.model.Client;
import course.java.invoicing.model.Contragent;
import course.java.invoicing.model.Product;
import course.java.invoicing.model.Supplier;
import course.java.invoicing.service.ContragentService;
import course.java.invoicing.service.ContragentServiceImpl;
import course.java.invoicing.service.ProductService;
import course.java.invoicing.service.ProductServiceImpl;
import course.java.invoicing.util.Alignment;
import course.java.invoicing.util.PrintUtils;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static course.java.invoicing.model.Unit.M;
import static course.java.invoicing.model.Unit.PCS;
import static course.java.invoicing.util.Alignment.*;
import static course.java.invoicing.util.PrintUtils.entitiesToString;
import static course.java.invoicing.util.PrintUtils.formatTable;
import static course.java.invoicing.util.PrintUtils.ColumnDescriptor;

public class InvoiceRegister {
    private static Logger LOG = Logger.getLogger("c.j.i.InvoiceRegister");
    //    static {
//        LOG.setLevel(Level.SEVERE);
//    }
    // or -Djava.util.logging.config.file=logging.properties
    public static final List<ColumnDescriptor> productColumns = Arrays.asList(new ColumnDescriptor[]{
            new ColumnDescriptor("id", "ID", 5, RIGHT),
            new ColumnDescriptor("code", "Code", 7, CENTER),
            new ColumnDescriptor("name", "Name", 30, LEFT),
            new ColumnDescriptor("price", "Price", 10, RIGHT),
            new ColumnDescriptor("unit", "Unit", 4, CENTER)
    });
    public static final List<ColumnDescriptor> contragentColumns = Arrays.asList(new ColumnDescriptor[]{
            new ColumnDescriptor("id", "ID", 10, RIGHT),
            new ColumnDescriptor("name", "Name", 30, LEFT),
            new ColumnDescriptor("address", "Address", 30, RIGHT),
            new ColumnDescriptor("telephone", "Phone", 18, CENTER),
            new ColumnDescriptor("vatNumber", "VAT #", 12, CENTER)
    });
    private ProductService productService;
    private ContragentService contragentService;

    public InvoiceRegister() {
        KeyGenerator<Long> productIdGen = new LongKeyGenerator();
        ProductRepository productRepo = new ProductRepositoryMock(productIdGen);
        productService = new ProductServiceImpl(productRepo);
        KeyGenerator<Long> contragentIdGen = new LongKeyGenerator();
        ContragentRepository contragentRepo = new ContragentRepositoryMock(contragentIdGen);
        contragentService = new ContragentServiceImpl(contragentRepo);
    }

    public void init() {
        //init contragents
        contragentService.addContragent(
                new Supplier(123456789L, "Best Widgets", "Sofia 1000", "BG123456789",
                        "RZBB123A1234566677", "RZBB"));
        contragentService.addContragent(
                new Client(9545678901L, "Ivan Petrov", "Plovdiv", null, true));

        // init products
        Arrays.stream(new Product[]{
                new Product("BK001", "Thinking in Java", 35.5),
                new Product("BK002", "UML Distilled", 28.7),
                new Product("CB002", "Network cable Cat.6", 2.25, M),
                new Product("BK001", "Thinking in Java", 35.5)})
                .forEach(productService::addProduct);

        try {
            Product tij3 = productService.getProductById(1L);
            tij3.setPrice(tij3.getPrice() * 1.2);
            productService.updateProduct(tij3);
//                    new Product(6L, "BK006", "Thinking in C++", 35.5, PCS));
        } catch (NonexistingProductException e) {
            LOG.log(Level.SEVERE, "Error initializing products: ", e);
        } finally {
            LOG.info("Update try finished.");
        }

    }

    public void printAllContragents() {
        System.out.println(entitiesToString(contragentService.getAllContragents()));
    }

    public void printAllProducts() {
        System.out.println(formatTable(productColumns, productService.getAllProducts()));
    }

    public static void main(String[] args) {
        InvoiceRegister register = new InvoiceRegister();
        register.init();
//        register.printAllContragents();
        register.printAllProducts();

    }

}
