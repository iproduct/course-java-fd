package invoicing.dao;

import invoicing.model.Product;

import java.util.*;

public class RepositoryMemoryImpl<K, V extends Identifiable<K>> implements Repository<K,V>{
    private Map<K, V> products = new HashMap<>();
    private KeyGenerator<K> keyGenerator; // has_a = composition

    public RepositoryMemoryImpl(KeyGenerator<K> keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    @Override
    public List<V> findAll() {
         return new ArrayList<>(products.values());
    }

    @Override
    public List<V> findAllSorted(Comparator<V> productComparator) {
        List<V> list = new ArrayList<>(products.values());
        list.sort(productComparator);
        return list;
    }

    @Override
    public List<V> findAllSorted(Comparator<V> productComparator, boolean reverse) {
        return findAllSorted(productComparator.reversed());
    }

    @Override
    public V findById(K id) {
        return products.get(id); // O(1)
    }

    @Override
    public V create(V product) {
        product.setId(keyGenerator.getNextId());
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public V update(V product) {
        if(products.replace(product.getId(), product) == null) {
            return null;
        }
        return product;
    }

    @Override
    public V deleteById(K id) {
        return products.remove(id);
    }

    @Override
    public long count() {
        return 0;
    }
}
