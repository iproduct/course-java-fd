package course.java.invoicing.dao;

import course.java.invoicing.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductRepositoryOldMockList implements ProductRepositoryOld {
    private static ProductRepositoryOldMockList instance = new ProductRepositoryOldMockList();
    public static ProductRepositoryOld getInstance() {
        return instance;
    }

//    private AtomicLong nextId = new AtomicLong(0L);
//    private List<Product> products = new CopyOnWriteArrayList<>();

    private long nextId = 0L;
    private List<Product> products = new ArrayList<>();

    private ProductRepositoryOldMockList() {
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public List<Product> findAllSorted(Comparator<Product> comparator) {
        List<Product> copy = new ArrayList<>(products);
//        Collections.copy(products, copy);
        copy.sort(comparator);
        return copy;
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
        int indexFound = Collections.binarySearch(products, new Product(id));
        if(indexFound < 0) {
            return null;
        }
        return products.get(indexFound);
    }

    @Override
    public Product create(Product product) {
//        product.setId(nextId.incrementAndGet());
        product.setId(++nextId);
        products.add(product);
        return product;
    }

    @Override
    public Product update(Product product) {
        int index = Collections.binarySearch(products, product);
        if(index < 0) {
            return null;
        }
        products.set(index, product);
//        products.replaceAll(pr -> pr.equals(product) ? product: pr);
        return product;
    }

    @Override
    public Product deleteById(Long id) {
        int index = Collections.binarySearch(products, new Product(id));
        if(index < 0) {
            return null;
        }
        return products.remove(index);
    }

    @Override
    public long count() {
        return products.size();
    }
}
