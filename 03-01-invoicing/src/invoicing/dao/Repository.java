package invoicing.dao;

import invoicing.model.Product;

import java.util.Comparator;
import java.util.List;

/**
 * Repository of Products ...
 */
public interface Repository<K, V extends Identifiable<K>> {
    List<V> findAll(); // no body - only specification - public by default
    List<V> findAllSorted(Comparator<V> productComparator);
    List<V> findAllSorted(Comparator<V> productComparator, boolean reverse);

    /**
     * find product by id
     * @param id product id
     * @return the found product if exists otherwise null
     */
    V findById(K id); // return null - special value strategy
    V create(V entity);
    V update(V entity);
    V deleteById(K id);
    long count();
}
