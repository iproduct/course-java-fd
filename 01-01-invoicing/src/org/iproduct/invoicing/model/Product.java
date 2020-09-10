package org.iproduct.invoicing.model;

import java.util.Arrays;
import java.util.Objects;

import static org.iproduct.invoicing.model.Unit.*;

public class Product implements Comparable<Product> {
//    private static long nextId = 0;
    private Long id; // = ++nextId;
    private String code;
    private String name;
    private double price;
    private Unit unit = PCS; //default

    // No args constructor
    public Product() {
    }

    // Required args constructor
    public Product(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public Product(String code, String name, double price, Unit unit) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.unit = unit;
    }

    // All args constructor
    public Product(Long id, String code, String name, double price, Unit unit) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.unit = unit;
    }

    public Product(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long anId) {
        id = anId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", price=").append(price);
        sb.append(", unit=").append(unit);
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) {
        Product[] products = new Product[10];
        for(int i = 0; i < products.length; i++) {
            products[i] = new Product();
        }
//        for(Product p : products) {
//            System.out.println(p);
//        }
//        System.out.println(Arrays.toString(products));
//        for(int i = 0; i < products.length; i++) {
//            System.out.println(products[i]);
//        }

        Product p1 = new Product(1L,"BK001", "Thinking in Java", 35.5, PCS);
        Product p2  = new Product(2L, "BK002", "UML Distilled", 28.7, PCS);
//        p2.setId(p1.getId());
        System.out.println(p1);
        System.out.println(p2);

        System.out.println(p1.equals(p2));
    }

    @Override
    public int compareTo(Product other) {
        return this.id.compareTo(other.id);
    }
}
