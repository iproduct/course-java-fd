package invoicing.dao;

import invoicing.model.Product;

import java.util.*;

public class ProductRepositoryListImpl implements ProductRepository{
    private static long nextId = 0L;
    private List<Product> products = new ArrayList<>();

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public List<Product> findAllSorted(Comparator<Product> productComparator) {
        List<Product> toSort = new ArrayList<>(products);
//        List<Product> toSort = new ArrayList<>();
//        Collections.copy(toSort, products);
        toSort.sort(productComparator);
        return toSort;
    }

    @Override
    public List<Product> findAllSorted(Comparator<Product> productComparator, boolean reverse) {
        return findAllSorted(reverse?
                productComparator :
                productComparator.reversed());
    }

    @Override
    public Product findById(Long id) {
        //1)
//        for(int i = 0; i < products.size(); i++){
//            Product p = products.get(i);
//            if(p.getId().equals(id)) {
//                return p;
//            }
//        }
        //2)
//        for(Product p : products){
//            if(p.getId().equals(id)) {
//                return p;
//            }
//        }
//        // 3)
//        Iterator<Product> it = products.iterator(); // O(N) complexity
//        while(it.hasNext()){
//            Product p = it.next();
//            if(p.getId().equals(id)) {
//                return p;
//            }
//        }

        // 4) - better performance
        int index = Collections.binarySearch(products, new Product(id)); // O(log(N))
        if(index >= 0) {
            return products.get(index);
        }
        return null;
    }

    @Override
    public Product create(Product product) {
        product.setId(++nextId);
        products.add(product);
        return product;
    }

    @Override
    public Product update(Product product) {
        int index = Collections.binarySearch(products, product); // O(log(N))
        if(index >= 0) {
            products.set(index, product);
            return products.get(index);
        }
        return null;
    }

    @Override
    public Product deleteById(Long id) {
        int index = Collections.binarySearch(products, new Product(id)); // O(log(N))
        if(index >= 0) {
            return products.remove(index);
        }
        return null;
    }

    @Override
    public long count() {
        return products.size();
    }
}
