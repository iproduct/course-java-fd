package invoicing.dao;

import invoicing.model.Product;

import java.util.Comparator;
import java.util.List;

/**
 * Repository of Products ...
 */
public interface ProductRepositoryOld {
    List<Product> findAll(); // no body - only specification - public by default
    List<Product> findAllSorted(Comparator<Product> productComparator);
    List<Product> findAllSorted(Comparator<Product> productComparator, boolean reverse);

    /**
     * find product by id
     * @param id product id
     * @return the found product if exists otherwise null
     */
    Product findById(Long id); // return null - special value strategy
    Product create(Product product);
    Product update(Product product);
    Product deleteById(Long id);
    long count();
}
