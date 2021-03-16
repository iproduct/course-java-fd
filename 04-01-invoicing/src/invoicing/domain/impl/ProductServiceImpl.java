package invoicing.domain.impl;

import invoicing.dao.Repository;
import invoicing.dao.exception.EntityNotFoundException;
import invoicing.dao.impl.LongIdGenerator;
import invoicing.dao.impl.RepositoryMapImpl;
import invoicing.domain.ProductService;
import invoicing.model.Product;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private Repository<Long, Product> productRepo;

    public ProductServiceImpl(Repository<Long, Product> productRepo) { // Dependency Injection - DI
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> findProducts() {
        return productRepo.findAll();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepo.findById(id);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepo.create(product);
    }

    @Override
    public Product updateProduct(Product product) throws EntityNotFoundException {
        return productRepo.update(product);
    }

    @Override
    public Product deleteProductById(Long id) throws EntityNotFoundException {
        return productRepo.deleteById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Product with ID='%s' does not exist", id)));
    }

    @Override
    public long getProductsCount() {
        return productRepo.count();
    }
}
