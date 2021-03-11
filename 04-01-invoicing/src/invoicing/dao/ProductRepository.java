package invoicing.dao;

import invoicing.model.Product;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    List<Product> findAllSorted(Comparator comparator);
    Optional<Product> findById(Long id);
    Product create(Product product);
    Product update(Product product);
    Optional<Product> deleteById(Long id);
    long count();
}