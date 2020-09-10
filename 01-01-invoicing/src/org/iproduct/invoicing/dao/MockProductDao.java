package org.iproduct.invoicing.dao;

import org.iproduct.invoicing.model.Product;

import java.util.*;

public class MockProductDao implements ProductDao {
    public static final int MAX_PRODUCTS = 10;
    private static long nextId = 0;
    private Product[] products = new Product[MAX_PRODUCTS];
    private int numProducts = 0;
    // Map<Long, Product> products = new HashMap<>();

    @Override
    public Collection<Product> findAll() {
        return Arrays.asList(Arrays.copyOf(products, numProducts));
//        return products.values();
    }

    @Override
    public Product findById(Long id) {
        int index = Arrays.binarySearch(products, 0, numProducts, new Product(id));
        return products[index];
//        return products.get(id);
    }

    @Override
    public Product create(Product product) {
        product.setId(getNextId());
//        int indexToInsert = - Arrays.binarySearch(products, product) - 1;
        if(numProducts < MAX_PRODUCTS) {
            products[numProducts++] = product;
        } else {
            // TODO: throw exception
        }
//        products.put(product.getId(), product);
        return product;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public Product deleteById(Long id) {
        int index = Arrays.binarySearch(products, 0, numProducts, new Product(id));
        if(index >= 0) {
            Product removed = products[index];
            numProducts --;
            for(int i = index; i < numProducts; i++) {
                products[i] = products[i + 1];
            }
            return removed;
        }
        return null;
    }

    @Override
    public long size() {
        return numProducts;
    }

    protected Long getNextId() {
        return ++nextId;
    }
}
