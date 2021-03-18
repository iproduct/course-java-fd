package invoicing.model;

import java.util.Date;
import java.util.Objects;

import static invoicing.model.Unit.PCS;

public class Product extends AbstractEntity<Long, Product> {
//    public static long nextId = 0L;
//    private Long id; // = ++ nextId; //default initialization when declaring attribute
    private String code;
    private String name;
    private String description = "No description";
//    private String comments; // null, readonly
    private Double price; // 0.0
    private Unit unit = PCS; //default initialization when declaring attribute

    // Constructors overloading
    // no-args constructor
    public Product(){
//        super(); // called by default
    }

    // required args constructor
    public Product(String code, String name, String description, Double price) {
        super();
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(String code, String name, String description, Double price,
                   Date created, Date updated, Long createdById, Long updatedById) {
        super(null, created, updated, createdById, updatedById);
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
    }
    // more constructors
    public Product(String code, String name, String description, Double price, Unit unit) {
        this(code, name, description, price);
        this.unit = unit;
    }

    // all args constructor
    public Product(Long id, String code, String name, String description, Double price, Unit unit) {
        this(code, name, description, price, unit);
        setId(id);
    }

    public Product(Long id, String code, String name, String description, Double price, Unit unit,
                   Date created, Date updated, Long createdById, Long updatedById) {
        super(id, created, updated, createdById, updatedById);
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.unit = unit;
    }

    public Product(Long id) {
        super(id);
    }

    // getters and setters
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

//    public String getComments() {
//        if(comments == null){ // lazy initialization
//            comments = "A lot of comments here ...";
//        }
//        return comments;
//    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(getId());
        sb.append(", code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
//        sb.append(", comments='").append(comments).append('\'');
        sb.append(", price=").append(price);
        sb.append(", unit=").append(unit);
        sb.append(", created=").append(getCreated());
        sb.append(", updated=").append(getUpdated());
        sb.append(", createdById=").append(getCreatedById());
        sb.append(", updatedById=").append(getUpdatedById());
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
