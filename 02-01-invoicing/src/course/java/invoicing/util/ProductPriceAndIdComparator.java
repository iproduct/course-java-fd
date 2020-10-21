package course.java.invoicing.util;

import course.java.invoicing.model.Product;

import java.util.Comparator;

public class ProductPriceAndIdComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        int result = (int) Math.signum(p1.getPrice() - p2.getPrice());
        if(result == 0) {
            return p1.getId().compareTo(p2.getId());
        } else {
            return result;
        }
    }
}
