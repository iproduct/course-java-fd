package course.java.invoicing.dao;

import course.java.invoicing.exception.RepositoryFullException;
import course.java.invoicing.model.Product;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class ProductRepositoryMockArrays implements ProductRepository {
    public static final int MAX_PRODUCTS = 10;
    private static ProductRepositoryMockArrays instance = new ProductRepositoryMockArrays();

    private long nextId = 0L;
    private Product[] products = new Product[MAX_PRODUCTS];
    private int numProducts = 0;

    public static ProductRepository getInstance() {
        return instance;
    }

    private ProductRepositoryMockArrays() {
    }

    @Override
    public List<Product> findAll() {
        return Arrays.asList(Arrays.copyOf(products, numProducts));
    }

    @Override
    public List<Product> findAllSorted(Comparator<Product> comparator) {
        Product[] copy = Arrays.copyOf(products, numProducts);
        Arrays.sort(copy, comparator);
        return Arrays.asList(copy);
    }

    @Override
    public List<Product> findAllSorted(Comparator<Product> comparator, boolean reverse) {
        if(reverse) {
            return findAllSorted(comparator.reversed());
        } else {
            return findAllSorted(comparator);
        }
    }

    @Override
    public Product findById(Long id) {
        int index = Arrays.binarySearch(products, 0, numProducts, new Product(id));
        if(index < 0) {
            return null;
        }
        return products[index];
    }

    @Override
    public Product create(Product product) {
        product.setId(++nextId);
        if(numProducts >= MAX_PRODUCTS){
            throw new RepositoryFullException(
                String.format("Product repository has reached maximal capacity of %s.", MAX_PRODUCTS));
        }
        products[numProducts++] = product;
        return product;
    }

    @Override
    public Product update(Product product) {
        int index = Arrays.binarySearch(products, 0, numProducts, product);
        if(index < 0) {
            return null;
        }
        products[index] = product;
        return product;
    }

    @Override
    public Product deleteById(Long id) {
        int index = Arrays.binarySearch(products, 0, numProducts, new Product(id));
        if(index < 0) {
            return null;
        }
        Product removed = products[index];
        numProducts --;
        for(int i = index; i < numProducts; i++) {
            products[i] = products[i+1];
        }

        return removed;
    }

    @Override
    public long count() {
        return numProducts;
    }
}
