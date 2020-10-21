package course.java.invoicing.dao;

import course.java.invoicing.model.Product;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    List<Product> findAllSorted(Comparator<Product> comparator);
    List<Product> findAllSorted(Comparator<Product> comparator, boolean reverse);
    Product findById(Long id);
    Product create(Product product);
    Product update(Product product);
    Product deleteById(Long id);
    long count();
}
