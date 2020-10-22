package course.java.invoicing;

import course.java.invoicing.dao.*;
import course.java.invoicing.model.Contragent;
import course.java.invoicing.model.Product;
import course.java.invoicing.service.ProductService;
import course.java.invoicing.service.ProductServiceImpl;

public class InvoiceRegister {
    private ProductService productService;
//    private ContragentService contragentService;

    public InvoiceRegister(){
        KeyGenerator<Long> productIdGen = new LongKeyGenerator();
        ProductRepository productRepo = new ProductRepositoryMock(productIdGen);
        productService = new ProductServiceImpl(productRepo);
        KeyGenerator<Long> contragentIdGen = new LongKeyGenerator();
//        contragentRepo = new MockRepository<>(contragentIdGen);
    }

    public void printAllContragents() {

    }

}
