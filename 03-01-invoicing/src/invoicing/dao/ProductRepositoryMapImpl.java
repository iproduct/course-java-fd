package invoicing.dao;

import invoicing.model.Product;

import java.util.*;

public class ProductRepositoryMapImpl implements ProductRepository{
    private static long nextId = 0L;
    private Map<Long, Product> products = new HashMap<>();
    @Override
    public List<Product> findAll() {
         return new ArrayList<>(products.values());
    }

    @Override
    public List<Product> findAllSorted(Comparator<Product> productComparator) {
        List<Product> list = new ArrayList<>(products.values());
        list.sort(productComparator);
        return list;
    }

    @Override
    public List<Product> findAllSorted(Comparator<Product> productComparator, boolean reverse) {
        return findAllSorted(productComparator.reversed());
    }

    @Override
    public Product findById(Long id) {
        return products.get(id); // O(1)
    }

    @Override
    public Product create(Product product) {
        product.setId(++nextId);
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public Product update(Product product) {
        if(products.replace(product.getId(), product) == null) {
            return null;
        }
        return product;
    }

    @Override
    public Product deleteById(Long id) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}
