package invoicing.model;

import java.util.Objects;

import static invoicing.model.Unit.PCS;

public class Product implements Comparable<Product> {
//    public static long nextId = 0L;
    private Long id; // = ++ nextId; //default initialization when declaring attribute
    private String code;
    private String name;
    private String description = "No description";
    private String comments; // null, readonly
    private double price; // 0.0
    private Unit unit = PCS; //default initialization when declaring attribute

    // Constructors overloading
    // no-args constructor
    public Product(){
    }

    // required args constructor
    public Product(String code, String name, String description, double price) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // more constructors
    public Product(String code, String name, String description, double price, Unit unit) {
        this(code, name, description, price);
        this.unit = unit;
    }

    // all args constructor
    public Product(Long id, String code, String name, String description, double price, Unit unit) {
        this(code, name, description, price, unit);
        this.id = id;
    }

    public Product(Long id) {
        this.id = id;
    }

    // getters and setters
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

    public String getComments() {
        if(comments == null){ // lazy initialization
            comments = "A lot of comments here ...";
        }
        return comments;
    }

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", unit=").append(unit);
        sb.append(", comments='").append(getComments()).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static String formatAsTableRow(Product p) {
        return String.format("| %5d | %5.5s | %-30.30s | %-30.30s | %8.2f | %3.3s |",
            p.getId(), p.getCode(), p.getName(), p.getDescription(), p. getPrice(), p.getUnit());
    }

    @Override
    public int compareTo(Product other) {
        return getId().compareTo(other.getId());
    }
}
