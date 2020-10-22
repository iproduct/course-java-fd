package course.java.invoicing.dao;

public interface KeyGenerator<K> {
    K getNextId();
}
