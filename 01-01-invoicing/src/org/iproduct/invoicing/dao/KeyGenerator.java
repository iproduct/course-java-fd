package org.iproduct.invoicing.dao;

public interface KeyGenerator<K> {
    K getNextId(); // returns unique new key
}
