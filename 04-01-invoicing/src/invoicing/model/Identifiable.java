package invoicing.model;

public interface Identifiable<K> {
    K getId();
    void setId(K id);
}
