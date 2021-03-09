package invoicing.util;

import invoicing.model.Product;

import java.util.Comparator;

public class ProductByPriceComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        return (int) Math.signum(o1.getPrice() - o2.getPrice());
    }
}
