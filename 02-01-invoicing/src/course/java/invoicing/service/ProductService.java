package course.java.invoicing.service;

import course.java.invoicing.exception.NonexistingProductException;
import course.java.invoicing.model.Product;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface ProductService {
    Collection<Product> getAllProducts();
    List<Product> getProductsSorted(Comparator<Product> comparator);
    Product getProductById(Long id) throws NonexistingProductException;
    Product addProduct(Product product);
    Product updateProduct(Product product) throws NonexistingProductException;
    Product deleteProduct(Long productId) throws NonexistingProductException;
    long getCount();
}
