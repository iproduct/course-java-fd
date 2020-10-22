package course.java.invoicing.dao;

import course.java.invoicing.model.Product;
import course.java.invoicing.util.ProductPriceComparator;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface ProductRepositoryOld {
    List<Product> findAll();
    List<Product> findAllSorted(Comparator<Product> comparator);
    List<Product> findAllSorted(Comparator<Product> comparator, boolean reverse);
    default List<Product> findAllSortedByPrice(boolean reverse) {
        return findAllSorted(new ProductPriceComparator(), reverse);
    }
    Product findById(Long id);
    Product create(Product product);
    Product update(Product product);
    Product deleteById(Long id);
    long count();
}
