package org.iproduct.invoicing.service;

import org.iproduct.invoicing.dao.Repository;
import org.iproduct.invoicing.exceptions.EntityAlreadyExistsException;
import org.iproduct.invoicing.exceptions.NonexistingEntityException;
import org.iproduct.invoicing.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private Repository<Long, Product> productRepo;

    public ProductServiceImpl(Repository<Long, Product> productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> results = new ArrayList<>(productRepo.findAll());
        return results;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id);
    }

    @Override
    public Product addProduct(Product product) throws EntityAlreadyExistsException {
        return productRepo.create(product);
    }

    @Override
    public Product updateProduct(Product product) throws NonexistingEntityException {
        return productRepo.update(product);
    }

    @Override
    public Product deleteProductById(Long id) throws NonexistingEntityException {
        return productRepo.deleteById(id);
    }

    @Override
    public long size() {
        return productRepo.size();
    }
}
