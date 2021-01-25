package invoicing.dao;

public interface KeyGenerator<K> {
    K getNextId();
}
