package invoicing.dao.impl;

import invoicing.dao.ProductRepository;
import invoicing.exception.EntityNotFoundException;
import invoicing.model.Product;

import java.util.*;

public class ProductRepositoryList implements ProductRepository {
    public static long nextId = 0L;
    private List<Product> products = new ArrayList<>();

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public List<Product> findAllSorted(Comparator<Product> comparator) {
        List<Product> toSort = new ArrayList<>(products);
        toSort.sort(comparator);
        return toSort;
    }

    @Override
    public Optional<Product> findById(Long id) {
        int index = Collections.binarySearch(products, new Product(id));
        if(index >= 0){
            return Optional.of(products.get(index));
        }
        return Optional.empty();
    }

    @Override
    public Product create(Product product) {
        product.setId(++nextId);
        products.add(product);
        return product;
    }

    @Override
    public Product update(Product product) throws EntityNotFoundException {
        int index = Collections.binarySearch(products, product);
        if(index < 0) {
           throw new EntityNotFoundException(
                   String.format("Product %s: '%s' not found", product.getId(), product.getName())
           );
        }
        products.set(index, product);
        return product;
    }

    @Override
    public Optional<Product> deleteById(Long id) {
        int index = Collections.binarySearch(products, new Product(id));
        if(index < 0) {
            return Optional.empty();
        }
        Product deleted = products.get(index);
        products.remove(index);
        return Optional.of(deleted);
    }

    @Override
    public long count() {
        return products.size();
    }
}
