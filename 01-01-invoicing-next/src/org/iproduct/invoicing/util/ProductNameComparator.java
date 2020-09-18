package org.iproduct.invoicing.util;

import org.iproduct.invoicing.model.Product;

import java.util.Comparator;

public class ProductNameComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p1.getName().compareToIgnoreCase(p2.getName());
    }
}
