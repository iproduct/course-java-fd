package course.java.invoicing.service;

import course.java.invoicing.exception.NonexistingEntityException;
import course.java.invoicing.model.Product;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface ProductService {
    Collection<Product> getAllProducts();
    List<Product> getProductsSorted(Comparator<Product> comparator);
    Product getProductById(Long id) throws NonexistingEntityException;
    Product addProduct(Product product);
    Product updateProduct(Product product) throws NonexistingEntityException;
    Product deleteProduct(Long productId) throws NonexistingEntityException;
    long getCount();
}
