package org.iproduct.invoicing.service;

import org.iproduct.invoicing.exceptions.NonexistingEntityException;
import org.iproduct.invoicing.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product addProduct(Product product);
    Product updateProduct(Product product) throws NonexistingEntityException;
    Product deleteProductById(Long id) throws NonexistingEntityException;
    long size();
}
