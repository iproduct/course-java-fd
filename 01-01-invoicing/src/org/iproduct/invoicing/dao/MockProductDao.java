package org.iproduct.invoicing.dao;

import org.iproduct.invoicing.model.Product;

import java.util.*;

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
        return products.put(product.getId(), product);
    }

    @Override
    public Product update(Product product) {
        return  products.put(product.getId(), product);
    }

    @Override
    public Product deleteById(Long id) {
         return products.remove(id);
    }

    @Override
    public long size() {
        return products.size();
    }

    protected Long getNextId() {
        return ++nextId;
    }
}
