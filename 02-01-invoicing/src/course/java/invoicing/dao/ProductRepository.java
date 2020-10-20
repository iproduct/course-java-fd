package course.java.invoicing.dao;

import course.java.invoicing.model.Product;

import java.util.Collection;

public interface ProductRepository {
    Collection<Product> findAll();
    Product findById(Long id);
    Product create(Product product);
    Product update(Product product);
    Product deleteById(Long id);
    long count();
}
