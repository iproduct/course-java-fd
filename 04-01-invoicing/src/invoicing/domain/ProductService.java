package invoicing.domain;

import invoicing.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findProducts();
    Optional<Product> findProductById(Long id);
    Product addProduct(Product product);
    Product updateProduct(Product product);
    Product deleteProductById(Long id);
    long getProductsCount();
}
