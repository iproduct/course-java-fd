package invoicing.domain;

import invoicing.dao.exception.EntityNotFoundException;
import invoicing.dao.exception.InvalidEntityDataException;
import invoicing.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findProducts();
    Optional<Product> findProductById(Long id);
    Product addProduct(Product product) throws InvalidEntityDataException;
    Product updateProduct(Product product) throws EntityNotFoundException;
    Product deleteProductById(Long id) throws EntityNotFoundException ;
    long getProductsCount();
}