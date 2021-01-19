package invoicing.dao;

import invoicing.model.Product;

public class ProductRepositoryArray {
    private static long nextId = 0L;
    public static int MAX_PRODUCTS = 4;
    private Product[] products = new Product[MAX_PRODUCTS];
    private int len = 0; // number of products

    public ProductRepositoryArray() {
    }

    public ProductRepositoryArray(int maxProducts) {
        products = new Product[maxProducts];
    }

    public Product addProduct(Product product) {
        product.setId(++nextId);
        if(len == products.length) {
            Product[] newProducts = new Product[2 * products.length];
            for(int i = 0; i < len; i++) {
                newProducts[i] = products[i];
            }
            products = newProducts;
        }
        products[len] = product;
        len++;
        return product;
    }

}
