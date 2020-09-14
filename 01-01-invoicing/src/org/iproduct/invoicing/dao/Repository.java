package org.iproduct.invoicing.dao;

import org.iproduct.invoicing.model.Product;

import java.util.Collection;
import java.util.List;

// Repository API
public interface Repository<K, E extends Identifiable<K>> {
    Collection<E> findAll();
    E findById(K id);
    E create(E item);
    E update(E item);
    E deleteById(K id);
    long size();
}
