package invoicing.dao.impl;

import invoicing.model.Product;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class ProductRepositoryArray {
    public static long nextId = 0L;
    private static final int DEFAULT_CAPACITY = 32;
    private Product[] products = new Product[DEFAULT_CAPACITY];
    private int length = 0;
    private int capacity = DEFAULT_CAPACITY;

    public ProductRepositoryArray() {
    }
    public ProductRepositoryArray(int capacity) {
        this.capacity = capacity;
        products = new Product[capacity];
    }

    public Product[] findAll() {
        return Arrays.copyOfRange(products, 0, length);
    }

    public Product findById(Long id){
//        for(Product p: products){ // O(N)
//            if(p.getId().equals(id)){
//                return p;
//            }
//        }
        int index = Arrays.binarySearch(products,0, length, new Product(id)); // O(log2(N))
        if(index >= 0){
            return products[index];
        } else {
            return null;
        }
    }

    public Product[] findAllSorted(Comparator comp) {
        Product[] results = Arrays.copyOfRange(products, 0, length);
        Arrays.sort(results, comp);
        return results;
    }


    public Product add(Product product) {
        product.setId(++ nextId);
        if(length >= capacity) {
            products = Arrays.copyOf(products, 2 * capacity);
            capacity *= 2;
        }
        products[length] = product;
        length++;
        return product;
    }
}
