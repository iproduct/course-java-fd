package course.java.invoicing;

import course.java.invoicing.dao.*;
import course.java.invoicing.model.Client;
import course.java.invoicing.model.Contragent;
import course.java.invoicing.model.Product;
import course.java.invoicing.model.Supplier;
import course.java.invoicing.service.ContragentService;
import course.java.invoicing.service.ContragentServiceImpl;
import course.java.invoicing.service.ProductService;
import course.java.invoicing.service.ProductServiceImpl;
import course.java.invoicing.util.PrintUtils;

import static course.java.invoicing.util.PrintUtils.entitiesToString;

public class InvoiceRegister {
    private ProductService productService;
    private ContragentService contragentService;

    public InvoiceRegister(){
        KeyGenerator<Long> productIdGen = new LongKeyGenerator();
        ProductRepository productRepo = new ProductRepositoryMock(productIdGen);
        productService = new ProductServiceImpl(productRepo);
        KeyGenerator<Long> contragentIdGen = new LongKeyGenerator();
        ContragentRepository contragentRepo = new ContragentRepositoryMock(contragentIdGen);
        contragentService = new ContragentServiceImpl(contragentRepo);
    }

    public void init() {
        contragentService.addContragent(
                new Supplier(123456789L, "Best Widgets", "Sofia 1000", "BG123456789",
                        "RZBB123A1234566677", "RZBB"));
        contragentService.addContragent(
                new Client(9545678901L, "Ivan Petrov", "Plovdiv", null, true));
    }

    public void printAllContragents() {
        System.out.println(entitiesToString(contragentService.getAllContragents()));
    }

    public static void main(String[] args) {
        InvoiceRegister register = new InvoiceRegister();
        register.init();
        register.printAllContragents();
    }

}
