package invoicing.util;

import invoicing.model.Product;

import java.util.Comparator;

public class ProductPriceComarator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return Double.compare(p1.getPrice(),p2.getPrice());
    }
}
