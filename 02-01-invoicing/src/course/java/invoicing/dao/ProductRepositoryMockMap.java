package course.java.invoicing.dao;

import course.java.invoicing.model.Product;
import course.java.invoicing.util.ProductPriceAndIdComparator;
import course.java.invoicing.util.ProductPriceComparator;

import java.util.*;

public class ProductRepositoryMockMap implements ProductRepository {
    private static ProductRepositoryMockMap instance = new ProductRepositoryMockMap();
    public static ProductRepository getInstance() {
        return instance;
    }

//    private AtomicLong nextId = new AtomicLong(0L);
//    private List<Product> products = new CopyOnWriteArrayList<>();

    private long nextId = 0L;
    private Map<Long, Product> products = new HashMap<>();
    private TreeSet<Product> priceSortedProducts = new TreeSet<>(new ProductPriceAndIdComparator());

    private ProductRepositoryMockMap() {
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public List<Product> findAllSorted(Comparator<Product> comparator) {
        List<Product> copy = new ArrayList<>(products.values());
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
    public List<Product> findAllSortedByPrice(boolean reverse) {
        List<Product> result = new ArrayList<>(priceSortedProducts);
        if(reverse) {
           Collections.reverse(result);
        }
        return result;
    }

    @Override
    public Product findById(Long id) {
        return products.get(id);
    }

    @Override
    public Product create(Product product) {
        product.setId(++nextId);
        products.put(product.getId(), product);
        priceSortedProducts.add(product);
        return product;
    }

    @Override
    public Product update(Product product) {
        Product old = products.replace(product.getId(), product);
        if(old == null) {
            return null;
        }
        priceSortedProducts.remove(old);
        priceSortedProducts.add(product);
        return product;
    }

    @Override
    public Product deleteById(Long id) {
        Product old = products.remove(id);
        if(old == null) {
            return null;
        }
        priceSortedProducts.remove(old);
        return old;
    }

    @Override
    public long count() {
        return products.size();
    }
}
