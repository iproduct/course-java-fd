package course.java.invoicing.service;

import course.java.invoicing.dao.ProductRepository;
import course.java.invoicing.exception.NonexistingEntityException;
import course.java.invoicing.model.Product;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepo;

    public ProductServiceImpl(ProductRepository productRepo){
        this.productRepo = productRepo;
    }

    public ProductRepository getProductRepository() {
        return productRepo;
    }

    public void setProductRepository(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Collection<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> getProductsSorted(Comparator<Product> comparator) {
        return productRepo.findAllSorted(comparator);
    }

    @Override
    public Product getProductById(Long id) throws NonexistingEntityException {
        Product found = productRepo.findById(id);
        if(found == null) {
            throw new NonexistingEntityException(String.format("Product with ID:%d does not exist", id));
        }
        return found;
    }

    @Override
    public Product addProduct(Product product) {
        return productRepo.create(product);
    }

    @Override
    public Product updateProduct(Product product) throws NonexistingEntityException {
        Product updated = productRepo.update(product);
        if(updated == null) {
            throw new NonexistingEntityException(
                String.format("Product '%d: %s' does not exist", product.getId(), product.getName()));
        }
        return updated;
    }

    @Override
    public Product deleteProduct(Long id) throws NonexistingEntityException {
        Product deleted = productRepo.deleteById(id);
        if(deleted == null) {
            throw new NonexistingEntityException(
                    String.format("Product with ID:%d does not exist", id));
        }
        return deleted;
    }

    @Override
    public long getCount() {
        return productRepo.count();
    }
}
