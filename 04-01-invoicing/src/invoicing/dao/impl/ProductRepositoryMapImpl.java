package invoicing.dao.impl;

import invoicing.dao.ProductRepository;
import invoicing.model.Product;

import java.util.*;

public class ProductRepositoryMapImpl implements ProductRepository {
    private static long nextId = 0L;
    private Map<Long, Product> products = new HashMap<>();

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public List<Product> findAllSorted(Comparator comparator) {
        List<Product> productsList = findAll();
        productsList.sort(comparator);
        return productsList;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public Product create(Product product) {
        product.setId(++nextId);
        return products.put(product.getId(), product);
    }

    @Override
    public Product update(Product product) {
        Product oldProduct = products.replace(product.getId(), product);
        if(oldProduct == null) {
            // TODO throw exception
        }
        return product;
    }

    @Override
    public Optional<Product> deleteById(Long id) {
        return Optional.ofNullable(products.remove(id));
    }

    @Override
    public long count() {
        return products.size();
    }
}
