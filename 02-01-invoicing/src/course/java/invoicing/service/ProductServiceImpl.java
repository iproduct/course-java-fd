package course.java.invoicing.service;

import course.java.invoicing.dao.ProductRepository;
import course.java.invoicing.model.Product;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepo;

    public ProductServiceImpl(ProductRepository productRepo){
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
    public Product getProductById(Long id) {
        return productRepo.findById(id);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepo.create(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepo.update(product);
    }

    @Override
    public Product deleteProduct(Long id) {
        return productRepo.deleteById(id);
    }

    @Override
    public long getCount() {
        return productRepo.count();
    }
}
