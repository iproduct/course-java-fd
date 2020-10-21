package course.java.invoicing.util;

import course.java.invoicing.model.Product;

import java.util.Comparator;

public class ProductPriceComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        return (int) Math.signum(p1.getPrice() - p2.getPrice());
    }
}
