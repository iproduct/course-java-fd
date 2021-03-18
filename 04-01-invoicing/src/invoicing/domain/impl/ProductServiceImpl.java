package invoicing.domain.impl;

import invoicing.dao.Repository;
import invoicing.exception.EntityNotFoundException;
import invoicing.exception.InvalidEntityDataException;
import invoicing.domain.ProductService;
import invoicing.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductServiceImpl implements ProductService {
    private static final Logger LOG = Logger.getLogger(ProductServiceImpl.class.getName());

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
    public Product addProduct(Product product) throws InvalidEntityDataException {
        // validate product code
        Pattern codePattern = Pattern.compile("([A-Z]{2})(\\d{3})");
        Matcher codeMatcher = codePattern.matcher(product.getCode());
        if (codeMatcher.matches()) {
            LOG.fine(String.format("Product Category: %s, Product Number: %s",
                    codeMatcher.group(1), codeMatcher.group(2)));
        } else {
            throw new InvalidEntityDataException(
                    String.format("Invalid product code: '%s' for product '%s'",
                            product.getCode(), product.getName())
            );
        }
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
