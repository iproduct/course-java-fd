package invoicing.model;

import invoicing.dao.Identifiable;

import java.io.Serializable;
import java.util.Objects;

import static invoicing.model.Unit.PCS;

public class Product implements Comparable<Product>, Identifiable<Long>, Serializable {
    static final long serialVersionUID = 1L;
    //  0. private static long nextId = 0L; // static initialization
    private Long id; // = ++nextId; // default initialization
    private String code;
    private String name;
    private String description;
    private double price;
    private Unit unit = PCS; // default initialization

    // 1. Constructors - overloaded
    public Product() {
    }

    public Product(Long id) {
        this.id = id;
    }

    public Product(String code, String name, String description, double price) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(String code, String name, String description, double price, Unit unit) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.unit = unit; // initialization in constructor
    }

    public Product(Long id, String code, String name, String description, double price, Unit unit) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.unit = unit;
    }


    // 2. Getters and setters = properties
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    // 3. object identity => hashCode and equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // 4. String representation of the state of object toString()

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", unit=").append(unit);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Product other) {
        return this.getId().compareTo(other.getId());
    }
}
