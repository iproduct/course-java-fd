package course.java.invoicing.dao;

import course.java.invoicing.model.Product;

public class ProductRepositoryMock extends MockRepository<Long, Product> implements ProductRepository {
    public ProductRepositoryMock(KeyGenerator<Long> keyGenerator) {
        super(keyGenerator);
    }
}
