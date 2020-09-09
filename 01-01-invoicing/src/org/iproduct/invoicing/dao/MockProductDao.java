package org.iproduct.invoicing.dao;

import org.iproduct.invoicing.model.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MockProductDao implements ProductDao {
    private static long nextId = 0;
    Map<Long, Product> products = new HashMap<>();

    @Override
    public Collection<Product> findAll() {
        return products.values();
    }

    @Override
    public Product findById(Long id) {
        return products.get(id);
    }

    @Override
    public Product create(Product product) {
        product.setId(getNextId());
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public Product deleteById(Long id) {
        return null;
    }

    @Override
    public long size() {
        return 0;
    }

    protected Long getNextId() {
        return ++nextId;
    }
}
