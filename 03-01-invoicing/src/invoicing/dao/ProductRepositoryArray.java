package invoicing.dao;

import invoicing.model.Product;

public class ProductRepositoryArray {
    private static long nextId = 0L;
    public static int MAX_PRODUCTS = 10;
    private Product[] products = new Product[MAX_PRODUCTS];
    private int len = 0;

    public ProductRepositoryArray() {
    }

    public ProductRepositoryArray(int maxProducts) {
        products = new Product[maxProducts];
    }

    public Product addProduct(Product product) {
        product.setId(++nextId);
        products[len] = product;
        len++;
        return product;
    }

}
