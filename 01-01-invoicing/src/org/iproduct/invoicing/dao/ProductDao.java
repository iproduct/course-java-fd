package org.iproduct.invoicing.dao;

import org.iproduct.invoicing.model.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

// Product Repo API
public interface ProductDao {
    Collection<Product> findAll();
    Product findById(Long id);
    Product create(Product product);
    Product update(Product product);
    Product deleteById(Long id);
    long size();
}
