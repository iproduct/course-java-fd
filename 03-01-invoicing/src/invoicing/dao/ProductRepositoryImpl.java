package invoicing.dao;

import invoicing.model.Product;

public class ProductRepositoryImpl extends RepositoryMemoryImpl<Long, Product> implements ProductRepository{
    public ProductRepositoryImpl(KeyGenerator<Long> keyGenerator) {
        super(keyGenerator);
    }
}
