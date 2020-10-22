package course.java.invoicing.model;

import course.java.invoicing.dao.Identifiable;

import java.util.Objects;

import static course.java.invoicing.model.Unit.M;
import static course.java.invoicing.model.Unit.PCS;

public class Product implements Comparable<Product>, Identifiable<Long> {
    private static long nextId = 0L;
    private Long id = ++nextId;
    private String code;
    private String name;
    private Double price;
    private Unit unit = PCS;

    // No args constructor
    public Product() {
    }

    // Required args constructor
    public Product(String code, String name, Double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public Product(String code, String name, Double price, Unit unit) {
        this(code, name, price);
        this.unit = unit;
    }

    // All args constructor
    public Product(Long id, String code, String name, Double price, Unit unit) {
        this(code, name, price, unit);
        this.id = id;
    }

    public Product(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append("ID: ").append(id)
                .append(", Code: ").append(code)
                .append(", Name: ").append(name)
                .append(", Price: ").append(price)
                .append(", Unit: ").append(unit)
                .toString();
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
        if (id == null) return 0;
        long longId = id;
        return (int)(longId >> 32 ^ longId);
//        return Objects.hash(id);
    }

    @Override
    public int compareTo(Product o) {
        return id.compareTo(o.getId());
    }
}
