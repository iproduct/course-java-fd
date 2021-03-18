package invoicing.dao;

import invoicing.exception.EntityNotFoundException;
import invoicing.model.Product;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    List<Product> findAllSorted(Comparator<Product> comparator);
    Optional<Product> findById(Long id);
    Product create(Product product);
    Product update(Product product) throws EntityNotFoundException;
    Optional<Product> deleteById(Long id);
    long count();
}
